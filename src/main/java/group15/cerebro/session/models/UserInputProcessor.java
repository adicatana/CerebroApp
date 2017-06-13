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
        MainApplication.logger.info("Topic: " + proposedQuestion.getTopic().getTopicname());
        MainApplication.logger.info("Question: " + proposedQuestion.getQuestion());
        MainApplication.logger.info("Answer: " + proposedQuestion.getAnswer());
        MainApplication.logger.info("Wrong1: " + proposedQuestion.getWrong1());
        MainApplication.logger.info("Wrong2: " + proposedQuestion.getWrong2());

        if (!validate(proposedQuestion)) {
            MainApplication.logger.info("The submitted question was not valid.");
            return;
        }

        // Find the topic by given name
        Topic topic = topicRepository.findByTopicName(
                proposedQuestion.getTopic().getTopicname());

        if (topic == null) {
            MainApplication.logger.info("Newly proposed or invalid topic.");
            return;
        }

        // Set the found-by-name topic
        proposedQuestion.setTopic(topic);

        // Save proposed question
        // MainApplication.logger.info("Saving the new question.");
        // questionRepository.save(proposedQuestion);
    }

    private boolean validate(Question proposedQuestion) {
        // Can add supplemental validation
        return true;
    }
}
