package com.hf.utils.security;


import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class JWTAuthenticationProvider extends DaoAuthenticationProvider {
    public JWTAuthenticationProvider(UserDetailServiceImpl userDetailService) {
        setUserDetailsService(userDetailService);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return super.authenticate(authentication);
    }
}
