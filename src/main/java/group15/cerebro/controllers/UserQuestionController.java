package group15.cerebro.controllers;

import group15.cerebro.repositories.UserQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userquest")
public class UserQuestionController {

    UserQuestionRepository userQuestionRepository;

    @Autowired
    public UserQuestionController(UserQuestionRepository userQuestionRepository) {
        this.userQuestionRepository = userQuestionRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll() {
        return "la misto";
    }
}