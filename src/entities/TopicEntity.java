package entities;

import javax.persistence.*;

/**
 * Created by ioanbudea on 30/05/17.
 */
@Entity
@Table(name = "topic", schema = "public", catalog = "g1627115_u")
public class TopicEntity {
    private int topicid;
    private String topicname;

    @Id
    @Column(name = "topicid")
    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    @Basic
    @Column(name = "topicname")
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

        TopicEntity that = (TopicEntity) o;

        if (topicid != that.topicid) return false;
        if (topicname != null ? !topicname.equals(that.topicname) : that.topicname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = topicid;
        result = 31 * result + (topicname != null ? topicname.hashCode() : 0);
        return result;
    }
}
