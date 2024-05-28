package com.hbk.auth.controller;

import com.hbk.auth.dto.request.UserDetailsRequest;
import com.hbk.auth.dto.response.UserDetailsResponse;
import com.hbk.auth.entity.UserDetails;
import com.hbk.auth.repository.UserDetailsRepository;
import com.hbk.auth.service.UserDetailsServiceImpl;
import com.hbk.corefw.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userdetails")
public class UserDetailsController extends CrudController<UserDetailsRequest, UserDetailsResponse, UserDetails, Long, UserDetailsRepository, UserDetailsServiceImpl> {
}
