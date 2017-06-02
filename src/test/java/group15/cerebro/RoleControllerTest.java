package group15.cerebro;

import group15.cerebro.controllers.RoleController;
import group15.cerebro.entities.Role;
import group15.cerebro.repositories.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by andrei-octavian on 02.06.2017.
 */
public class RoleControllerTest {

    private RoleRepository repo = Mockito.mock(RoleRepository.class);
    private RoleController ctrl = new RoleController(repo);

    @Before
    public void setup(){
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole("admin");
        roles.add(role);
        when(repo.findAll()).thenReturn(roles);
    }

    @Test
    public void getAllRolesTest() {
        List<Role> roles = ctrl.getAll();
        assertEquals(roles.get(0).getRole(), "admin");
    }


}
