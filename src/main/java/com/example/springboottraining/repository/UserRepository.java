package com.example.springboottraining.repository;

import com.example.springboottraining.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByName(String name);

    List findUserByPermissionList_Role(String role);
    List findUserByPermissionList_UserList_PermissionList_Role(String role);

}
