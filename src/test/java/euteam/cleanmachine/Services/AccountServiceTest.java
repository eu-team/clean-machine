package euteam.cleanmachine.Services;

import euteam.cleanmachine.CleanmachineApplication;
import euteam.cleanmachine.dao.AccountDao;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.dto.AccountSubscriptionDto;
import euteam.cleanmachine.dto.BalanceDto;
import euteam.cleanmachine.model.billing.AccountSubscription;
import euteam.cleanmachine.model.enums.SubscriptionPeriodicity;
import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.AuthItem;
import euteam.cleanmachine.model.user.Customer;
import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.service.AccountService;

import euteam.cleanmachine.service.UserService;
import javassist.NotFoundException;
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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanmachineApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountServiceTest {

    private MockMvc mvc;

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Autowired
    private WebApplicationContext context;
   
    @Autowired
    AccountService accountService;

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
    public void AccountAdded() {
        User user = new Customer();
        userDao.save(user);
        Account account = new Account();
        account.setUser(user);
        account.setBalance(0);
        Account accountSaved = accountService.addAccount(account);
        assertNotNull(accountService.getAccountByUser(user));
        assertEquals(accountService.getAccountByID(accountSaved.getId()).getId(), accountSaved.getId());
    }

    @Test
    public void subscribeToPlan() {
        AccountSubscriptionDto accountSubscriptionDto = accountService.subscribeToPlan(100L, 100L);
        Account account = accountService.getAccountByID(100L);

        assertEquals(1, account.getSubscriptions().size());

        AccountSubscription accountSubscription = account.getSubscriptions().get(0);

        assertNotNull(accountSubscriptionDto.getId());
        assertNotNull(accountSubscription.getStartDate());
        assertEquals("10 per month", accountSubscription.getSubscriptionPlan().getName());
        assertEquals(10, accountSubscription.getSubscriptionPlan().getPrice(), 0.0);
        assertEquals(SubscriptionPeriodicity.MONTHLY, accountSubscription.getSubscriptionPlan().getSubscriptionPeriodicity());
    }

    @Test
    public void getUserBalance() {
        Customer customer = new Customer();
        userDao.save(customer);

        Account account = new Account();
        account.setUser(customer);
        account.setBalance(10);
        accountDao.save(account);

        BalanceDto balanceDto = accountService.getUserBalance(customer);

        assertEquals(10, balanceDto.getBalance(), 0.0);
    }
}
