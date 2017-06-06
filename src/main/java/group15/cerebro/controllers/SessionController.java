package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Usr;
import group15.cerebro.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/session")
@Scope("session")
public class SessionController {
    private SessionManager manager;

    @Autowired
    public SessionController(SessionManager manager) {
        this.manager = manager;
    }

    // Returns the session token and it starts the game
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void startSession(@RequestBody Usr usr) {
        MainApplication.logger.warn(usr.getLogin());
        MainApplication.logger.warn(usr.getPassword());
        manager.makeNewSession(usr);
    }
}
