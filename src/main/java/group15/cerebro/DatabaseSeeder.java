package group15.cerebro;

import group15.cerebro.entities.Question;
import group15.cerebro.repositories.QuestionRepository;
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

    public DatabaseSeeder(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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

    }
}
