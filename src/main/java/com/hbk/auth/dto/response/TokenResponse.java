package com.hbk.auth.dto.response;

import com.hbk.corefw.dto.response.CoreResponse;

public class TokenResponse implements CoreResponse {

    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
