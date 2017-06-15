package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.session.multi.Match;
import group15.cerebro.session.multi.SessionPool;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Match match() throws InterruptedException {
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

    @RequestMapping(value = "/connection_lost", method = RequestMethod.GET)
    public void lostConnection() {
        MainApplication.logger.info("Lost connection");
        manager.lostConnection();

        // TODO: THE OTHER SESSION REMAINS HANGING IF THE INTERNET CONNECTION FOR OPPONENT
        // TODO: HAS BEEN LOST
        // unblocks match object so it is allowed to be garbage collected
        match.unblock();
        match = null; // Allows match to be garbage collected
    }

    // Sync. mechanism.
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public boolean ping() {
        MainApplication.logger.info(manager.getUserForSession().getName() + " sync");
        return match != null && match.ping(manager.getUserForSession());
    }

    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public void endMultiplayer() {
        MainApplication.logger.info("Ending game.");
        manager.returnMainScreenMultiplayerGame();
        match = null; // Allows match to be garbage collected
    }

    // should send back the question shuffled
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Question random() {
        // If there are no more questions remaining we return null to the front-end
        if (match.getRemainingQuestions() == 0) {
            MainApplication.logger.info("Finished game");
            manager.endMultiplayerGame();
            return null;
        }

        match.checkNullSetQuestion(getRandomQuestion());
        MainApplication.logger.info("Question:" + match.getQuestion().getQuestion());

        // Each player does play
        match.play();

        return match.getQuestionRandomized();
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public void nextQuestionSync() throws InterruptedException {
        match.next();

        // They both synchronize on next, so we need to reset it after the sync. is done.
        match.setQuestion(null);
    }

    // should send back the question un-shuffled
    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public Question getAnswer(@RequestBody String chosen) {
        MainApplication.logger.info("Player " + manager.getUserForSession().getName()
                + " responded " + chosen);
        match.verify(manager.getUserForSession(), chosen);
        return match.getQuestion();
    }

    @RequestMapping(value = "/lost", method = RequestMethod.GET)
    public void connectionLost() {
        // TODO!!!!!
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public Match stats() {
        return match;
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
