package group15.cerebro.controllers;

import group15.cerebro.entities.Topic;
import group15.cerebro.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ioanbudea on 06/06/17.
 */
@RestController
@RequestMapping(value = "/topics")
public class TopicController {
    private TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository){
        this.topicRepository = topicRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    @RequestMapping(value = "/misc", method = RequestMethod.GET)
    public List<Topic> getMiscTopics() {

        List<Topic> allTopics = topicRepository.findAll();
        List<Topic> miscTopics = new ArrayList<>();
        for(Topic topic : allTopics) {
            if (!topic.isBook()) {
                miscTopics.add(topic);
            }
        }
        return miscTopics;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Topic> getBookTopics() {
        List<Topic> allTopics = topicRepository.findAll();
        List<Topic> miscTopics = new ArrayList<>();
        for(Topic topic : allTopics) {
            if (topic.isBook()) {
                miscTopics.add(topic);
            }
        }
        return miscTopics;
    }



}
