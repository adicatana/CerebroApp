package group15.cerebro.entities;

import javax.persistence.*;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_topicid_seq")
    @SequenceGenerator(name = "topic_topicid_seq", sequenceName = "topic_topicid_seq", allocationSize = 1)
    private long id;
    private String topicname;
    private boolean isBook;
    private String author;

    private int questionNo;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }


    public boolean isBook() {
        return isBook;
    }

    public void setBook(boolean book) {
        isBook = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic that = (Topic) o;

        if (id != that.id) return false;
        if (topicname != null ? !topicname.equals(that.topicname) : that.topicname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (topicname != null ? topicname.hashCode() : 0);
        return result;
    }
}