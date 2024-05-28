package com.hbk.auth.dto.response;

import com.hbk.auth.dto.request.AuthorityRequest;
import com.hbk.corefw.dto.response.CoreResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsResponse implements CoreResponse {
    private Long id;
    private String username;
    private String password;
    private Boolean isAccountExpired;
    private Boolean isAccountLocked;
    private Boolean isCredentialsExpired;
    private Boolean isActive;
    private List<AuthorityResponse> authorities;

    public UserDetailsResponse(Long id, String username, String password, Boolean isAccountExpired, Boolean isAccountLocked, Boolean isCredentialsExpired, Boolean isActive, List<AuthorityResponse> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAccountExpired = isAccountExpired;
        this.isAccountLocked = isAccountLocked;
        this.isCredentialsExpired = isCredentialsExpired;
        this.isActive = isActive;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAccountExpired() {
        return isAccountExpired;
    }

    public Boolean getAccountLocked() {
        return isAccountLocked;
    }

    public Boolean getCredentialsExpired() {
        return isCredentialsExpired;
    }

    public Boolean getActive() {
        return isActive;
    }

    public List<AuthorityResponse> getAuthorities() {
        return authorities;
    }
}
