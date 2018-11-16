package euteam.cleanmachine.Services;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.commands.Authenticate;
import euteam.cleanmachine.commands.MachineCommand;
import euteam.cleanmachine.model.facility.Machine;
import euteam.cleanmachine.model.facility.machine.state.AuthenticatedState;
import euteam.cleanmachine.model.facility.machine.state.IdleState;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.MachineService;
import euteam.cleanmachine.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MachineTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private MachineService machineService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void authenticateOnMachine() {
        User user = userService.getUserByID(100L);
        Machine machine = machineService.getMachineByIdentifier("ABC987654321");

        machine.setState(new IdleState());

        assert(machine.getState() instanceof IdleState);

        MachineCommand command = new Authenticate();
        command.execute(user, machine);

        assert(machine.getState() instanceof AuthenticatedState);
    }
}
