package group15.cerebro.controllers;

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import group15.cerebro.session.templates.SessionManagerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rankings")
@Scope("session")
public class RankingsController {

    private UserRepository userRepository;
    private SessionManagerEngine manager;

    @Autowired
    public RankingsController(UserRepository userRepository, SessionManagerEngine manager){
        this.userRepository = userRepository;
        this.manager = manager;
    }

    @RequestMapping(value = "/all/sorted", method = RequestMethod.GET)
    public List<Usr> getAll() {
        List<Usr> ans = userRepository.findAll();
        ans.sort((o1, o2) -> o1.getRating() == o2.getRating() ? 0 :
                o1.getRating() > o2.getRating() ? -1 : 1);
        return ans;
    }

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public void enter() {
        manager.enterRankings();
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public void leave() {
        manager.leaveRankings();
    }

}
