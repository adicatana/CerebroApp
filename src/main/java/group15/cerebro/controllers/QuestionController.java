package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.session.Game;
import group15.cerebro.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/questions")
@Scope("session")
public class QuestionController {

    private QuestionRepository questionRepository;
    private SessionManager manager;
    private Game game;

    @Autowired
    public QuestionController(QuestionRepository questionRepository, SessionManager manager) {
        this.questionRepository = questionRepository;
        this.manager = manager;
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public void startGame() {
        MainApplication.logger.warn("Starting single game");
        manager.startNewGame();

        if (game == null) {
            game = new Game();
        } else {
            // Game restarted abruptly
        }
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Question getRandom() {
        MainApplication.logger.info(" MY LOGGER : Get questions");
        MainApplication.logger.warn(manager.getUid().toString());

        if (manager.getPhase() == SessionManager.Phase.SINGLE
                && game.getGames() > 0) {
            game.play();
            game.setQuestion(getRandomQuestion());
            return game.genRandomOrder();
        }
        return null;
    }

    @RequestMapping(value = "/answer/{answer}", method = RequestMethod.GET)
    public Question getAnswer(@PathVariable("answer") String response) {
        MainApplication.logger.info(" MY LOGGER : Responding: " + response);
        MainApplication.logger.info(" MY LOGGER : Correct response: " + game.getAnswer());

        game.respond(response);

        return game.getQuestion();
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