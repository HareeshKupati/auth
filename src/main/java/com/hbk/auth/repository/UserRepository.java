package com.hbk.auth.repository;

import com.hbk.auth.jdo.AuthorityJDO;
import com.hbk.auth.jdo.RoleJDO;
import com.hbk.auth.jdo.UserJDO;
import com.hbk.corefw.repository.CoreRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CoreRepository<UserJDO, Long> {
    public Optional<UserJDO> findUserJDOByUsername(String username);

    @Query("select role from RoleJDO role where role.name IN :names ")
    public List<RoleJDO> findRoleJDOByName(@Param("names") Set<String> names);

}
