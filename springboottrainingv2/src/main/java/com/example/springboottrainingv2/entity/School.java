package com.example.springboottrainingv2.entity;

import com.example.springboottrainingv2.constants.Gender;
import com.example.springboottrainingv2.converter.GenderEnumConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school", schema = "new_application")
@Builder
@Getter
@Setter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<User> users;
}
