package group15.cerebro.controllers;

import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.repositories.UserRepository;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by adicatana on 20.06.2017.
 */
@RestController
@RequestMapping(value = "/admin")
@Scope("session")
public class AdminController {

    private QuestionRepository questionRepository;
    private SessionManagerEngine manager;

    @Autowired
    public AdminController(QuestionRepository questionRepository, SessionManagerEngine manager) {
        this.questionRepository = questionRepository;
        this.manager = manager;
    }

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public void enter() {
        if (!manager.getUserForSession().isPulete()) {
            manager.enterAdmin();
        }
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public void exit() {
        manager.exitAdmin();
    }

    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public List<Question> getPendingQuestions() {
        return questionRepository.findQuestionsByValid(false);
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public void receiveAdminApprovals(@RequestBody List<Question> modifiedQuestions) {
        questionRepository.save(modifiedQuestions);
    }

}
