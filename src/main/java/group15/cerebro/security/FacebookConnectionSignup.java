package group15.cerebro.security;

import group15.cerebro.MainApplication;
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
        final Usr user = new Usr();
        MainApplication.logger.info(connection.createData().getAccessToken());

        user.setLogin(connection.getDisplayName());
        MainApplication.logger.info(connection.getDisplayName());
       // user.setPassword(randomAlphabetic(8));
        //userRepository.save(user);
        return user.getLogin();
    }
}