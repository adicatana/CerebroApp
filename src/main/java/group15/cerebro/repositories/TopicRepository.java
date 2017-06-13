package group15.cerebro.repositories;

import group15.cerebro.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    default Topic findByTopicName(String topicName) {
        List<Topic> topics = findAll();
        for(Topic topic : topics) {
            if(topic.getTopicname().equals(topicName)) {
                return topic;
            }
        }
        return null;
    }
}
