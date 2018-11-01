package euteam.cleanmachine.service;

import java.util.List;

import euteam.cleanmachine.model.user.User;
import euteam.cleanmachine.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> findAll() {

        List<User> users = (List<User>) repository.findAll();

        return users;
    }
    @RequestMapping(value="/users", method= RequestMethod.POST)
    public String customerSubmit(@ModelAttribute User user, Model model) {

        model.addAttribute("user",  user);
        String info = String.format("User Submission: id = %d,  Name = %s",
                user.getId(), user.getName());
        log.info(info);
        repository.save(user);

        return "result";
    }



}