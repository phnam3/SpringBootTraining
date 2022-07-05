package com.example.springboottrainingv2.service.impl;

import com.example.springboottrainingv2.dto.UserDto;
import com.example.springboottrainingv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Profile("profile2")
@ConditionalOnProperty(name="app.test", havingValue = "true")
@RequiredArgsConstructor
public class TestUserServiceImpl implements UserService {

    private final Environment env;

    @Override
    public List<UserDto> get() {
        return null;
    }

    @Override
    public List<UserDto> findAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public List<UserDto> findAll(Integer page, Integer size, String order, String by) {
        return null;
    }

    public String getProperties(String key) {
        // Read from os environment variables
        String envName = env.getProperty("APPLICATION_ENV_NAME");
        // Read from intellij program arguments
        String configName = env.getProperty("app.config.name");

        return "Environment Name is : " + envName + "\n" + "Configuration Name is : " + configName;
    }
}
