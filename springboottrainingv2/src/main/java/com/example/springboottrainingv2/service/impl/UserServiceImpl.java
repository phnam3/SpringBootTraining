package com.example.springboottrainingv2.service.impl;

import com.example.springboottrainingv2.dto.UserDto;
import com.example.springboottrainingv2.entity.User;
import com.example.springboottrainingv2.entity.config.AppProperties;
import com.example.springboottrainingv2.repository.UserRepository;
import com.example.springboottrainingv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Profile("profile1")
@ConditionalOnProperty(name="app.test", havingValue = "false")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${app.defaultTitle}")
    private String defaultValue; //Read application properties with @Value
    private final Environment env; //Read application properties with Environment
    private final AppProperties appProperties; //Read application properties with configuration properties
    private final UserRepository userRepository;

    @Override
    public List<UserDto> get() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDto> users = userPage.stream().map(this::toDto).collect(Collectors.toList());
        return new PageImpl<>(users);
    }

    @Override
    public String getProperties(String key) {
        if (key != null && (!key.isEmpty())) {
            //key should have format: "name.subname" - E.g: app.title
            return env.getProperty(key);
        }
        // Read from os environment variables
        String temp = env.getProperty("APPLICATION_ENV_NAME");
        // Read from intellij program arguments
        String temp2 = env.getProperty("app.config.name");

        return defaultValue + ": " + appProperties.getDescription();
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setGender(user.getGender());
        userDto.setSchool(user.getSchool());
        userDto.setCity(user.getCity());
        return userDto;
    }
}
