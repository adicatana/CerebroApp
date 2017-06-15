package group15.cerebro.repositories;

import group15.cerebro.entities.Question;
import group15.cerebro.entities.Topic;
import group15.cerebro.entities.UserQuestion;
import group15.cerebro.entities.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by adi_c on 01/06/2017.
 */
public interface UserQuestionRepository extends JpaRepository<UserQuestion, Long> {

    UserQuestion findUserQuestionByUserIdAndQuestion(Usr user, Question question);
    List<UserQuestion> findUserQuestionsByUserId(Usr user);


}
