package com.hf.commons;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String role;
    private String authority;
}
