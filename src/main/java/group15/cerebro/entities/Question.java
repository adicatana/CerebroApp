package group15.cerebro.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_questionid_seq")
    @SequenceGenerator(name = "question_questionid_seq", sequenceName = "question_questionid_seq", allocationSize = 1)
    private long id;

    private String question;
    private String answer;
    private String wrong1;
    private String wrong2;
    private boolean valid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic")
    private Topic topic;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getWrong1() {
        return wrong1;
    }

    public void setWrong1(String wrong1) {
        this.wrong1 = wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public void setWrong2(String wrong2) {
        this.wrong2 = wrong2;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question that = (Question) o;

        if (id != that.id) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (wrong1 != null ? !wrong1.equals(that.wrong1) : that.wrong1 != null) return false;
        if (wrong2 != null ? !wrong2.equals(that.wrong2) : that.wrong2 != null) return false;

        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (wrong1 != null ? wrong1.hashCode() : 0);
        result = 31 * result + (wrong2 != null ? wrong2.hashCode() : 0);
        return (int)result;
    }
}