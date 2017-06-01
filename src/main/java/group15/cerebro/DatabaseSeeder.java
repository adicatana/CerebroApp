package group15.cerebro;

import group15.cerebro.entities.*;
import group15.cerebro.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private QuestionRepository questionRepository;
    private RoleRepository roleRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private RoleUserRepository roleUserRepository;
    private UserQuestionRepository userQuestionRepository;

    public DatabaseSeeder(QuestionRepository questionRepository,
                          RoleRepository roleRepository,
                          TopicRepository topicRepository,
                          UserRepository userRepository,
                          RoleUserRepository roleUserRepository,
                          UserQuestionRepository userQuestionRepository
                          ) {
        this.questionRepository = questionRepository;
        this.roleRepository = roleRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.roleUserRepository = roleUserRepository;
        this.userQuestionRepository = userQuestionRepository;
    }

    private Question makeQuestion(String question, String answer1, String wrong1, String wrong2) {
        Question quest = new Question();
        quest.setQuestion(question);
        quest.setAnswer(answer1);
        quest.setWrong1(wrong1);
        quest.setWrong2(wrong2);
        return quest;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Question> bookings = new ArrayList<>();

        bookings.add(makeQuestion(
                "Ce faci Ioan?",
                "Asta e buna",
                "Prima proasta",
             "A doua proasta"
        ));
        bookings.add(makeQuestion(
                "Cum e vremea?",
                "E da bine",
                "Sa moara bibi",
                "Ploua"
        ));
        bookings.add(makeQuestion(
                "Cum e Adi?",
                "Valoros",
                "Mare",
                "Frumos"
        ));

        questionRepository.save(bookings);


        List<Role> roles = new ArrayList<>();
        Role adm = new Role();
        adm.setRole("admin");
        Role usr = new Role();
        usr.setRole("user");
        roles.add(usr);

        roleRepository.save(roles);

        List<Topic> topics = new ArrayList<>();

        Topic t1 = new Topic();
        t1.setTopicname("networks");
        Topic t2 = new Topic();
        t2.setTopicname("os");

        topics.add(t1);
        topics.add(t2);

        topicRepository.save(topics);

        List<Usr> users = new ArrayList<>();
        Usr usr1 = new Usr();
        Usr usr2 = new Usr();
        usr1.setEmail("ac7815");
        usr1.setLogin("adicatana");
        usr1.setName("Adrian");
        usr1.setPassword("parolabossului");
        usr1.setRating(1);

        usr2.setEmail("ib2215");
        usr2.setLogin("ioanbudea");
        usr2.setName("Ioan");
        usr2.setPassword("parolabossului2");
        usr2.setRating(5);

        users.add(usr1);
        users.add(usr2);

        userRepository.save(users);

        List<RoleUser> roleUsers = new ArrayList<>();
        roleUserRepository.save(roleUsers);

        List<UserQuestion> userQuestions = new ArrayList<>();
        userQuestionRepository.save(userQuestions);

    }
}
