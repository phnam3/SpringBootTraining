package com.example.springboottrainingv2.service.impl;

import com.example.springboottrainingv2.constants.Gender;
import com.example.springboottrainingv2.dto.UserDto;
import com.example.springboottrainingv2.entity.City;
import com.example.springboottrainingv2.entity.School;
import com.example.springboottrainingv2.entity.User;
import com.example.springboottrainingv2.entity.config.AppProperties;
import com.example.springboottrainingv2.repository.UserRepository;
import com.example.springboottrainingv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Profile("profile1")
@ConditionalOnProperty(name = "app.test", havingValue = "false")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;
    @Value("${app.defaultTitle}")
    private String defaultValue; //Read application properties with @Value
    private final Environment env; //Read application properties with Environment
    private final AppProperties appProperties; //Read application properties with configuration properties
    private final UserRepository userRepository;

    @Override
    public List<UserDto> get() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    /***
     * Test all cascadeType with entity manager to differentiate those types.
     */
    /*
    @Override
    @Transactional
    public void create() {
        User user = new User();
        School school = new School();
        City city = new City();
        school.setUsers(List.of(user));
        user.setGender(Gender.FEMALE);
        user.setSchool(school);
        user.setCity(city);
        entityManager.persist(user);
        entityManager.flush();

        User savedUser = entityManager.find(User.class, 9L);
        School savedSchool = savedUser.getSchool();
        savedUser.setName("TESTTEST");
        savedSchool.setName("TESTSCHOOL");
        entityManager.merge(savedUser);
        entityManager.flush();

        User savedUser1 = entityManager.find(User.class, 9L);
        entityManager.remove(savedUser1);
        entityManager.flush();

        User user1 = new User();
        School school1 = new School();
        City city1 = new City();
        user1.setGender(Gender.FEMALE);
        user1.setSchool(school1);
        user1.setCity(city1);
        entityManager.persist(user1);
        entityManager.flush();

        System.out.println(entityManager.contains(user1));
        System.out.println(entityManager.contains(school1));
        System.out.println(entityManager.contains(city1));

        entityManager.detach(user);

        System.out.println(entityManager.contains(user1));
        System.out.println(entityManager.contains(school1));
        System.out.println(entityManager.contains(city1));

        User user2 = new User();
        School school2 = new School();
        City city2 = new City();
        school.setUsers(List.of(user2));
        user2.setGender(Gender.FEMALE);
        user2.setSchool(school2);
        user2.setCity(city2);
        user2.setName("TEST");
        entityManager.persist(user2);
        entityManager.flush();

        System.out.println(user2.getName());

        user.setName("OTHERTEST");
        entityManager.refresh(user2);

        System.out.println(user2.getName());

        throw new RuntimeException();
    }
     */

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
