package com.hbk.auth.dto.request;

import com.hbk.corefw.dto.request.CoreRequest;

public class TokenRequest implements CoreRequest {
    private String username;
    private String password;

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
}
