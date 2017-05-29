package entities;

import javax.persistence.*;

/**
 * Created by ioanbudea on 30/05/17.
 */
@Entity
@Table(name = "question", schema = "public", catalog = "g1627115_u")
public class QuestionEntity {
    private int questionid;
    private int topicid;
    private String question;
    private String answer;
    private String wrong1;
    private String wrong2;

    @Id
    @Column(name = "questionid")
    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    @Basic
    @Column(name = "topicid")
    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "wrong1")
    public String getWrong1() {
        return wrong1;
    }

    public void setWrong1(String wrong1) {
        this.wrong1 = wrong1;
    }

    @Basic
    @Column(name = "wrong2")
    public String getWrong2() {
        return wrong2;
    }

    public void setWrong2(String wrong2) {
        this.wrong2 = wrong2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (questionid != that.questionid) return false;
        if (topicid != that.topicid) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (wrong1 != null ? !wrong1.equals(that.wrong1) : that.wrong1 != null) return false;
        if (wrong2 != null ? !wrong2.equals(that.wrong2) : that.wrong2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionid;
        result = 31 * result + topicid;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (wrong1 != null ? wrong1.hashCode() : 0);
        result = 31 * result + (wrong2 != null ? wrong2.hashCode() : 0);
        return result;
    }
}
