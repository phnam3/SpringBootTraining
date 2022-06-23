package com.example.springboottraining.controller;

import com.example.springboottraining.entity.Permission;
import com.example.springboottraining.entity.User;
import com.example.springboottraining.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/application")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<Page> findOne(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return userService.findAll(page,size);
    }

    @GetMapping("/findOne")
    public ResponseEntity<User> findOne(@RequestParam(name = "id") Long id) {
        return userService.findOne(id);
    }

    @GetMapping("/findAnother")
    public ResponseEntity<Permission> findAnother(@RequestParam(name = "id") Long id) {
        return userService.findAnother(id);
    }

    @GetMapping("/findUserWithHeader")
    public ResponseEntity<List<User>> findUserWithHeader(@RequestHeader(name = "permission", required = false) String permission){
        return userService.findUserWithUser(permission);
    }


    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "permission", required = false) List<String> permission) {
        log.info("Save user with name : " + name + " and permissions : " + permission);
        userService.saveOneUser(name, permission);
        return ResponseEntity.ok().body(name);
    }
}
