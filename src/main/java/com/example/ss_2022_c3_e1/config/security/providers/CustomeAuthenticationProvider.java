package com.example.ss_2022_c3_e1.config.security.providers;

import com.example.ss_2022_c3_e1.config.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomeAuthenticationProvider implements AuthenticationProvider {

    @Value("${our.secret.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication ca = (CustomAuthentication) authentication;

        String headerKey = ca.getKey();

        if (key.equals(headerKey)) {
            // if key equals headerKey then we need to create CustomAuthentication with authentication true and key with null
            CustomAuthentication result = new CustomAuthentication(true, null);
            return result;
        }

        throw new BadCredentialsException("Oh No!");
    }

    // The supports method tells for which kind of authentication is this provider
    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
