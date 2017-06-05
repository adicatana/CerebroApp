package group15.cerebro;

import group15.cerebro.controllers.QuestionController;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.Topic;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class QuestionControllerTest {

    private QuestionRepository repo = Mockito.mock(QuestionRepository.class);
    private SessionManager manager = Mockito.mock(SessionManager.class);
    private QuestionController ctrl = new QuestionController(repo, manager);

    @Before
    public void setUp(){
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestion("Will the demo work?");
        question.setAnswer("Of course.");
        question.setWrong1("It will fail.");
        question.setWrong2("No, it will not work.");
        Topic topic = new Topic();
        topic.setTopicname("WebApps");
        question.setTopic(topic);
        questions.add(question);
        when(repo.findAll()).thenReturn(questions);
    }

    @Test
    public void getLoginNameTest() {
        List<Question> questions = ctrl.getAll();
        assertEquals(questions.get(0).getQuestion(), "Will the demo work?");
        assertEquals(questions.get(0).getAnswer(), "Of course.");
    }


}