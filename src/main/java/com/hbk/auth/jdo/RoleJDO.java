package com.hbk.auth.jdo;

import com.hbk.corefw.jdo.CoreIdJDO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleJDO extends CoreIdJDO {
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<AuthorityJDO> authorityJDOs = new ArrayList<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorityJDO> getAuthorityJDOs() {
        return authorityJDOs;
    }

    public void setAuthorityJDOs(List<AuthorityJDO> authorityJDOs) {
        this.authorityJDOs = authorityJDOs;
    }
}
