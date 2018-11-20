package euteam.cleanmachine.Services;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dao.ReservationDao;
import euteam.cleanmachine.dao.RoleDao;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.dto.*;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.enums.ReservationPeriodicity;
import euteam.cleanmachine.model.enums.RoleName;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.reservation.Reservation;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.Employee;
import euteam.cleanmachine.model.user.Maintainer;
import euteam.cleanmachine.model.user.Role;
import euteam.cleanmachine.service.ReservationService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class ReservationServiceTest {

    private MockMvc mvc;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserDao userDao;

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    MachineDao machineDao;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void createOneTimeReservation() {
        Customer customer = new Customer();
        customer.setName("customer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_CUSTOMER);
        customer.setRole(role);
        userDao.save(customer);

        ReserveOneTimeDto reserveOneTimeDto = new ReserveOneTimeDto();
        reserveOneTimeDto.setMachineId(1L);

        Date date = new Date();
        reserveOneTimeDto.setStartDate(date);
        Date endDate =  new Date();
        endDate.setTime(date.getTime() + 3600000);
        reserveOneTimeDto.setEndDate(endDate);

        OneTimeReservationDto oneTimeReservationDto = reservationService.createOneTimeReservation(customer, reserveOneTimeDto);

        assertNotNull(oneTimeReservationDto);
        assertEquals("customer", oneTimeReservationDto.getUserDto().getName());

        Machine machine = machineDao.findById("1").orElse(null);

        Date inbetween = new Date();
        inbetween.setTime(inbetween.getTime() + 10000);
        assertTrue(reservationService.checkIfMachineReserved(machine, inbetween));

        Date notInbetween = new Date();
        notInbetween.setTime(notInbetween.getTime() + 7200000);
        assertFalse(reservationService.checkIfMachineReserved(machine, notInbetween));
    }

    @Test
    public void createMaintenanceReservation() {
        Employee maintainer = new Maintainer();
        maintainer.setName("maintainer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_MAINTAINER);
        maintainer.setRole(role);
        userDao.save(maintainer);

        ReserveMaintenanceDto reserveMaintenanceDto = new ReserveMaintenanceDto();
        reserveMaintenanceDto.setMachineId(1L);

        Date date = new Date();
        reserveMaintenanceDto.setStartDate(date);
        Date endDate =  new Date();
        endDate.setTime(date.getTime() + 3600000);
        reserveMaintenanceDto.setEndDate(endDate);

        MaintenanceReservationDto maintenanceReservationDto = reservationService.createMaintenanceReservation(maintainer, reserveMaintenanceDto);

        assertNotNull(maintenanceReservationDto);
        assertEquals("maintainer", maintenanceReservationDto.getUserDto().getName());

        Machine machine = machineDao.findById("1").orElse(null);

        Date inbetween = new Date();
        inbetween.setTime(inbetween.getTime() + 10000);
        assertTrue(reservationService.checkIfMachineReserved(machine, inbetween));

        Date notInbetween = new Date();
        notInbetween.setTime(notInbetween.getTime() + 7200000);
        assertFalse(reservationService.checkIfMachineReserved(machine, notInbetween));
    }


    @Test
    public void createRepeatingReservation() {
        Employee maintainer = new Maintainer();
        maintainer.setName("maintainer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_MAINTAINER);
        maintainer.setRole(role);
        userDao.save(maintainer);

        ReserveRepeatingDto reserveRepeatingDto = new ReserveRepeatingDto();
        reserveRepeatingDto.setMachineId(1L);
        Date date = new Date();
        reserveRepeatingDto.setStartDate(date);
        Date endDate =  new Date();
        endDate.setTime(date.getTime() + 3600000);
        reserveRepeatingDto.setEndDate(endDate);
        reserveRepeatingDto.setReservationPeriodicity(ReservationPeriodicity.MONTHLY);

        RepeatingReservationDto repeatingReservationDto = reservationService.createRepeatingReservation(maintainer, reserveRepeatingDto);

        assertNotNull(repeatingReservationDto);
        assertEquals("maintainer", repeatingReservationDto.getUserDto().getName());

        Machine machine = machineDao.findById("1").orElse(null);

        Date inbetween = new Date();
        inbetween.setTime(inbetween.getTime() + 10000);
        assertTrue(reservationService.checkIfMachineReserved(machine, inbetween));

        Date notInbetween = new Date();
        notInbetween.setTime(notInbetween.getTime() + 7200000);
        assertFalse(reservationService.checkIfMachineReserved(machine, notInbetween));
    }

    @Test
    public void cancelRepeatingReservation() {
        Employee maintainer = new Maintainer();
        maintainer.setName("maintainer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_MAINTAINER);
        maintainer.setRole(role);
        userDao.save(maintainer);

        ReserveRepeatingDto reserveRepeatingDto = new ReserveRepeatingDto();
        reserveRepeatingDto.setMachineId(1L);
        Date date = new Date();
        reserveRepeatingDto.setStartDate(date);
        Date endDate =  new Date();
        endDate.setTime(date.getTime() + 3600000);
        reserveRepeatingDto.setEndDate(endDate);
        reserveRepeatingDto.setReservationPeriodicity(ReservationPeriodicity.MONTHLY);

        RepeatingReservationDto repeatingReservationDto = reservationService.createRepeatingReservation(maintainer, reserveRepeatingDto);

        assertNotNull(repeatingReservationDto);
        assertEquals("maintainer", repeatingReservationDto.getUserDto().getName());

        Reservation reservation = reservationDao.findById(repeatingReservationDto.getId()).orElse(null);
        assertNotNull(reservation);

        reservation.setCancelled(true);
        reservationDao.save(reservation);

        Machine machine = machineDao.findById("1").orElse(null);

        Date inbetween = new Date();
        inbetween.setTime(inbetween.getTime() + 10000);
        assertFalse(reservationService.checkIfMachineReserved(machine, inbetween));

        Date notInbetween = new Date();
        notInbetween.setTime(notInbetween.getTime() + 7200000);
        assertFalse(reservationService.checkIfMachineReserved(machine, notInbetween));
    }

    @Test
    public void shouldntCreateSecondReservation() {
        Customer customer = new Customer();
        customer.setName("customer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_CUSTOMER);
        customer.setRole(role);
        userDao.save(customer);

        ReserveOneTimeDto reserveOneTimeDto1 = new ReserveOneTimeDto();
        reserveOneTimeDto1.setMachineId(1L);

        Date date = new Date();
        reserveOneTimeDto1.setStartDate(date);
        Date endDate =  new Date();
        endDate.setTime(date.getTime() + 3600000);
        reserveOneTimeDto1.setEndDate(endDate);

        OneTimeReservationDto oneTimeReservationDto = reservationService.createOneTimeReservation(customer, reserveOneTimeDto1);

        ReserveOneTimeDto reserveOneTimeDto2 = new ReserveOneTimeDto();
        reserveOneTimeDto2.setMachineId(1L);

        date = new Date();
        reserveOneTimeDto2.setStartDate(date);
        endDate =  new Date();
        endDate.setTime(date.getTime() + 3600000);
        reserveOneTimeDto2.setEndDate(endDate);

        ServiceException exception = assertThrows(ServiceException.class, () -> {
            reservationService.createOneTimeReservation(customer, reserveOneTimeDto2);
        });
        assertEquals("Machine already reserved at this date", exception.getMessage());
    }
}
