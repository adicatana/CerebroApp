package group15.cerebro.entities;

import javax.persistence.*;

@Entity
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleuser_roleuserid_seq")
    @SequenceGenerator(name = "roleuser_roleuserid_seq", sequenceName = "roleuser_roleuserid_seq", allocationSize = 1)
    private int id;
    private int userid;
    private int roleid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleUser that = (RoleUser) o;

        if (id != that.id) return false;
        if (userid != that.userid) return false;
        if (roleid != that.roleid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userid;
        result = 31 * result + roleid;
        return result;
    }
}