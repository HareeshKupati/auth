package com.hbk.auth.service;

import com.hbk.auth.dto.RoleDTO;
import com.hbk.auth.jdo.RoleJDO;
import com.hbk.auth.repository.RoleRepository;
import com.hbk.corefw.service.ICoreService;

public interface IRoleService extends ICoreService<RoleDTO, RoleJDO, Long, RoleRepository> {
}
