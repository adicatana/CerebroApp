package group15.cerebro;

import group15.cerebro.entities.Question;
import group15.cerebro.entities.Role;
import group15.cerebro.entities.Topic;
import group15.cerebro.entities.User;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.repositories.RoleRepository;
import group15.cerebro.repositories.TopicRepository;
import group15.cerebro.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adi_c on 31/05/2017.
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private QuestionRepository questionRepository;
    private RoleRepository roleRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;

    public DatabaseSeeder(QuestionRepository questionRepository,
                          RoleRepository roleRepository,
                          TopicRepository topicRepository,
                          UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.roleRepository = roleRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
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

    }
}
