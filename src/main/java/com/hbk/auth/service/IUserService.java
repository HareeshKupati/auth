package com.hbk.auth.service;

import com.hbk.auth.dto.UserDTO;
import com.hbk.auth.jdo.UserJDO;
import com.hbk.auth.repository.UserRepository;
import com.hbk.corefw.service.ICoreService;

import java.util.Calendar;
import java.util.Date;

public interface IUserService extends ICoreService<UserDTO, UserJDO, Long, UserRepository> {

    void updateUserLoggingInfo(String username, Date loggingInTime);
}
