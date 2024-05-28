package com.hbk.auth.controller;

import com.hbk.auth.dto.request.TokenRequest;
import com.hbk.auth.dto.response.TokenResponse;
import com.hbk.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HomeController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping
    public String welcome(){
        return "Welcome to Auth Service";
    }

    @PostMapping("/api/v1/token")
    public ResponseEntity<TokenResponse> token(@RequestBody TokenRequest tokenRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword()));
        UserDetails userDetails =  userDetailsService.loadUserByUsername(tokenRequest.getUsername());
        return ResponseEntity.ok(new TokenResponse(JwtUtil.generateToken(userDetails)));
    }
}
