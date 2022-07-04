package com.example.springboottrainingv1.entity;

import java.util.List;

public enum ERoles {
    SUPER_ADMIN,
    ADMIN,
    USER,
    NON_USER;

    public static boolean isExisted(List<String> list) {
        for (String name : list) {
            for (ERoles eRoles : values()) {
                if (eRoles.name().equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
