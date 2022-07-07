package com.example.springboottrainingv2.entity.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Profile("profile2")
public class Profile2 {

    static {
        System.out.println("profile2");
    }
}
