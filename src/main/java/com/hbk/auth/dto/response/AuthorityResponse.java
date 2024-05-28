package com.hbk.auth.dto.response;

import com.hbk.corefw.dto.response.CoreResponse;
import org.springframework.security.core.GrantedAuthority;

public class AuthorityResponse implements GrantedAuthority, CoreResponse {

    private String authority;

    public AuthorityResponse(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
