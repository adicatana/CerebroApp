package group15.cerebro.controllers;

import group15.cerebro.entities.Question;
import group15.cerebro.entities.Topic;
import group15.cerebro.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

}
