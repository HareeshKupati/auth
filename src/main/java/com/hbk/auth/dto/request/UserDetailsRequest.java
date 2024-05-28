package com.hbk.auth.dto.request;

import com.hbk.corefw.dto.request.CoreRequest;

import java.util.List;

public class UserDetailsRequest implements CoreRequest {
    private String username;
    private String password;
    public Boolean isAccountExpired;
    public Boolean isAccountLocked;
    public Boolean isCredentialsExpired;
    public Boolean isActive;
    private List<AuthorityRequest> authorities;

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

    public Boolean getAccountExpired() {
        return isAccountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        isAccountExpired = accountExpired;
    }

    public Boolean getAccountLocked() {
        return isAccountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        isAccountLocked = accountLocked;
    }

    public Boolean getCredentialsExpired() {
        return isCredentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        isCredentialsExpired = credentialsExpired;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<AuthorityRequest> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityRequest> authorities) {
        this.authorities = authorities;
    }
}
