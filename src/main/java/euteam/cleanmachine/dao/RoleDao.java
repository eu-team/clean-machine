package euteam.cleanmachine.dao;

import euteam.cleanmachine.model.enums.RoleName;
import euteam.cleanmachine.model.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
