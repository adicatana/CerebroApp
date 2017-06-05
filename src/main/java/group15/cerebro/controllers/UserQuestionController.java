package group15.cerebro.controllers;

import group15.cerebro.entities.UserQuestion;
import group15.cerebro.repositories.UserQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/userquest")
@Scope("session")
public class UserQuestionController {

    UserQuestionRepository userQuestionRepository;

    @Autowired
    public UserQuestionController(UserQuestionRepository userQuestionRepository) {
        this.userQuestionRepository = userQuestionRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserQuestion> getAll() {
        return userQuestionRepository.findAll();
    }
}