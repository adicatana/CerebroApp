package group15.cerebro.controllers;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Topic;
import group15.cerebro.entities.UserQuestion;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.TopicRepository;
import group15.cerebro.repositories.UserQuestionRepository;
import group15.cerebro.repositories.UserRepository;
import group15.cerebro.session.models.Progress;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrei-octavian on 07.06.2017.
 */
@RestController
@RequestMapping(value = "/profile")
@Scope("session")
public class UserProfileController {

    private SessionManagerEngine manager;
    private UserQuestionRepository userQuestionRepository;

    @Autowired
    public UserProfileController(SessionManagerEngine manager,
                                 UserQuestionRepository userQuestionRepository) {
        this.manager = manager;
        this.userQuestionRepository = userQuestionRepository;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Usr getUserProfile() {
        return manager.getUserForSession();
    }

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public void enter() {
        manager.enterProfile();
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public void leave() {
        manager.leaveProfile();
    }

    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    public List<Progress> getQuestionsPerTopic() {


        List<UserQuestion> uqs = userQuestionRepository.findUserQuestionsByUserId(manager.getUserForSession());
        HashMap<Topic, Progress> map = new HashMap<>();

        /* All user-questions mappings. */
        for (UserQuestion uq : uqs) {
            /* Look for current user's questions. */
                Topic currentTopic = uq.getQuestion().getTopic();

                Progress currentProgress = map.get(currentTopic);
                if (currentProgress == null) {
                    currentProgress = new Progress();
                    currentProgress.setTopic(currentTopic);
                    currentProgress.setTotalAnswers(currentTopic.getQuestionNo());
                    map.put(currentTopic, currentProgress);
                }

                if (uq.isLastattempt()) {
                    currentProgress.incrementRight();
                } else {
                    currentProgress.incrementWrong();
                }
        }

        return new ArrayList<>(map.values());
    }

}
