package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.user.Account;
import euteam.cleanmachine.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDao extends CrudRepository<Account, Long> {

    Optional<Account> findByUser(User user);

}
