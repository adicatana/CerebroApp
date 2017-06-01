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

    @Override
    public void run(String... args) throws Exception {
        List<Question> bookings = new ArrayList<>();
        Question quest = new Question();
        quest.setQuestion("Ce faci Ioan?");
        quest.setAnswer("Asta e buna");
        quest.setWrong1("Prima proasta");
        quest.setWrong2("A doua proasta");
        bookings.add(quest);

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

        List<User> users = new ArrayList<>();
        User usr1 = new User();
        User usr2 = new User();
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
