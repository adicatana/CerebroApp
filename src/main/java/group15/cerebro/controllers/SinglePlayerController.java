package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.session.models.Game;
import group15.cerebro.session.templates.GameEngine;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/singleplayer")
@Scope("session")
public class SinglePlayerController {

    private QuestionRepository questionRepository;
    private SessionManagerEngine manager;
    private GameEngine game;


    public SinglePlayerController(QuestionRepository questionRepository,
                                  SessionManagerEngine manager, GameEngine game) {
        this.questionRepository = questionRepository;
        this.manager = manager;
        this.game = game;
    }

    @Autowired
    public SinglePlayerController(QuestionRepository questionRepository, SessionManagerEngine manager) {
        this.questionRepository = questionRepository;
        this.manager = manager;
        this.game = new Game();
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public void startGame() {
        MainApplication.logger.warn("Starting single game");
        manager.startNewGame();

        //TODO: flag for game
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Question getRandom() {
        MainApplication.logger.info(" MY LOGGER : Get questions");
        MainApplication.logger.warn(manager.getUid().toString());

        if (manager.getPhase() == SessionManagerEngine.Phase.SINGLE
                && game.getGames() > 0) {
            game.play();
            game.setQuestion(getRandomQuestion());
            return game.genRandomOrder();
        }
        return null;
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET, produces="text/plain")
    public String getAnswer() {
        MainApplication.logger.info(" MY LOGGER : Percent: " + game.getPercent());
        return "" + game.getPercent();
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