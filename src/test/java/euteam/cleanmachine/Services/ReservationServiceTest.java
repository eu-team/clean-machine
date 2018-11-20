package euteam.cleanmachine.Services;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.dao.ReservationDao;
import euteam.cleanmachine.dao.RoleDao;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.dto.*;
import euteam.cleanmachine.model.enums.ReservationPeriodicity;
import euteam.cleanmachine.model.enums.RoleName;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;
import java.util.Date;

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
        ReserveOneTimeDto reserveOneTimeDto = new ReserveOneTimeDto();
        reserveOneTimeDto.setMachineId(1L);
        reserveOneTimeDto.setReservationDate(new Date());

        OneTimeReservationDto oneTimeReservationDto = reservationService.createOneTimeReservation(customer, reserveOneTimeDto);

        assertNotNull(oneTimeReservationDto);
        assertEquals("customer", oneTimeReservationDto.getUserDto().getName());
    }

    @Test
    public void createMaintenanceReservation() {
        Employee maintainer = new Maintainer();
        maintainer.setName("maintainer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_MAINTAINER);
        maintainer.setRole(role);
        ReserveMaintenanceDto reserveMaintenanceDto = new ReserveMaintenanceDto();
        reserveMaintenanceDto.setMachineId(1L);

        reserveMaintenanceDto.setStartDate(new Date());
        reserveMaintenanceDto.setEndDate(new Date());

        MaintenanceReservationDto maintenanceReservationDto = reservationService.createMaintenanceReservation(maintainer, reserveMaintenanceDto);

        assertNotNull(maintenanceReservationDto);
        assertEquals("maintainer", maintenanceReservationDto.getUserDto().getName());
    }


    @Test
    public void createRepeatingReservation() {
        Employee maintainer = new Maintainer();
        maintainer.setName("maintainer");
        Role role = roleDao.findByRoleName(RoleName.ROLE_MAINTAINER);
        maintainer.setRole(role);
        ReserveRepeatingDto reserveRepeatingDto = new ReserveRepeatingDto();
        reserveRepeatingDto.setMachineId(1L);
        reserveRepeatingDto.setReservationDate(new Date());
        reserveRepeatingDto.setReservationPeriodicity(ReservationPeriodicity.MONTHLY);

        RepeatingReservationDto repeatingReservationDto = reservationService.createRepeatingReservation(maintainer, reserveRepeatingDto);

        assertNotNull(repeatingReservationDto);
        assertEquals("maintainer", repeatingReservationDto.getUserDto().getName());
    }
}
