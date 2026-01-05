package com.hbk.auth.service;

import com.hbk.auth.dto.UserDTO;
import com.hbk.auth.jdo.RoleJDO;
import com.hbk.auth.jdo.UserJDO;
import com.hbk.auth.repository.UserRepository;
import com.hbk.corefw.dto.Error;
import com.hbk.corefw.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends CoreService<UserDTO, UserJDO, Long, UserRepository> implements IUserService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void mapToDTO(UserJDO jdo, UserDTO dto) {
        if (!CollectionUtils.isEmpty(jdo.getRoleJDOs())) {
            dto.setRoles(jdo.getRoleJDOs().stream().map(RoleJDO::getName).collect(Collectors.toList()));
        }
    }

    @Override
    public void mapToJDO(UserDTO dto, UserJDO jdo) {
        jdo.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (!CollectionUtils.isEmpty(dto.getRoles())) {
            List<RoleJDO> roleJDOs = userRepository.findRoleJDOByName(new HashSet<>(dto.getRoles()));
            jdo.setRoleJDOs(roleJDOs);
        }
    }

    @Override
    public List<Error> validateDTO(UserDTO dto) {
        return Collections.emptyList();
    }

    @Override
    public void updateUserLoggingInfo(String username, Date loggingInTime) {
        UserJDO userJDO = repository.findUserJDOByUsername(username).get();
        userJDO.setTokenIssuedDate(loggingInTime);
        repository.save(userJDO);
    }

}
