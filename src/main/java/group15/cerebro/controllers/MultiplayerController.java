package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.session.multi.Match;
import group15.cerebro.session.multi.SessionPool;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/multi")
@Scope("session")
public class MultiplayerController {
    private Match match;
    private SessionManagerEngine manager;
    private SessionPool sessionPool = MainApplication.pool;
    private QuestionRepository questionRepository;

    @Autowired
    public MultiplayerController(SessionManagerEngine manager, QuestionRepository questionRepository) {
        this.manager = manager;
        this.match = null;
        this.questionRepository = questionRepository;
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

    // Sync. mechanism.
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public boolean ping() {
        return match != null && match.ping(manager.getUserForSession());
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Question random() {
        if (match.getQuestion() == null) {
            match.setQuestion(getRandomQuestion());
        }
        MainApplication.logger.info("Question:" + match.getQuestion().getQuestion());
        return match.getQuestion();
    }

    

    private Question getRandomQuestion() {
        List<Question> all = getAll();
        int index = ThreadLocalRandom.current().nextInt(0, all.size());
        return all.get(index);
    }

    private List<Question> getAll() {
        return questionRepository.findAll();
    }
}
