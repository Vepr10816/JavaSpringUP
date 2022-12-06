package com.example.up_04_01_vepritkiy.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    Администратор, глОтдел, млОТдел;


    @Override
    public String getAuthority() {
        return name();
    }
}
