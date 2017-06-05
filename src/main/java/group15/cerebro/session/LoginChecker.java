package group15.cerebro.session;

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginChecker {

    private UserRepository userRepository;
    private Usr user;

    @Autowired
    public LoginChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateAuthetication(Usr auth) {
        for (Usr usr : userRepository.findAll()) {
            if (auth.getLogin().equals(usr.getLogin())) {
                user = usr;
                return auth.getPassword().equals(usr.getPassword());
            }
        }
        return false;
    }

    public Usr getUser() {
        return user;
    }
}
