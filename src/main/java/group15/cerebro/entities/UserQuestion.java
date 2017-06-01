package group15.cerebro.entities;
import javax.persistence.*;

@Entity
public class UserQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int userid;
    private int questionid;
    private boolean lastattempt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public boolean isLastattempt() {
        return lastattempt;
    }

    public void setLastattempt(boolean lastattempt) {
        this.lastattempt = lastattempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestion that = (UserQuestion) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (questionid != that.questionid) return false;
        if (lastattempt != that.lastattempt) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + userid;
        result = 31 * result + questionid;
        result = 31 * result + (lastattempt ? 1 : 0);
        return result;
    }
}