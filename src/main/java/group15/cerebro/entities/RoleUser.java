package group15.cerebro.entities;

import javax.persistence.*;

@Entity
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleuser_roleuserid_seq")
    @SequenceGenerator(name = "roleuser_roleuserid_seq", sequenceName = "roleuser_roleuserid_seq", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Usr userid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role roleid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usr getUser() {
        return userid;
    }

    public void setUserid(Usr userid) {
        this.userid = userid;
    }

    public Role getRole() {
        return roleid;
    }

    public void setRoleid(Role roleid) {
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
        long result = id;
        result = 31 * result + userid.hashCode();
        result = 31 * result + roleid.hashCode();
        return (int)result;
    }
}