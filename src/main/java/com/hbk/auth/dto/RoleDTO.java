package com.hbk.auth.dto;

import com.hbk.corefw.dto.CoreIdDTO;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO extends CoreIdDTO {
    private String name;

    private List<AuthorityDTO> authorityDTOs = new ArrayList<>();

    private List<String> authorities = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorityDTO> getAuthorityDTOs() {
        return authorityDTOs;
    }

    public void setAuthorityDTOs(List<AuthorityDTO> authorityDTOs) {
        this.authorityDTOs = authorityDTOs;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
