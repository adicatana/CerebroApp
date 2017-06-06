package group15.cerebro.security;

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

/**
 * Created by andrei-octavian on 06.06.2017.
 */
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        Usr user = new Usr();
        //UserProfile profile = connection.fetchUserProfile();
        user.setName("ABC");
                //profile.getFirstName() + profile.getLastName());
        user.setLogin("mylogin");
                //profile.getUsername());
        user.setPassword("123456");
        user.setEmail("mail@email.com");
                //profile.getEmail());
        userRepository.save(user);
        return user.getLogin();
    }
}