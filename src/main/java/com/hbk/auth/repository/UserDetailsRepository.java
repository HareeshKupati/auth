package com.hbk.auth.repository;

import com.hbk.auth.entity.UserDetails;
import com.hbk.corefw.repository.CoreRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends CoreRepository<UserDetails, Long> {
    public Optional<UserDetails> findUserDetailByUsername(String username);
}
