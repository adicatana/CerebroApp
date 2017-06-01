package group15.cerebro.controllers;


import group15.cerebro.entities.Question;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Usr> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<Usr> create(@RequestBody Usr usr) {
        userRepository.save(usr);
        return userRepository.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public List<Usr> remove(@PathVariable long id){
        userRepository.delete(id);
        return userRepository.findAll();

    }

}
