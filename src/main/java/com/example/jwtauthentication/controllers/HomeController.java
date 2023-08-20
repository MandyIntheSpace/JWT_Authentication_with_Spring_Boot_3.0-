package com.example.jwtauthentication.controllers;

import com.example.jwtauthentication.contant.ApiConstant;
import com.example.jwtauthentication.models.Users;
import com.example.jwtauthentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiConstant.HOME)
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(ApiConstant.USERS)
    public List<Users> getUser() {
        System.out.println("getting users");
        return userService.getUsers();
    }

    @GetMapping(ApiConstant.CURRENT_USER)
    public String getLogged(Principal principal) {
        return principal.getName();
    }
}
