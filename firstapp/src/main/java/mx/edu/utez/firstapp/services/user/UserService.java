package mx.edu.utez.firstapp.services.user;

import mx.edu.utez.firstapp.models.user.User;
import mx.edu.utez.firstapp.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public User getUserByUsername (String username){
        return repository.findOneByUsername(username);
    }

}
