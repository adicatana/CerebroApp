package group15.cerebro.session.models;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.QuestionRepository;

public class UserInputProcessor {
    private QuestionRepository questionRepository;

    public UserInputProcessor(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // This class should save the received user question.
    public void processInput(Question proposedQuestion, Usr user) {
        MainApplication.logger.info("Question received from " + user.getName());
        MainApplication.logger.info("Question: " + proposedQuestion.getQuestion());
        MainApplication.logger.info("Answer: " + proposedQuestion.getAnswer());
        MainApplication.logger.info("Wrong1: " + proposedQuestion.getWrong1());
        MainApplication.logger.info("Wrong2: " + proposedQuestion.getWrong2());

    }
}
