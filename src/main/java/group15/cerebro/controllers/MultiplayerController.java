package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.session.multi.Match;
import group15.cerebro.session.multi.SessionPool;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/multi")
@Scope("session")
public class MultiplayerController {
    private Match match;
    private SessionManagerEngine manager;
    private SessionPool sessionPool = MainApplication.pool;

    @Autowired
    public MultiplayerController(SessionManagerEngine manager) {
        this.manager = manager;
        this.match = null;
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public void join() {
        manager.multiplayerJoin();

        MainApplication.logger.info(manager.getUserForSession().getName() + " joined the room");
        sessionPool.join(manager.getUserForSession());
    }

    @RequestMapping(value = "/match", method = RequestMethod.GET)
    public Match match() {
        match = sessionPool.match(manager.getUserForSession());
        if (match != null) {
            MainApplication.logger.info("Match made!");
            MainApplication.logger.info("Player1:" + match.getPlayer1().getName());
            MainApplication.logger.info("Player2:" + match.getPlayer2().getName());
        } else {
            MainApplication.logger.info("No match was made.");
        }
        return match;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public boolean ping() {
        return match != null && match.ping(manager.getUserForSession());
    }
}
