package group15.cerebro.controllers;


import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feedback")
@Scope("session")
public class FeedbackController {
    private SessionManagerEngine manager;

    @Autowired
    public FeedbackController(SessionManagerEngine manager) {
        this.manager = manager;
    }

    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public void endFeedbackSession() {
        manager.finishFeedbackPhase();
    }
}
