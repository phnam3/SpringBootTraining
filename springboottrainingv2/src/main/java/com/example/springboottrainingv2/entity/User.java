package com.example.springboottrainingv2.entity;

import com.example.springboottrainingv2.constants.Gender;
import com.example.springboottrainingv2.converter.GenderEnumConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "new_application")
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = GenderEnumConverter.class)
    private Gender gender;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="school_id",nullable = false)
    private School school;
}
