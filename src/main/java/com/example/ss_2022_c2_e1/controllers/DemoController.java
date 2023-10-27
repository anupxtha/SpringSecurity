package com.example.ss_2022_c2_e1.controllers;

import com.example.ss_2022_c2_e1.entities.User;
import com.example.ss_2022_c2_e1.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DemoController {

    private final UserRepository userRepository;

    @GetMapping("/demo")
    public String demo(){
        List<User> all = this.userRepository.findAll();
        return "DEMO";
    }
}
