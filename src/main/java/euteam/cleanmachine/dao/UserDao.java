package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.user.AuthItem;
import euteam.cleanmachine.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getUserByAuthItemListContains(AuthItem authItem);
}
