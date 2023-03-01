package com.example.demomicroservice.controllers;

import com.example.demomicroservice.models.UserData;
import com.example.demomicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserData userData) {
        if (userData.getEmailid() != null && userData.getPassword() != null) {
            userService.saveUser(userData);
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginUser(@RequestBody UserData userData) {
        if (userData.getEmailid() != null && userData.getPassword() != null) {
            UserData user = userService.getUser(userData.getUsername());
            if (user.getPassword().equalsIgnoreCase(userData.getPassword()) ) {
                return ResponseEntity.ok(HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
