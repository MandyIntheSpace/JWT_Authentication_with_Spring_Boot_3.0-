package com.example.jwtauthentication.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    private String userId;
    private String name;
    private String email;
}
