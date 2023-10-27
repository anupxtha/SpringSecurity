package com.example.ss_2022_c3_e1.config.security.filters;

import com.example.ss_2022_c3_e1.config.security.authentication.CustomAuthentication;
import com.example.ss_2022_c3_e1.config.security.managers.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
// This filter should get request only once per instance and should execute only once so instead of implementing Filter we extend OncePerRequestFilter
public class CustomAuthenticationFilter extends  OncePerRequestFilter   {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Create an authentication object with key coming in request which is not yet authenticate

        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication authentication = new CustomAuthentication(false, key);

        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain
        var a = customAuthenticationManager.authenticate(authentication);

        if(a.isAuthenticated()){
            // If CustomerAuthenticationManager manage to authenticate the request then in the SecurityContextHolder
            // get the context and set the Authentication so that it can be later on used by authorization/authorization filter/authorization Configuration,
            // so that authorization mechanism can check out who authenticated and their privileges they will be granted different access in the application
            SecurityContextHolder.getContext().setAuthentication(a);

            filterChain.doFilter(request,response); // only when authentication worked
        }

    }
}
