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

        MainApplication.log(match, manager, manager.getUserForSession().getName() + " joined the room");
        sessionPool.join(manager.getUserForSession());
    }

    @RequestMapping(value = "/match", method = RequestMethod.GET)
    public Match match() throws InterruptedException {
        match = sessionPool.match(manager.getUserForSession());
        if (match != null) {
            MainApplication.log(match, manager, "Match made: player1:" + match.getPlayer1().getName() +
                    "player2:" + match.getPlayer2().getName());
        } else {
            MainApplication.log(match, manager, "No match was made.");
        }
        return match;
    }

    @RequestMapping(value = "/connection_lost", method = RequestMethod.GET)
    public void lostConnection() {
        MainApplication.log(match, manager, "Lost connection");
        manager.lostConnection();

        // unblocks match object so it is allowed to be garbage collected
        match.unblock();
        match = null; // Allows match to be garbage collected
    }

    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public void endMultiplayer() {
        MainApplication.log(match, manager, "Ending game.");
        manager.returnMainScreenMultiplayerGame();
        match = null; // Allows match to be garbage collected
    }

    // should send back the question shuffled
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Question random() {
        // If there are no more questions remaining we return null to the front-end
        if (match.getRemainingQuestions() <= 0) {
            MainApplication.log(match, manager, "Finished game");
            manager.endMultiplayerGame();
            return null;
        }

        match.checkNullSetQuestion(getRandomQuestion());
        MainApplication.log(match, manager, "Question:" + match.getQuestion().getQuestion());

        // Each player does play
        match.play();

        return match.getQuestionRandomized();
    }

    // Will be called only when Cancel button is pressed.
    @RequestMapping(value = "/exit-room", method = RequestMethod.GET)
    public void exitRoom(){
        MainApplication.log(match, manager, "Ending game.");
        sessionPool.dismiss(manager.getUserForSession());

        // Cleans the reference to match.
        if (match != null) {
            match.unblock();
            match = null;
        }
        manager.returnMainScreenMultiplayerGame();
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public void nextQuestionSync() throws InterruptedException {
        match.next(manager.getUserForSession());

        // They both synchronize on next, so we need to reset it after the sync. is done.
        match.setQuestion(null);
    }

    // should send back the question un-shuffled
    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public Question getAnswer(@RequestBody String chosen) {
        MainApplication.log(match, manager, "Player " + manager.getUserForSession().getName()
                + " responded " + chosen);
        match.verify(manager.getUserForSession(), chosen);
        return match.getQuestion();
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
