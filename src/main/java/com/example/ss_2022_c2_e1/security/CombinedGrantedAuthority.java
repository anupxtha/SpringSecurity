package com.example.ss_2022_c2_e1.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;


public class CombinedGrantedAuthority implements GrantedAuthority {

    private final String role;
    private final List<String> authorities;

    public CombinedGrantedAuthority(String role, List<String> authorities) {
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public String getAuthority() {
        StringBuilder combinedAuthority = new StringBuilder(role);
        for (String authority : authorities) {
            combinedAuthority.append("_").append(authority);
        }
        return combinedAuthority.toString();
    }
}
