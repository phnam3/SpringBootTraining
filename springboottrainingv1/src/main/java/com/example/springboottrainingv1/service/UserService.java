package com.example.springboottrainingv1.service;

import com.example.springboottrainingv1.entity.ERoles;
import com.example.springboottrainingv1.entity.Permission;
import com.example.springboottrainingv1.entity.User;
import com.example.springboottrainingv1.repository.PermissionRepository;
import com.example.springboottrainingv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Transactional
    public void saveOneUser(String name, List<String> permissions) {
        boolean existed = userRepository.findUserByName(name).isPresent();

        if(!existed && (ERoles.isExisted(permissions) || permissions.isEmpty())){
            User user = new User().builder().name(name).permissionList(new ArrayList<>()).build();
            for(String s : permissions){
                Permission permission = permissionRepository.findPermissionByRole(s).get();
                user.getPermissionList().add(permission);
                permission.getUserList().add(user);
            };
            userRepository.save(user);
        }
    }

    public ResponseEntity<User> findOne(Long id) {
        return ResponseEntity.ok().body(userRepository.findById(id).get());
    }

    public ResponseEntity<Permission> findAnother(Long id) {
        return ResponseEntity.ok().body(permissionRepository.findById(id).get());
    }

    public ResponseEntity<List<User>> findUserWithHeader(String permission) {
        return ResponseEntity.ok(permission != null ? userRepository.findUserByPermissionList_Role(permission) : userRepository.findAll());
    }

    public ResponseEntity<Page> findAll(Integer page, Integer size) {
            PageRequest pageRequest = PageRequest.of(page,size);
            return ResponseEntity.ok(userRepository.findAll(pageRequest));
    }
}
