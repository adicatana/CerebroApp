package group15.cerebro.controllers;

import group15.cerebro.entities.RoleUser;
import group15.cerebro.repositories.RoleUserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/roleuser")
@Scope("session")
public class RoleUserController {

    RoleUserRepository roleUserRepository;

    public RoleUserController(RoleUserRepository roleUserRepository) {
        this.roleUserRepository = roleUserRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<RoleUser> getAll() {
        return roleUserRepository.findAll();
    }

}