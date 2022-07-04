package com.example.springboottrainingv2.controller;

import com.example.springboottrainingv2.dto.UserDto;
import com.example.springboottrainingv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                 @RequestParam(name = "order", required = false) String order,
                                                 @RequestParam(name = "by", required = false) String by){
        if(order == null || order.isEmpty() || by == null || by.isEmpty()){
            return userService.findAll(page,size);
        }
        return userService.findAll(page,size,order,by);
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(userService.get());
    }
}