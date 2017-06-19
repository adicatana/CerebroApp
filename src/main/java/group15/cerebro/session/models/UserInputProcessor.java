package group15.cerebro.session.models;

import group15.cerebro.MainApplication;
import group15.cerebro.entities.Question;
import group15.cerebro.entities.Topic;
import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.QuestionRepository;
import group15.cerebro.repositories.TopicRepository;

public class UserInputProcessor {
    private QuestionRepository questionRepository;
    private TopicRepository topicRepository;

    public UserInputProcessor(QuestionRepository questionRepository, TopicRepository topicRepository) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
    }

    // This class should save the received user question.
    public void processInput(Question proposedQuestion, Usr user) {
        MainApplication.logger.info("Question received from " + user.getName());

        // This would be more efficient if we change the user interface accordingly
        MainApplication.logger.info("Topic id: " + proposedQuestion.getTopic().getId());
        MainApplication.logger.info("Question: " + proposedQuestion.getQuestion());
        MainApplication.logger.info("Answer: " + proposedQuestion.getAnswer());
        MainApplication.logger.info("Wrong1: " + proposedQuestion.getWrong1());
        MainApplication.logger.info("Wrong2: " + proposedQuestion.getWrong2());

        if (!validate(proposedQuestion)) {
            MainApplication.logger.info("The submitted question was not valid.");
            return;
        }

        // Find the topic by id
        Topic topic = topicRepository.findOne(
                proposedQuestion.getTopic().getId());

        if (topic == null) {
            MainApplication.logger.error("Something went wrong: invalid topic. No user input registered.");
            return;
        }

        // Set the found-by-name topic
        proposedQuestion.setTopic(topic);

        proposedQuestion.setValid(false);

        // Save proposed question
        MainApplication.logger.info("Saving the new question.");
        questionRepository.save(proposedQuestion);

        topic.setQuestionNo(topic.getQuestionNo() + 1);
        topicRepository.save(topic);
    }

    private boolean validate(Question proposedQuestion) {
        return !proposedQuestion.getQuestion().isEmpty()
                && !proposedQuestion.getAnswer().isEmpty()
                && !proposedQuestion.getWrong1().isEmpty()
                && !proposedQuestion.getWrong2().isEmpty();
    }
}
