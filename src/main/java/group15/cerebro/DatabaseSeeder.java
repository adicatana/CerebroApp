package group15.cerebro;

import group15.cerebro.entities.*;
import group15.cerebro.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
//public class DatabaseSeeder implements CommandLineRunner {
public class DatabaseSeeder {
//    private QuestionRepository questionRepository;
//    private RoleRepository roleRepository;
//    private TopicRepository topicRepository;
//    private UserRepository userRepository;
//    private RoleUserRepository roleUserRepository;
//    private UserQuestionRepository userQuestionRepository;
//
//    public DatabaseSeeder(QuestionRepository questionRepository,
//                          RoleRepository roleRepository,
//                          TopicRepository topicRepository,
//                          UserRepository userRepository,
//                          RoleUserRepository roleUserRepository,
//                          UserQuestionRepository userQuestionRepository
//                          ) {
//        this.questionRepository = questionRepository;
//        this.roleRepository = roleRepository;
//        this.topicRepository = topicRepository;
//        this.userRepository = userRepository;
//        this.roleUserRepository = roleUserRepository;
//        this.userQuestionRepository = userQuestionRepository;
//    }
//
//    private Question makeQuestion(String question, String answer1, String wrong1, String wrong2) {
//        Question quest = new Question();
//        quest.setQuestion(question);
//        quest.setAnswer(answer1);
//        quest.setWrong1(wrong1);
//        quest.setWrong2(wrong2);
//        return quest;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<Question> bookings = new ArrayList<>();
//
//        bookings.add(makeQuestion(
//                "'Tux' a penguin is a mascot of ______?",
//                "Linux",
//                "Apple",
//             "Microsoft"
//        ));
//        bookings.add(makeQuestion(
//                "In C++, what does \0 mean?",
//                "Null",
//                "Tab",
//                "Newline"
//        ));
//        bookings.add(makeQuestion(
//                "What does doctype in HTML do?",
//                "Tells the browser how to render the HTML markup",
//                "Loads all references to external JavaScript files",
//                "Adds CSS styles to the HTML page"
//        ));
//        bookings.add(makeQuestion(
//                "What maybe a problem with serving pages as application/xhtml+xml?",
//                "Browsers that don't accept it render plain text/html",
//                "There are performance issues",
//                "The browser only displays the CSS and not the HTML"
//        ));
//        bookings.add(makeQuestion(
//                "How do you declare the language in HTML?",
//                "A language attribute can be added to the HTML tag",
//                "A language attribute should be added to the body tag",
//                "There's no way to declare it in HTML, it requires JavaScript"
//        ));
//        bookings.add(makeQuestion(
//                "What is the port number for HTTP?",
//                "80",
//                "22",
//                "120"
//        ));
//        bookings.add(makeQuestion(
//                "What is the port number for SMTP?",
//                "25",
//                "15",
//                "100"
//        ));
//        bookings.add(makeQuestion(
//                "How many layers are in the OSI model?",
//                "7",
//                "5",
//                "4"
//        ));
//        bookings.add(makeQuestion(
//                "What is NOT an HTML5 element?",
//                "block",
//                "audio",
//                "canvas"
//        ));
//        bookings.add(makeQuestion(
//                "Which port number for DNS?",
//                "53",
//                "50",
//                "54"
//        ));
//        bookings.add(makeQuestion(
//                "Also known as one-to-all transmission:",
//                "broadcast",
//                "multicast",
//                "unicast"
//        ));
//        bookings.add(makeQuestion(
//                "OSPF is an implementation of:",
//                "distance-vector routing",
//                "path-vector routing",
//                "link-state routing"
//        ));
//        bookings.add(makeQuestion(
//                "Known as the physical address:",
//                "MAC",
//                "IP",
//                "SSID"
//        ));
//        bookings.add(makeQuestion(
//                "Routing metric for BGP:",
//                "hop count",
//                "ping",
//                "latency"
//        ));
//
//
//        questionRepository.save(bookings);
//
//
//        List<Role> roles = new ArrayList<>();
//        Role adm = new Role();
//        adm.setRole("admin");
//        Role usr = new Role();
//        usr.setRole("user");
//        roles.add(usr);
//
//        roleRepository.save(roles);
//
//        List<Topic> topics = new ArrayList<>();
//
//        Topic t1 = new Topic();
//        t1.setTopicname("networks");
//        Topic t2 = new Topic();
//        t2.setTopicname("os");
//
//        topics.add(t1);
//        topics.add(t2);
//
//        topicRepository.save(topics);
//
//        List<Usr> users = new ArrayList<>();
//        Usr usr1 = new Usr();
//        Usr usr2 = new Usr();
//        usr1.setEmail("ac7815");
//        usr1.setLogin("adicatana");
//        usr1.setName("Adrian");
//        usr1.setPassword("parolabossului");
//        usr1.setRating(1);
//
//        usr2.setEmail("ib2215");
//        usr2.setLogin("ioanbudea");
//        usr2.setName("Ioan");
//        usr2.setPassword("parolabossului2");
//        usr2.setRating(5);
//
//        users.add(usr1);
//        users.add(usr2);
//
//        userRepository.save(users);
//
//        List<RoleUser> roleUsers = new ArrayList<>();
//        roleUserRepository.save(roleUsers);
//
//        List<UserQuestion> userQuestions = new ArrayList<>();
//        userQuestionRepository.save(userQuestions);
//
//    }
}
