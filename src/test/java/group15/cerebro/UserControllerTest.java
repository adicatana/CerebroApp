package group15.cerebro;

/**
 * Created by andrei-octavian on 02.06.2017.
 */

import group15.cerebro.controllers.UserController;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserRepository repo = Mockito.mock(UserRepository.class);
    private UserController ctrl = new UserController(repo);

    @Before
    public void setUp(){
        List<Usr> users = new ArrayList<>();
        Usr user = new Usr();
        user.setLogin("adi");
        user.setEmail("adi@mail.com");
        users.add(user);
        when(repo.findAll()).thenReturn(users);
    }

    @Test
    public void getUserDetailsTest() {
        List<Usr> users = ctrl.getAll();
        assertEquals(users.get(0).getLogin(), "adi");
        assertEquals(users.get(0).getEmail(), "adi@mail.com");
    }


}
