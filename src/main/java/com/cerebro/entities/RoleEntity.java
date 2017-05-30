package com.cerebro.entities;

import javax.persistence.*;

/**
 * Created by ioanbudea on 30/05/17.
 */
@Entity
@Table(name = "role", schema = "public", catalog = "g1627115_u")
public class RoleEntity {
    private int roleid;
    private String role;

    @Id
    @Column(name = "roleid")
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (roleid != that.roleid) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleid;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
