package com.hbk.auth.service;

import com.hbk.auth.dto.request.AuthorityRequest;
import com.hbk.auth.dto.request.UserDetailsRequest;
import com.hbk.auth.dto.response.AuthorityResponse;
import com.hbk.auth.dto.response.UserDetailsDto;
import com.hbk.auth.dto.response.UserDetailsResponse;
import com.hbk.auth.entity.Authority;
import com.hbk.auth.entity.UserDetails;
import com.hbk.auth.repository.UserDetailsRepository;
import com.hbk.corefw.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl extends CoreService<UserDetailsRequest, UserDetailsResponse, UserDetails, Long, UserDetailsRepository> implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetailsDto loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> optionalUserDetails = userDetailsRepository.findUserDetailByUsername(username);
        if(optionalUserDetails.isPresent()){
            UserDetails userDetails = optionalUserDetails.get();
            return new UserDetailsDto(userDetails.getUsername(),userDetails.getPassword(),
                    !userDetails.getAccountExpired(), !userDetails.getAccountLocked(), !userDetails.getCredentialsExpired(),
                    userDetails.getActive(), userDetails.getAuthorities().stream().map(e -> new AuthorityResponse(e.getName())).collect(Collectors.toList()));
        }
        throw new UsernameNotFoundException("Invalid username: "+username);
    }

    @Override
    public UserDetails mapToEntity(UserDetailsRequest req) {
        if(req != null) {
            final UserDetails userDetails = new UserDetails();
            userDetails.setUsername(req.getUsername());
            userDetails.setPassword(getPasswordEncoder().encode(req.getPassword()));
            userDetails.setAccountExpired(false);
            userDetails.setAccountLocked(false);
            userDetails.setCredentialsExpired(false);
            userDetails.setActive(true);
            if(req.getAuthorities() != null){
                List<Authority> authorities = req.getAuthorities().stream().map(e ->mapToEntityAuthority(e, userDetails)).collect(Collectors.toList());
                userDetails.setAuthorities(authorities);
            }
            return userDetails;
        }
        return null;
    }

    private Authority mapToEntityAuthority(AuthorityRequest authorityRequest, UserDetails userDetails){
        Authority authority = null;
        if(authorityRequest != null){
            authority = new Authority();
            authority.setName(authorityRequest.getAuthority());
            authority.setUserDetails(userDetails);
        }
        return authority;
    }

    @Override
    public UserDetailsResponse mapToResponse(UserDetails userDetails) {
        UserDetailsResponse userDetailsResponse = null;
        if(userDetails != null){
            List<AuthorityResponse> authorityResponses = null;
            if(userDetails.getAuthorities() != null){
                authorityResponses = userDetails.getAuthorities().stream().map(e -> new AuthorityResponse(e.getName())).collect(Collectors.toList());
            }
            userDetailsResponse =    new UserDetailsResponse(userDetails.getId(), userDetails.getUsername(),userDetails.getPassword(),
                    userDetails.getAccountExpired(),userDetails.getAccountLocked(),userDetails.getCredentialsExpired(),
                    userDetails.getActive(), authorityResponses);
        }
        return userDetailsResponse;
    }

    @Override
    public void validate(UserDetails userDetails) {

    }
}
