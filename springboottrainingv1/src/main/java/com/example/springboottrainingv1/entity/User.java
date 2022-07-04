package com.example.springboottrainingv1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "application")
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_permission", schema = "application",
            joinColumns = @JoinColumn(name = "user_id",nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "permission_id",nullable = false, updatable = false)
    )
    List<Permission> permissionList;

}
