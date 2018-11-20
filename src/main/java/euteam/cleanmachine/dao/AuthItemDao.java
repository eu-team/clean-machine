package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.user.AuthItem;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthItemDao extends CrudRepository<AuthItem, Long> {

    AuthItem getAuthItemById(Long id);

}
