package group15.cerebro.controllers;

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by andrei-octavian on 07.06.2017.
 */
@RestController
@RequestMapping(value = "/profile")
@Scope("session")
public class UserProfileController {

    private SessionManagerEngine manager;
    private UserRepository userRepository;

    @Autowired
    public UserProfileController(SessionManagerEngine manager, UserRepository userRepository) {
        this.manager = manager;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Usr getUserProfile() {
        return manager.getUserForSession();
    }

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public void enter() {
        manager.enterProfile();
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public void leave() {
        manager.leaveProfile();
    }

}
