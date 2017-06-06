package group15.cerebro.repositories;

import group15.cerebro.entities.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Usr, Long> {
    default Usr findByLogin(String login) {
        List<Usr> users = findAll();
        for(Usr usr : users) {
            if(usr.getLogin().equals(login)) {
                return usr;
            }
        }
        return null;
    }
}
