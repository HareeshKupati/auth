package com.hbk.auth.service;

import com.hbk.auth.dto.AuthorityDTO;
import com.hbk.auth.dto.RoleDTO;
import com.hbk.auth.dto.UserDTO;
import com.hbk.auth.jdo.AuthorityJDO;
import com.hbk.auth.jdo.RoleJDO;
import com.hbk.auth.jdo.UserJDO;
import com.hbk.auth.repository.RoleRepository;
import com.hbk.auth.repository.UserRepository;
import com.hbk.corefw.dto.Error;
import com.hbk.corefw.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService extends CoreService<RoleDTO, RoleJDO, Long, RoleRepository> implements IRoleService {

    @Override
    public void mapToDTO(RoleJDO jdo, RoleDTO dto) {

    }

    @Override
    public void mapToJDO(RoleDTO dto, RoleJDO jdo) {
        if (!CollectionUtils.isEmpty(dto.getAuthorities())) {
            Set<String> authorities = new HashSet<>(dto.getAuthorities());
            List<AuthorityJDO> authorityJDOs = repository.findAuthorityJDOByName(authorities);
            Set<String> savedAuthorities = authorityJDOs.stream().map(AuthorityJDO::getName).collect(Collectors.toSet());
            authorities.removeAll(savedAuthorities);
            if(!CollectionUtils.isEmpty(authorities)){
                List<AuthorityJDO> authorityJDOList = authorities.stream().map(e -> new AuthorityJDO(e)).collect(Collectors.toList());
                authorityJDOList  = repository.saveAll((Iterable) authorityJDOList);
                authorityJDOs.addAll(authorityJDOList);
            }
            jdo.setAuthorityJDOs(authorityJDOs);
        }
    }

    @Override
    public List<Error> validateDTO(RoleDTO dto) {
        return null;
    }


}
