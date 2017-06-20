package group15.cerebro.repositories;

import group15.cerebro.entities.Question;
import group15.cerebro.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionsByTopicAndValid(Topic topic, boolean valid);
    List<Question> findQuestionsByValid(boolean valid);

}
