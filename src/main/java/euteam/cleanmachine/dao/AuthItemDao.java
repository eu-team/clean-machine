package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.user.AuthItem;
import org.springframework.data.repository.CrudRepository;

public interface AuthItemDao extends CrudRepository<AuthItem,Long> {

}
