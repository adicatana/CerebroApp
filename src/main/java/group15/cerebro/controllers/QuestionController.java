package group15.cerebro.controllers;

import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.entities.Question;
import group15.cerebro.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/questions")
@Scope("session")
public class QuestionController {

    private SessionManager manager;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository, SessionManager manager) {
        this.questionRepository = questionRepository;
        this.manager = manager;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Question> getAll() {
        if (manager.getPhase() == SessionManager.Phase.LOGGED) {
            return new ArrayList<Question>();
        }
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