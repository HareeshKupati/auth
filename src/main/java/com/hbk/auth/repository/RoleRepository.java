package com.hbk.auth.repository;

import com.hbk.auth.jdo.AuthorityJDO;
import com.hbk.auth.jdo.RoleJDO;
import com.hbk.corefw.repository.CoreRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends CoreRepository<RoleJDO, Long> {
    @Query("select role from RoleJDO role where role.name IN :names ")
    public List<RoleJDO> findRoleJDOByName(@Param("names") Set<String> names);

    @Query("select authority from AuthorityJDO authority where authority.name IN :names ")
    public List<AuthorityJDO> findAuthorityJDOByName(@Param("names") Set<String> names);

}
