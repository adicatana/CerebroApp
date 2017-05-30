package com.cerebro.entities;

import javax.persistence.*;

/**
 * Created by ioanbudea on 30/05/17.
 */
@Entity
@Table(name = "roleuser", schema = "public", catalog = "g1627115_u")
public class RoleuserEntity {
    private int roleuserid;
    private int userid;
    private int roleid;

    @Id
    @Column(name = "roleuserid")
    public int getRoleuserid() {
        return roleuserid;
    }

    public void setRoleuserid(int roleuserid) {
        this.roleuserid = roleuserid;
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
    @Column(name = "roleid")
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

        RoleuserEntity that = (RoleuserEntity) o;

        if (roleuserid != that.roleuserid) return false;
        if (userid != that.userid) return false;
        if (roleid != that.roleid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleuserid;
        result = 31 * result + userid;
        result = 31 * result + roleid;
        return result;
    }
}
