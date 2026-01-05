package com.hbk.auth.jdo;

import com.hbk.corefw.jdo.CoreIdJDO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "authority")
public class AuthorityJDO extends CoreIdJDO {

    @Column(name = "name")
    private String name;

    public AuthorityJDO(){
        super();
    }

    public AuthorityJDO(String name){
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
