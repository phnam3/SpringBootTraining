package com.example.springboottrainingv2.dto;

import com.example.springboottrainingv2.constants.Gender;
import com.example.springboottrainingv2.entity.City;
import com.example.springboottrainingv2.entity.School;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private Gender gender;
    private School school;
    private City city;
}
