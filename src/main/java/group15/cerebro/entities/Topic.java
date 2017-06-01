package group15.cerebro.entities;

import javax.persistence.*;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String topicname;

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
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