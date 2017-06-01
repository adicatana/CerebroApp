package group15.cerebro.entities;
import javax.persistence.*;

@Entity
public class UserQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userquestion_userquestionid_seq")
    @SequenceGenerator(name = "userquestion_userquestionid_seq", sequenceName = "userquestion_userquestionid_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Usr userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionId")
    private Question question;

    private boolean lastattempt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usr getUserid() {
        return userId;
    }

    public void setUserid(Usr userId) {
        this.userId = userId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + userId.hashCode();
        result = 31 * result + question.hashCode();
        result = 31 * result + (lastattempt ? 1 : 0);
        return result;
    }
}