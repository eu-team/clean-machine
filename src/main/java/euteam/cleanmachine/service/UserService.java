package euteam.cleanmachine.service;

import java.util.ArrayList;
import java.util.List;

import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.dto.UserSignUpDto;
import euteam.cleanmachine.model.user.*;
import euteam.cleanmachine.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Service
public class UserService {

    @Autowired
    private UserDao repository;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {

        List<User> users = (List<User>) repository.findAll();

        return users;
    }




    public UserDto addUser(UserSignUpDto userSignUpDto) {

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

            default: return null;

        }
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        user.setName(userSignUpDto.getName());
        user.setUsername(userSignUpDto.getUsername());
        user.setEmail(userSignUpDto.getEmail());
        Role role = roleService.getRoleByRoleName(userSignUpDto.getRoleName());
        if (role == null) return null;
        role.setRoleName(userSignUpDto.getRoleName());
        user.setRole(role);
        user.setAuthItemList(new ArrayList<>());
        User userSaved = repository.save(user);
        Account account = new Account();
        account.setBalance(0);
        account.setUser(userSaved);
        UserDto userDto = new UserDto();
        userDto.setId(userSaved.getId());
        userDto.setName(userSaved.getName());
        userDto.setEmail(userSaved.getEmail());
        userDto.setAuthItemList(userSaved.getAuthItemList());
        userDto.setRoleName(userSaved.getRole().getRoleName());
        accountService.addAccount(account);
        return userDto;
    }

    public User getUserByID(Long id) {
        return repository.findById(id).orElse(null);
    }

}