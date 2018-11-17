package euteam.cleanmachine.Controllers;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.dto.UserSignInDto;
import euteam.cleanmachine.dto.UserSignUpDto;
import euteam.cleanmachine.model.enums.RoleName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.google.gson.Gson;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserDao userDao;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void addUserSuccessfully() throws  Exception{
        UserSignUpDto userSignUpDto = new UserSignUpDto();
        userSignUpDto.setUsername("user");
        userSignUpDto.setPassword("password");
        userSignUpDto.setEmail("usr@mail.com");
        userSignUpDto.setName("aName");
        userSignUpDto.setRoleName(RoleName.ROLE_CUSTOMER);

        Gson gson = new Gson();
        String json = gson.toJson(userSignUpDto, UserSignUpDto.class);


        mvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("aName"));
    }
}
