package com.example.springboottrainingv2.service;

import com.example.springboottrainingv2.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> get();

    Page<UserDto> findAll(Pageable pageable);

    String getProperties(String key);

}
