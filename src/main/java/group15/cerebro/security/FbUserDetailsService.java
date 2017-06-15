package group15.cerebro.security;

/**
 * Created by andrei-octavian on 06.06.2017.
 */

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FbUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public FbUserDetailsService() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final Usr user = userRepository.findUsrByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}