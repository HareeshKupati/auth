package com.hbk.auth.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.security.AllPermission;
import java.util.Collection;

@Entity
@Table(name = "user_details")
public class UserDetails implements com.hbk.corefw.entity.CoreEntity {
    private Long id;
    private String username;
    private String password;
    public Boolean isAccountExpired;
    public Boolean isAccountLocked;
    public Boolean isCredentialsExpired;
    public Boolean isActive;
    public Collection<Authority> authorities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "isaccountexpired")
    public Boolean getAccountExpired() {
        return isAccountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        isAccountExpired = accountExpired;
    }

    @Column(name = "isaccountlocked")
    public Boolean getAccountLocked() {
        return isAccountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        isAccountLocked = accountLocked;
    }

    @Column(name = "iscredentialsexpired")
    public Boolean getCredentialsExpired() {
        return isCredentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        isCredentialsExpired = credentialsExpired;
    }

    @Column(name ="isactive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true,mappedBy = "userDetails")
    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }
}
