package com.example.springboottraining.repository;

import com.example.springboottraining.entity.ERoles;
import com.example.springboottraining.entity.Permission;
import com.example.springboottraining.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findPermissionByRole(String role);
}
