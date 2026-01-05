package com.hbk.auth.controller;

import com.hbk.auth.dto.TokenDTO;
import com.hbk.auth.dto.UsernamePasswordDTO;
import com.hbk.auth.service.UserService;
import com.hbk.corefw.filter.AuthenticationFilter;
import com.hbk.corefw.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/v1/token")
    public ResponseEntity<TokenDTO> token(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorizationHeader, @RequestBody UsernamePasswordDTO usernamePasswordDTO) {
        // I have validation logic on date. To get same date and pass, I am initializing in beginning
        Date loggingInTime = new Date();
        if (authorizationHeader != null && authorizationHeader.startsWith(AuthenticationFilter.BASIC_)) {
            String usernamePassword[] = new String(Base64.getDecoder().decode(authorizationHeader.replaceFirst(AuthenticationFilter.BASIC_, ""))).split(":");
            String username = usernamePassword[0];
            String password = usernamePassword[1];
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated()) {
                userService.updateUserLoggingInfo(username, loggingInTime);
                return ResponseEntity.ok(new TokenDTO(
                        jwtService.createToken(username, loggingInTime, JwtService.TokenType.ACCESS_TOKEN),
                        jwtService.createToken(username, loggingInTime, JwtService.TokenType.REFRESH_TOKEN)));
            }
        } else if (authorizationHeader != null && authorizationHeader.startsWith(AuthenticationFilter.BEARER_)) {
            String bearerToken = authorizationHeader.replaceFirst(AuthenticationFilter.BEARER_, "");
            String username = jwtService.getSubject(bearerToken, JwtService.TokenType.REFRESH_TOKEN);
            if (username != null) {
                userService.updateUserLoggingInfo(username, loggingInTime);
                return ResponseEntity.ok(new TokenDTO(
                        jwtService.createToken(username, loggingInTime, JwtService.TokenType.ACCESS_TOKEN),
                        jwtService.createToken(username, loggingInTime, JwtService.TokenType.REFRESH_TOKEN)));
            }
        } else if (usernamePasswordDTO != null){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usernamePasswordDTO.getUsername(), usernamePasswordDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                userService.updateUserLoggingInfo(usernamePasswordDTO.getUsername(), loggingInTime);
                return ResponseEntity.ok(new TokenDTO(
                        jwtService.createToken(usernamePasswordDTO.getUsername(), loggingInTime, JwtService.TokenType.ACCESS_TOKEN),
                        jwtService.createToken(usernamePasswordDTO.getUsername(), loggingInTime, JwtService.TokenType.REFRESH_TOKEN)));
            }
        }
        return ResponseEntity.badRequest().body(new TokenDTO(null, null));
    }

}
