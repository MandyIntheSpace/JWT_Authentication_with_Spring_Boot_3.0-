package com.example.jwtauthentication.services;

import com.example.jwtauthentication.models.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    public List<Users> store = new ArrayList<>();

    public UserService() {
        store.add(new Users(UUID.randomUUID().toString(), "Mandip Timsina", "timsinarewon@gmail.com"));
        store.add(new Users(UUID.randomUUID().toString(), "Joel Timsina", "timsinarewon@gmail.com"));
        store.add(new Users(UUID.randomUUID().toString(), "Nikhil Timsina", "timsinarewon@gmail.com"));
        store.add(new Users(UUID.randomUUID().toString(), "Ronish Timsina", "timsinarewon@gmail.com"));

    }

    public List<Users> getUsers(){
        return this.store;
    }
}
