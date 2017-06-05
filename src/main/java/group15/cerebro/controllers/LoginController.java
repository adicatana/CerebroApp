package group15.cerebro.controllers;

import group15.cerebro.entities.Usr;
import group15.cerebro.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/session")
@Scope("session")
public class LoginController {
    private SessionManager manager;
    static private Logger LOGGER;

    @Autowired
    public LoginController(SessionManager manager) {
        this.manager = manager;
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void startSession(@RequestBody Usr usr)
    {
        LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!! LOGIN: " + usr.getLogin());
        manager.makeNewSession();
    }
}
