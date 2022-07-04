package com.example.springboottrainingv2.service;

import com.example.springboottrainingv2.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
     List<UserDto> get();

     ResponseEntity<List<UserDto>> findAll(Integer page, Integer size);
     ResponseEntity<List<UserDto>> findAll(Integer page, Integer size, String order, String by);
}
