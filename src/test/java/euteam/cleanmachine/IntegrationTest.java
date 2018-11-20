package euteam.cleanmachine;

import euteam.cleanmachine.dao.*;
import euteam.cleanmachine.dto.OneTimeReservationDto;
import euteam.cleanmachine.dto.ReserveOneTimeDto;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.enums.Powder;
import euteam.cleanmachine.model.enums.RoleName;
import euteam.cleanmachine.model.enums.Softener;
import euteam.cleanmachine.model.enums.State;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.Program;
import euteam.cleanmachine.model.facility.StandardProgram;
import euteam.cleanmachine.model.facility.decorator.WithPowder;
import euteam.cleanmachine.model.facility.decorator.WithSoftener;
import euteam.cleanmachine.model.facility.machine.state.MachineState;
import euteam.cleanmachine.model.reservation.Reservation;
import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.NFCCard;
import euteam.cleanmachine.model.user.Role;
import euteam.cleanmachine.service.MachineService;
import euteam.cleanmachine.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class IntegrationTest {

    @Autowired
    MachineService machineService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    AuthItemDao authItemDao;

    @Autowired
    MachineDao machineDao;

    @Autowired
    ProgramDao programDao;

    @Autowired
    AccountDao accountDao;

    @Test
    public void reserveMachineAndDoLaundry() throws Exception {
        // Creating allowed customer
        Customer allowedCustomer = new Customer();
        allowedCustomer.setName("allowedCustomer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_CUSTOMER);
        allowedCustomer.setRole(role);
        NFCCard nfcCard1 = new NFCCard("123");
        nfcCard1 =  authItemDao.save(nfcCard1);
        allowedCustomer.addAuthItem(nfcCard1);
        Account account = new Account();
        account.setUser(allowedCustomer);
        account.setBalance(20);
        allowedCustomer.setAccount(account);
        accountDao.save(account);
        userDao.save(allowedCustomer);

        // Creating forbidden customer
        Customer forbiddenCustomer = new Customer();
        forbiddenCustomer.setName("forbiddenCustomer");
        forbiddenCustomer.setRole(role);
        NFCCard nfcCard2 = new NFCCard("456");
        nfcCard2 =  authItemDao.save(nfcCard2);
        forbiddenCustomer.addAuthItem(nfcCard2);
        userDao.save(forbiddenCustomer);

        // Allowed customer makes a reservation
        ReserveOneTimeDto reserveOneTimeDto = new ReserveOneTimeDto();
        reserveOneTimeDto.setMachineId(1L);
        Date date = new Date();
        reserveOneTimeDto.setStartDate(date);
        Date endDate =  new Date();
        endDate.setTime(date.getTime() + 3600000);
        reserveOneTimeDto.setEndDate(endDate);
        OneTimeReservationDto oneTimeReservationDto = reservationService.createOneTimeReservation(allowedCustomer, reserveOneTimeDto);

        // Forbidden user tries to authenticate on the machine reserved by the allowed user
        NFCCard finalNfcCard = nfcCard2;
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            machineService.authenticateOnMachine(finalNfcCard.getId(), "1");
        });
        assertEquals("Machine reserved by an other user", exception.getMessage());

        // Allowed customer tries to authenticate on the machine he reserved
        assertTrue(machineService.authenticateOnMachine(nfcCard1.getId(), "1"));
        Machine machine = machineDao.findById("1").orElse(null);
        assertEquals(State.AUTHENTICATED.getName(), machine.getState().getName());

        // Starting a program
        double initialBalance = allowedCustomer.getAccount().getBalance();
        Program premiumProgram = new WithPowder(new WithSoftener(new StandardProgram(), Softener.PREMIUMSOFTENER), Powder.PREMIUMPOWDER);
        premiumProgram.setDuration(0.1);
        premiumProgram = programDao.save(premiumProgram);
        machine.addProgram(premiumProgram);
        assertTrue(machineService.startProgram("1", premiumProgram.getId()));
        assertEquals(State.RUNNING.getName(), machineService.getMachineStatus("1"));

        // Account balance should be updated
        allowedCustomer = (Customer)(userDao.findById(allowedCustomer.getId()).orElse(null));
        assertEquals(initialBalance - premiumProgram.getCost(), allowedCustomer.getAccount().getBalance(), 0.0);

        // Forbidden user tries to authenticate on the machine reserved by the allowed user
        exception = assertThrows(ServiceException.class, () -> {
            machineService.authenticateOnMachine(finalNfcCard.getId(), "1");
        });
        assertEquals("Machine reserved by an other user", exception.getMessage());

        // Waiting for program to finish
        sleep(10000);

        // Program finished machine should be lock
        assertEquals(State.LOCKED.getName(), machineService.getMachineStatus("1"));

        // Forbidden user shouldn't be able to unlock the machine
        assertFalse(machineService.unlockMachine("1", nfcCard2.getId()));

        // Allowed user unlocks machine
        assertTrue(machineService.unlockMachine("1", nfcCard1.getId()));

        // Machine should be in idle state
        assertEquals(State.IDLE.getName(), machineService.getMachineStatus("1"));
    }
}
