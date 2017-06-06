package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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

    @Autowired
    public QuestionController(QuestionRepository questionRepository, SessionManager manager) {
        this.questionRepository = questionRepository;
        this.manager = manager;
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Question getRandom() {
        MainApplication.logger.info(" MY LOGGER : Get questions");
        MainApplication.logger.warn(manager.getUid().toString());

        if (manager.getPhase() == SessionManager.Phase.SINGLE) {
            List<Question> all = getAll();
            int index = ThreadLocalRandom.current().nextInt(0, all.size());
            return all.get(index);
        }
        return null;
    }

    private List<Question> getAll() {
        return questionRepository.findAll();
    }
}