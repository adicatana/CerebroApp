package group15.cerebro.repositories;

import group15.cerebro.entities.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by adi_c on 01/06/2017.
 */
public interface UserQuestionRepository extends JpaRepository<UserQuestion, Long> {
}
