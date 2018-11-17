package euteam.cleanmachine.Services;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.dao.AccountDao;
import euteam.cleanmachine.dao.MachineDao;
import euteam.cleanmachine.dao.ReservationDao;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.dto.MaintenanceReservationDto;
import euteam.cleanmachine.dto.OneTimeReservationDto;
import euteam.cleanmachine.dto.ReserveMaintenanceDto;
import euteam.cleanmachine.dto.ReserveOneTimeDto;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.reservation.OneTimeReservation;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.Employee;
import euteam.cleanmachine.model.user.Maintainer;
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
        ReserveOneTimeDto reserveOneTimeDto = new ReserveOneTimeDto();
        reserveOneTimeDto.setMachineId(1L);
        reserveOneTimeDto.setReservationDate(new Date());

        OneTimeReservationDto oneTimeReservationDto = reservationService.createOneTimeReservation(customer, reserveOneTimeDto);

        assertNotNull(oneTimeReservationDto);
        assertEquals("customer", oneTimeReservationDto.getCustomer().getName());
    }

    @Test
    public void createMaintenanceReservation() {
        Employee maintainer = new Maintainer();
        maintainer.setName("maintainer");
        ReserveMaintenanceDto reserveMaintenanceDto = new ReserveMaintenanceDto();
        reserveMaintenanceDto.setMachineId(1L);
        reserveMaintenanceDto.setStartDate(new Date());
        reserveMaintenanceDto.setEndDate(new Date());

        MaintenanceReservationDto maintenanceReservationDto = reservationService.createMaintenanceReservation(maintainer, reserveMaintenanceDto);

        assertNotNull(maintenanceReservationDto);
        assertEquals("maintainer", maintenanceReservationDto.getEmployee().getName());
    }
}
