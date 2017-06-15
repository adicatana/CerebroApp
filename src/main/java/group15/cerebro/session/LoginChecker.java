package group15.cerebro.session;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoginChecker {
    private UserRepository userRepository;

    @Autowired
    public LoginChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usr findUser(String login) {
        return userRepository.findUsrByLogin(login);
    }
}
