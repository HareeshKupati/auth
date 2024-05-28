package com.hbk.auth.filter;

import com.hbk.auth.dto.response.UserDetailsDto;
import com.hbk.auth.service.UserDetailsServiceImpl;
import com.hbk.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization") != null && request.getHeader("Authorization").contains("Bearer ")){
            String token = request.getHeader("Authorization").replaceFirst("Bearer ", "");
            String subject = JwtUtil.getSubject(token);
            if(subject != null){
                UserDetailsDto userDetailsDto = userDetailsService.loadUserByUsername(subject);
                if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() == null ){
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetailsDto, token, userDetailsDto.getAuthorities()));
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
