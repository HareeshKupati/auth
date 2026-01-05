package com.hbk.auth.controller;

import com.hbk.auth.dto.RoleDTO;
import com.hbk.auth.jdo.RoleJDO;
import com.hbk.auth.repository.RoleRepository;
import com.hbk.auth.service.IRoleService;
import com.hbk.corefw.controller.AuthCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController extends AuthCrudController<RoleDTO, RoleJDO, Long, RoleRepository, IRoleService> {

}
