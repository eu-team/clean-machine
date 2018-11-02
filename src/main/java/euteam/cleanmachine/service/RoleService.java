package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.RoleDao;
import euteam.cleanmachine.model.enums.RoleName;
import euteam.cleanmachine.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao repository;

    public Role getRoleByRoleName(RoleName roleName) {
        return repository.findByRoleName(roleName);
    }

}