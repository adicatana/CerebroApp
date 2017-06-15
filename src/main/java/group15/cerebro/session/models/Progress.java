package group15.cerebro.session.models;

import group15.cerebro.entities.Topic;

/**
 * Created by adicatana on 15.06.2017.
 */
public class Progress {

    public Topic topic;
    public int rightAnswers;
    public int wrongAnswers;
    public int totalAnswers;

    public Progress() {
        this.rightAnswers = 0;
        this.wrongAnswers = 0;
        this.totalAnswers = 0;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public void incrementRight() {
        rightAnswers++;
    }

    public void incrementWrong() {
        wrongAnswers++;
    }
}
