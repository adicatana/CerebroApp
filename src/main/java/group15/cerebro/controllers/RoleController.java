package group15.cerebro.controllers;

import group15.cerebro.entities.Question;
import group15.cerebro.entities.Role;
import group15.cerebro.repositories.RoleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
