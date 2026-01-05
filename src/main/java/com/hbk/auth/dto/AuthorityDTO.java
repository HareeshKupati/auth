package com.hbk.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hbk.corefw.dto.CoreIdDTO;
import org.springframework.security.core.GrantedAuthority;

public class AuthorityDTO extends CoreIdDTO implements GrantedAuthority {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.getName();
    }
}
