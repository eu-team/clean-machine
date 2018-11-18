package euteam.cleanmachine.service;

import java.util.ArrayList;
import java.util.List;

import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.dto.UserSignUpDto;
import euteam.cleanmachine.exceptions.ServiceException;
import euteam.cleanmachine.model.user.*;
import euteam.cleanmachine.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthItemService authItemService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {

        List<User> users = (List<User>) repository.findAll();

        return users;
    }

    public UserDto addUser(UserSignUpDto userSignUpDto) throws ServiceException{
        User user;

        switch (userSignUpDto.getRoleName()) {
            case ROLE_CUSTOMER:
                user = new Customer();
                break;
            case ROLE_MAINTAINER:
                user = new Maintainer();
                break;
            case ROLE_ADMINISTRATOR:
                user = new Administrator();
                break;

            default:
                throw new ServiceException("Role does not exist");

        }

        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        user.setName(userSignUpDto.getName());
        user.setUsername(userSignUpDto.getUsername());
        user.setEmail(userSignUpDto.getEmail());

        Role role = roleService.getRoleByRoleName(userSignUpDto.getRoleName());

        if(role == null) {
            throw new ServiceException("Role not found");
        }

        role.setRoleName(userSignUpDto.getRoleName());
        user.setRole(role);
        user.setAuthItemList(new ArrayList<>());
        User userSaved = repository.save(user);

        Account account = new Account(userSaved);
        UserDto userDto = new UserDto(userSaved);
        accountService.addAccount(account);

        return userDto;
    }

    public User getUserByID(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByAuthId(Long authId){
        AuthItem authItem = authItemService.getAuthItem(authId);

        try {
            return repository.getUserByAuthItemListContains(authItem);
        }catch (Exception e){
            return null;
        }
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void update(User u) {
        repository.save(u);
    }

    public boolean doesUserExist(Long authItemToken) {
       return getUserByAuthId(authItemToken)!=null;
    }

    public Long getUserIdByAuthId(Long authItemToken) {
        if(getUserByAuthId(authItemToken)==null)return null;
        return getUserByAuthId(authItemToken).getId();
    }
}