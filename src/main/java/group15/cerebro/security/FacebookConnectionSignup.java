package group15.cerebro.security;

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by andrei-octavian on 06.06.2017.
 */
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;


    private final String port = "8099"; // Check at deploy
    private final String resource = "/session/start/{user_id}";
    private final String url = "http://127.0.0.1:" + port + resource;

    @Override
    public String execute(Connection<?> connection) {
        // Does no update to the database; the update is done from the other connection
        final Usr user = new Usr();
        user.setLogin(connection.getKey().getProviderUserId());
        user.setName(connection.getDisplayName());
        user.setPassword("facebookLoginNoPassword");
        user.setEmail(UUID.randomUUID().toString());
        user.setRating(0);


        Usr foundUser = userRepository.findUsrByLogin(user.getLogin());
        if (foundUser == null) {
            userRepository.save(user);
        }

//        try {
//            RestTemplate restTemplate = new RestTemplate();
//
//            restTemplate.getForObject(url, String.class, user.getLogin().toString());
//        } catch (Exception e) {
//            MainApplication.logger.warn("Application could not start from user " + user.getName());
//        }

        return user.getLogin();
    }
}