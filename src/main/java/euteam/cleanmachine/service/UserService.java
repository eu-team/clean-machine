package euteam.cleanmachine.service;

import java.util.List;

import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao repository;

    public List<User> findAll() {

        List<User> users = (List<User>) repository.findAll();

        return users;
    }

    public User addUser(User user) {
        return repository.save(user);
    }
}