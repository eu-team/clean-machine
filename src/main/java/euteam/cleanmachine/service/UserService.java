package euteam.cleanmachine.service;

import java.util.List;

import euteam.cleanmachine.dto.UserDto;
import euteam.cleanmachine.dto.UserSignUpDto;
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
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {

        List<User> users = (List<User>) repository.findAll();

        return users;
    }

    public UserDto addUser(UserSignUpDto userSignUpDto) {

        User user;

        switch (userSignUpDto.getRole()) {
            case CUSTOMER:
                user = new Customer();
                break;
            case MAINTAINER:
                user = new Maintainer();
                break;
            case ADMINSTRATOR:
                user = new Administrator();
                break;

            default: return null;

        }
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        user.setName(userSignUpDto.getName());
        user.setEmail(userSignUpDto.getEmail());
        user.setRole(userSignUpDto.getRole());
        User userSaved = repository.save(user);
        Account account = new Account();
        account.setBalance(0);
        account.setUser(userSaved);
        UserDto userDto = new UserDto();
        userDto.setId(userSaved.getId());
        userDto.setName(userSaved.getName());
        userDto.setEmail(userSaved.getEmail());
        userDto.setAuthItemList(userSaved.getAuthItemList());
        userDto.setRole(userSaved.getRole());
        accountService.addAccount(account);
        return userDto;
    }

    public User getUserByID(Long id) {
        return repository.findById(id).orElse(null);
    }
}