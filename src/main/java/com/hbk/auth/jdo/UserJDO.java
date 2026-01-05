package com.hbk.auth.jdo;

import com.hbk.corefw.jdo.CoreActiveAuditJDO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserJDO extends CoreActiveAuditJDO {

    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "token_issued_date")
    private Date tokenIssuedDate;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private List<RoleJDO> roleJDOs = new ArrayList<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTokenIssuedDate() {
        return tokenIssuedDate;
    }

    public void setTokenIssuedDate(Date tokenIssuedDate) {
        this.tokenIssuedDate = tokenIssuedDate;
    }

    public List<RoleJDO> getRoleJDOs() {
        return roleJDOs;
    }

    public void setRoleJDOs(List<RoleJDO> roleJDOs) {
        this.roleJDOs = roleJDOs;
    }


}
