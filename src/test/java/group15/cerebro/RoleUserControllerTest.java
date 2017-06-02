package group15.cerebro;

/**
 * Created by andrei-octavian on 02.06.2017.
 */

import group15.cerebro.controllers.RoleUserController;
import group15.cerebro.entities.Role;
import group15.cerebro.entities.RoleUser;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.RoleUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RoleUserControllerTest {

    private RoleUserRepository repo = Mockito.mock(RoleUserRepository.class);
    private RoleUserController ctrl = new RoleUserController(repo);

    @Before
    public void setUp(){
        List<RoleUser> roleUsers = new ArrayList<>();
        Role role = new Role();
        Usr user = new Usr();

        role.setRole("admin");
        user.setLogin("adi");

        roleUsers.add(createRoleUser(role, user));
        when(repo.findAll()).thenReturn(roleUsers);
    }

    @Test
    public void getLoginNameTest() {
        List<RoleUser> roleUsers = ctrl.getAll();
        assertEquals(roleUsers.get(0).getRole().getRole(), "admin");
        assertEquals(roleUsers.get(0).getUser().getLogin(), "adi");
    }

    private RoleUser createRoleUser(Role role, Usr user) {
        RoleUser roleXuser = new RoleUser();
        roleXuser.setId(1);
        roleXuser.setRoleid(role);
        roleXuser.setUserid(user);
        return roleXuser;
    }


}