package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.AccountDao;
import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

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
}
