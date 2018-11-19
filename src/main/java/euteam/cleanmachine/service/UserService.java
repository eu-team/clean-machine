package euteam.cleanmachine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import euteam.cleanmachine.dao.AuthItemDao;
import euteam.cleanmachine.dto.NFCDto;
import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.dto.UserSignUpDto;
import euteam.cleanmachine.model.user.*;
import euteam.cleanmachine.dao.UserDao;
import euteam.cleanmachine.model.user.AuthItem;
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
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthItemDao authDao;


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

        Account account = new Account(userSaved);
        UserDto userDto = new UserDto(userSaved);
        accountService.addAccount(account);

        return userDto;
    }

    public boolean LinkCard( String Username,NFCDto nfcDto) {
        User user = repository.findByUsername(Username);
        //get authItem from db
        Optional<AuthItem> optional =  authDao.findById(nfcDto.getId());
        if(!optional.isPresent())   return false;
        AuthItem authItem = optional.get();
        user.addAuthItem(authItem);

        //update user object in database
        repository.save(user);


       return true;


    }

    public User getUserByID(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }
}