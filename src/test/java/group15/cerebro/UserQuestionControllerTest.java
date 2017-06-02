package group15.cerebro;

import group15.cerebro.controllers.UserQuestionController;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.UserQuestion;
import group15.cerebro.entities.Topic;
import group15.cerebro.repositories.UserQuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserQuestionControllerTest {

    private UserQuestionRepository repo = Mockito.mock(UserQuestionRepository.class);
    private UserQuestionController ctrl = new UserQuestionController(repo);

    @Before
    public void setUp(){
        List<UserQuestion> userQuestions = new ArrayList<>();
        UserQuestion userQuestion = new UserQuestion();
        Question question = new Question();
        question.setQuestion("Will the demo work?");
        question.setAnswer("Of course.");
        question.setWrong1("It will fail.");
        question.setWrong2("No, it will not work.");
        Topic topic = new Topic();
        topic.setTopicname("WebApps");
        question.setTopic(topic);

        userQuestion.setQuestion(question);
        userQuestion.setLastattempt(true);
        userQuestion.setId(5);

        userQuestions.add(userQuestion);
        when(repo.findAll()).thenReturn(userQuestions);
    }

    @Test
    public void getLoginNameTest() {
        List<UserQuestion> userQuestions = ctrl.getAll();
        assertEquals(userQuestions.get(0).getQuestion().getQuestion(), "Will the demo work?");
        assertEquals(userQuestions.get(0).getId(), 5);
    }


}