package euteam.cleanmachine.Services;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.dao.AccountDao;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.AccountService;

import euteam.cleanmachine.service.UserService;
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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    private MockMvc mvc;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    AccountDao accountDao;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void UserCreated() {
        User user = new Customer();
        user.setName("test");
        user.setPassword("pwd");
        user.setEmail("test@test.com");
        UserDto userDto = userService.addUser(user);
        assertEquals(userService.getUserByID(userDto.getId()).getId(), userDto.getId());
        assertNotEquals(userService.getUserByID(userDto.getId()).getPassword(), "pwd");
        assertNotNull(accountDao.findByUser(user));
    }
}
