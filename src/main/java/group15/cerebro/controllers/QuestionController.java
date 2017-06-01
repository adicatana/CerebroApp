package group15.cerebro.controllers;

import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController {

    QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Question> getAll() {
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