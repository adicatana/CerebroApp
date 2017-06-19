package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/session")
@Scope("session")
public class SessionController {
    private SessionManagerEngine manager;

    @Autowired
    public SessionController(SessionManagerEngine manager) {
        this.manager = manager;
    }

    @RequestMapping(value = "/start/{user_id}", method = RequestMethod.GET)
    public String startSession(@PathVariable("user_id") String id) {
        manager.makeNewSession(id);
        return null;
    }

    @RequestMapping(value = "/phase", method = RequestMethod.GET, produces="text/plain")
    public String getPhase() {
        MainApplication.log(manager, "Phase is now :" + manager.getPhase());
        return manager.getPhaseName(manager.getPhase());
    }
}
