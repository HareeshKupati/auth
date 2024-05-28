package com.hbk.auth.dto.request;

import com.hbk.corefw.dto.request.CoreRequest;

public class AuthorityRequest implements CoreRequest {
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
