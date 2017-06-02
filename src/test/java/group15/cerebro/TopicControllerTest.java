package group15.cerebro;

import group15.cerebro.controllers.TopicController;
import group15.cerebro.entities.Topic;
import group15.cerebro.repositories.TopicRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TopicControllerTest {

    private TopicRepository repo = Mockito.mock(TopicRepository.class);
    private TopicController ctrl = new TopicController(repo);

    @Before
    public void setUp(){
        List<Topic> topics = new ArrayList<>();
        Topic topic = new Topic();

        topic.setId(5);
        topic.setTopicname("networks");
        topics.add(topic);

        when(repo.findAll()).thenReturn(topics);
    }

    @Test
    public void getTopicsFieldsFindAllTest() {
        List<Topic> topics = ctrl.getAll();
        assertEquals(topics.get(0).getId(), 5);
        assertEquals(topics.get(0).getTopicname(), "networks");
    }


}