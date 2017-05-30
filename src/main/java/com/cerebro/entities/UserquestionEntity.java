package com.cerebro.entities;

import javax.persistence.*;

/**
 * Created by ioanbudea on 30/05/17.
 */
@Entity
@Table(name = "userquestion", schema = "public", catalog = "g1627115_u")
public class UserquestionEntity {
    private int userquestionid;
    private int userid;
    private int questionid;
    private boolean lastattempt;

    @Id
    @Column(name = "userquestionid")
    public int getUserquestionid() {
        return userquestionid;
    }

    public void setUserquestionid(int userquestionid) {
        this.userquestionid = userquestionid;
    }

    @Basic
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "questionid")
    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    @Basic
    @Column(name = "lastattempt")
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

        UserquestionEntity that = (UserquestionEntity) o;

        if (userquestionid != that.userquestionid) return false;
        if (userid != that.userid) return false;
        if (questionid != that.questionid) return false;
        if (lastattempt != that.lastattempt) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userquestionid;
        result = 31 * result + userid;
        result = 31 * result + questionid;
        result = 31 * result + (lastattempt ? 1 : 0);
        return result;
    }
}
