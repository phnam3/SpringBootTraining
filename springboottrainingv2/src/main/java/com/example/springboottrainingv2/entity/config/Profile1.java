package com.example.springboottrainingv2.entity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("profile1")
public class Profile1 {

    static {
        System.out.println("profile1");
    }
}
