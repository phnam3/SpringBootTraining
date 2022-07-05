package com.example.springboottrainingv2.service.impl;

import com.example.springboottrainingv2.dto.UserDto;
import com.example.springboottrainingv2.entity.User;
import com.example.springboottrainingv2.entity.config.AppProperties;
import com.example.springboottrainingv2.repository.UserRepository;
import com.example.springboottrainingv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
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
    public List<UserDto> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAll(Integer page, Integer size, String order, String by) {
        Sort sort = null;
        if (order.equalsIgnoreCase("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, by);
        } else if (order.equalsIgnoreCase("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, by);
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageRequest).stream().map(this::toDto).collect(Collectors.toList());
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
