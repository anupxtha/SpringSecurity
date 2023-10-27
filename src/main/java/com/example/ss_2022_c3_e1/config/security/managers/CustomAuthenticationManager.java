package com.example.ss_2022_c3_e1.config.security.managers;

import com.example.ss_2022_c3_e1.config.security.authentication.CustomAuthentication;
import com.example.ss_2022_c3_e1.config.security.providers.CustomeAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomeAuthenticationProvider customeAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(customeAuthenticationProvider.supports(authentication.getClass())){
            return customeAuthenticationProvider.authenticate(authentication);
        }
        return new CustomAuthentication(false, null);
    }
}
