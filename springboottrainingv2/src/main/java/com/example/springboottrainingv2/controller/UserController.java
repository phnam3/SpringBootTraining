package com.example.springboottrainingv2.controller;

import com.example.springboottrainingv2.Springboottrainingv2Application;
import com.example.springboottrainingv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/getAll")
    public ResponseEntity<?> findAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(userService.findAll(pageable));
    }

//    @GetMapping("/create")
//    public ResponseEntity<?> create(){
//        userService.create();
//        return ResponseEntity.ok("Created");
//    }

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(userService.get());
    }

    @GetMapping("/property")
    public ResponseEntity<?> getPropertyValue(@RequestParam(name = "key", required = false) String key){
        return ResponseEntity.ok(userService.getProperties(key));
    }

    @GetMapping("/restart")
    public void restart(){
        Springboottrainingv2Application.restart();
    }
}
