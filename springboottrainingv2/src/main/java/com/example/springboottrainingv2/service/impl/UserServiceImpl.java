package com.example.springboottrainingv2.service.impl;

import com.example.springboottrainingv2.dto.UserDto;
import com.example.springboottrainingv2.entity.User;
import com.example.springboottrainingv2.repository.UserRepository;
import com.example.springboottrainingv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> get() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<List<UserDto>> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(userRepository.findAll(pageRequest).stream().map(this::toDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<UserDto>> findAll(Integer page, Integer size, String order, String by) {
        Sort sort = null;
        if(order.equalsIgnoreCase("ASC")){
            sort = Sort.by(Sort.Direction.ASC, by);
        } else if(order.equalsIgnoreCase("DESC")){
            sort = Sort.by(Sort.Direction.DESC, by);
        }
        PageRequest pageRequest = PageRequest.of(page,size,sort);
        return ResponseEntity.ok(userRepository.findAll(pageRequest).stream().map(this::toDto).collect(Collectors.toList()));
    }

    private UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setGender(user.getGender());
        userDto.setSchool(user.getSchool());
        return userDto;
    }
}
