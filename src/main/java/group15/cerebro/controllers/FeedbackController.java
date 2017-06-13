package group15.cerebro.controllers;


import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.repositories.TopicRepository;
import group15.cerebro.session.models.UserInputProcessor;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feedback")
@Scope("session")
public class FeedbackController {
    private SessionManagerEngine manager;
    private UserInputProcessor processor;

    @Autowired
    public FeedbackController(SessionManagerEngine manager, QuestionRepository questionRepository, TopicRepository topicRepository) {
        this.manager = manager;
        this.processor = new UserInputProcessor(questionRepository, topicRepository);
    }

    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public void endFeedbackSession() {
        manager.finishFeedbackPhase();
    }

    // It is useful to know the session as we can get the user.
    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public void receiveUserFeedback(@RequestBody Question proposedQuestion) {
        processor.processInput(proposedQuestion, manager.getUserForSession());
    }
}
