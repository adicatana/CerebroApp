package group15.cerebro.repositories;

import group15.cerebro.entities.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Usr, Long> {

    Usr findUsrByLogin(String login);

}
