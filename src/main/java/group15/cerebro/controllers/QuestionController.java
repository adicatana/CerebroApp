package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.entities.Question;
import group15.cerebro.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Question> getAll() {
        MainApplication.logger.info(" MY LOGGER : Get questions");
        MainApplication.logger.warn(manager.getUid().toString());
        return questionRepository.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<Question> create(@RequestBody Question question) {
        questionRepository.save(question);
        return questionRepository.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public List<Question> remove(@PathVariable long id){
        questionRepository.delete(id);
        return questionRepository.findAll();

    }

}