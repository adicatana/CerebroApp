package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.Topic;
import group15.cerebro.entities.UserQuestion;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.repositories.TopicRepository;
import group15.cerebro.repositories.UserQuestionRepository;
import group15.cerebro.repositories.UserRepository;
import group15.cerebro.session.models.Game;
import group15.cerebro.session.models.Ranker;
import group15.cerebro.session.templates.GameEngine;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/singleplayer")
@Scope("session")
public class SinglePlayerController {

    private QuestionRepository questionRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private SessionManagerEngine manager;
    private GameEngine game;
    private Topic gameTopic;
    private UserQuestionRepository userQuestionRepository;
    private List<Question> alreadyAsked = new ArrayList<>();
    private final int gameLength = 5;


    // Add userRepository for testing
    public SinglePlayerController(QuestionRepository questionRepository, TopicRepository topicRepository,
                                  SessionManagerEngine manager, GameEngine game) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
        this.manager = manager;
        this.game = game;
    }

    @Autowired
    public SinglePlayerController(QuestionRepository questionRepository, TopicRepository topicRepository,
                                  SessionManagerEngine manager, UserRepository userRepository,
                                  UserQuestionRepository userQuestionRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.manager = manager;
        this.game = new Game();
        this.userQuestionRepository = userQuestionRepository;
    }

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public void topicSelection() {
        MainApplication.log(manager, "Topic selection stage");

        manager.selectTopic();
    }

    @RequestMapping(value = "/single/{topic}", method = RequestMethod.GET)
    public void startGame(@PathVariable Long topic) {
        MainApplication.log(manager, "Starting single game with topic " + topic);
        gameTopic = topicRepository.findOne(topic);

        game = new Game();
        manager.startNewGame();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Question getRandom() {
        MainApplication.log(manager, "Get questions");

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
    public String getPercent() {
        MainApplication.log(manager, " Percent: " + game.getPercent());
        alreadyAsked.clear();
        return "" + game.getPercent();
    }

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    public Question getAnswer(@RequestBody String chosen) {
        MainApplication.log(manager, " Responding: " +
                chosen + "; and correct response: " + game.getAnswer());

        boolean ans = false;

        if (manager != null) {
            ans = game.respond(chosen);
            (new Ranker(manager.getUserForSession(),
                    userRepository)).update(
                    ans
            );
        }

        /* Needed for user question progress in profile. */
        Question currentQuestion = game.getQuestion();
        Usr currentUser = manager.getUserForSession();

        UserQuestion userQuestion = userQuestionRepository.findUserQuestionByUserIdAndQuestion(currentUser, currentQuestion);

        if (userQuestion == null) {
            userQuestion = new UserQuestion();
            userQuestion.setQuestion(currentQuestion);
            userQuestion.setUserid(currentUser);
        }

        userQuestion.setLastattempt(ans);

        userQuestionRepository.save(userQuestion);

        return currentQuestion;
    }

    @RequestMapping(value = "/gamecount", method = RequestMethod.GET)
    public void getGamesPlayed() {
        if (manager != null) {
            Usr usr = manager.getUserForSession();
            MainApplication.log(manager, "Updating number of games for " + usr.getName());

            usr.setGamesPlayed(usr.getGamesPlayed() + 1);
            userRepository.save(usr);
        }
    }


    private Question getRandomQuestion() {
        List<Question> all = getAll();
        int index = ThreadLocalRandom.current().nextInt(0, all.size());
        Question question = all.get(index);

        /* Keep a list of already answered questions for a round. */
        if (all.size() >= gameLength)
            while (alreadyAsked.contains(question)) {
                index = ThreadLocalRandom.current().nextInt(0, all.size());
                question = all.get(index);
            }

        alreadyAsked.add(question);

        return question;
    }

    private List<Question> getAll() {
        if(gameTopic != null) {
            return questionRepository.findQuestionsByTopicAndValid(gameTopic, true);
        }
        return questionRepository.findAll();
    }
}