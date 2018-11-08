package euteam.cleanmachine.service;

import euteam.cleanmachine.dao.AuthItemDao;
import euteam.cleanmachine.model.user.AuthItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthItemService {

    @Autowired
    private AuthItemDao repository;

    public AuthItem getAuthItem(Long Id){
        return repository.getAuthItemById(Id);
    }

}
