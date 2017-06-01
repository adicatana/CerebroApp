package group15.cerebro.repositories;

import group15.cerebro.entities.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usr, Long> {
}
