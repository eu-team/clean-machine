package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.AccountDao;
import euteam.cleanmachine.dao.SubscriptionPlanDao;
import euteam.cleanmachine.dto.AccountSubscriptionDto;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.billing.AccountSubscription;
import euteam.cleanmachine.model.billing.SubscriptionPlan;
import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private SubscriptionPlanDao subscriptionPlanDao;

    public List<Account> findAll() {

        return (List<Account>) accountDao.findAll();
    }

    public Account getAccountByID(Long id) {
        return accountDao.findById(id).orElse(null);
    }

    public Account getAccountByUser(User user) {
        return accountDao.findByUser(user).orElse(null);
    }

    public Account addAccount(Account account) {
        return accountDao.save(account);
    }

    public double getUserBalance(User user) {
        Account account = accountDao.findByUser(user).orElse(null);
        if (account != null) return account.getBalance();
        return 0;
    }

    /**
     * Create a new subscription for an account to the specified subscription plan
     * @param accountId id of the account subscribing
     * @param subscriptionPlanId id of the plan to subscribe to
     * @return AccountSubscriptionDto the created subscription
     * @throws ServiceException
     */
    public AccountSubscriptionDto subscribeToPlan(Long accountId, Long subscriptionPlanId) throws ServiceException {
        Account account = accountDao.findById(accountId).orElse(null);
        SubscriptionPlan subscriptionPlan = subscriptionPlanDao.findById(subscriptionPlanId).orElse(null);

        if(account == null) {
            throw new ServiceException("Account not found");
        }
        if(subscriptionPlan == null) {
            throw new ServiceException("Subscription plan not found");
        }

        if(this.accountHasSubscriptionPlanId(account, subscriptionPlanId)) {
            throw new ServiceException("Already subscribed to plan");
        }

        AccountSubscription accountSubscription = new AccountSubscription();
        accountSubscription.setStartDate(new Date());
        accountSubscription.setSubscriptionPlan(subscriptionPlan);

        account.getSubscriptions().add(accountSubscription);

        account = accountDao.save(account);

        return new AccountSubscriptionDto(account.getSubscriptions().get(account.getSubscriptions().size() - 1));
    }

    private boolean accountHasSubscriptionPlanId(Account account, Long subscriptionPlanId) {
        for(AccountSubscription existingAccountSubscription : account.getSubscriptions()) {
            if (existingAccountSubscription.getSubscriptionPlan().getId() == subscriptionPlanId) {
                return true;
            }
        }
        return false;
    }
}
