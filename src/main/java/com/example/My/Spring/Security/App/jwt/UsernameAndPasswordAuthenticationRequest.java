package com.example.My.Spring.Security.App.jwt;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthenticationRequest {


    private String username;
    private String password;
}
