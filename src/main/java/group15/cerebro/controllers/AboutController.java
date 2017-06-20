package group15.cerebro.controllers;

import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by adicatana on 20.06.2017.
 */

@RestController
@RequestMapping(value = "/about")
@Scope("session")
public class AboutController {

    private SessionManagerEngine manager;

    @Autowired
    public AboutController(SessionManagerEngine manager) {
        this.manager = manager;
    }

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public void enter() {
        manager.enterAbout();
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public void exit() {
        manager.exitAbout();
    }

}
