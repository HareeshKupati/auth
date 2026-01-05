package com.hbk.auth.service;

import com.hbk.auth.jdo.AuthorityJDO;
import com.hbk.auth.jdo.UserJDO;
import com.hbk.auth.repository.UserRepository;
import com.hbk.corefw.dto.UserDetailsDTO;
import com.hbk.corefw.service.CoreUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthUserDetailService extends CoreUserDetailService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserJDOByUsername(username)
                .map(this::mapToUserDetailsDTO)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid User!!"));
    }

    private UserDetailsDTO mapToUserDetailsDTO(UserJDO userJDO){
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setId(userJDO.getId());
        userDetailsDTO.setFirstName(userJDO.getFirstName());
        userDetailsDTO.setLastName(userJDO.getLastName());
        userDetailsDTO.setUsername(userJDO.getUsername());
        userDetailsDTO.setPassword(userJDO.getPassword());
        userDetailsDTO.setTokenIssuedDate(userJDO.getTokenIssuedDate());
        userDetailsDTO.setDiscontinueDate(userJDO.getDiscontinueDate());
        userDetailsDTO.setCreatedBy(userJDO.getCreatedBy());
        userDetailsDTO.setCreatedDate(userJDO.getCreatedDate());
        userDetailsDTO.setUpdatedBy(userJDO.getUpdatedBy());
        userDetailsDTO.setUpdatedDate(userJDO.getUpdatedDate());
        userDetailsDTO.setAuthorities(userJDO.getRoleJDOs().stream().flatMap(e->e.getAuthorityJDOs().stream()).map(AuthorityJDO::getName).collect(Collectors.toSet()));
        return userDetailsDTO;
    }

}
