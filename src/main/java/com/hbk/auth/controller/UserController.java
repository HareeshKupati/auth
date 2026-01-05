package com.hbk.auth.controller;

import com.hbk.auth.dto.UserDTO;
import com.hbk.auth.jdo.UserJDO;
import com.hbk.auth.repository.UserRepository;
import com.hbk.auth.service.IUserService;
import com.hbk.corefw.controller.SimpleCrudController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@ApiOperation(value = "user service", authorizations = {@Authorization("bearer")})

public class UserController extends SimpleCrudController<UserDTO, UserJDO, Long, UserRepository, IUserService> {

}
