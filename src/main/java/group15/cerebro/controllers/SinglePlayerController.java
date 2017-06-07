package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.Topic;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.repositories.TopicRepository;
import group15.cerebro.session.models.Game;
import group15.cerebro.session.templates.GameEngine;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/singleplayer")
@Scope("session")
public class SinglePlayerController {

    private QuestionRepository questionRepository;
    private TopicRepository topicRepository;
    private SessionManagerEngine manager;
    private GameEngine game;
    private Topic gameTopic;


    public SinglePlayerController(QuestionRepository questionRepository, TopicRepository topicRepository,
                                  SessionManagerEngine manager, GameEngine game) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
        this.manager = manager;
        this.game = game;
    }

    @Autowired
    public SinglePlayerController(QuestionRepository questionRepository, TopicRepository topicRepository, SessionManagerEngine manager) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
        this.manager = manager;
        this.game = new Game();
    }

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public void topicSelection() {
        MainApplication.logger.warn("Topic selection stage");

        manager.selectTopic();
    }

    @RequestMapping(value = "/single/{topic}", method = RequestMethod.GET)
    public void startGame(@PathVariable Long topic) {
        MainApplication.logger.warn("Starting single game");
        MainApplication.logger.warn("The topic is" + topic);
        gameTopic = topicRepository.findOne(topic);

        game = new Game();
        manager.startNewGame();
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
        } else {
            manager.finishGame();
        }
        return null;
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET, produces="text/plain")
    public String getAnswer() {
        MainApplication.logger.info(" MY LOGGER : Percent: " + game.getPercent());
        return "" + game.getPercent();
    }

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public Question getAnswer(@RequestBody String chosen) {
        MainApplication.logger.info(" MY LOGGER : Responding: " + chosen);
        MainApplication.logger.info(" MY LOGGER : Correct response: " + game.getAnswer());

        game.respond(chosen);

        return game.getQuestion();
    }

    private Question getRandomQuestion() {
        List<Question> all = getAll();
        int index = ThreadLocalRandom.current().nextInt(0, all.size());
        return all.get(index);
    }

    private List<Question> getAll() {
        if(gameTopic != null) {
            return questionRepository.findQuestionsByTopic(gameTopic);
        }
        return questionRepository.findAll();
    }
}