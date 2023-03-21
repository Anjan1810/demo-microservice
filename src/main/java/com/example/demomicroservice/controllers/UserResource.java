package com.example.demomicroservice.controllers;

import com.example.demomicroservice.models.UserData;
import com.example.demomicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserData userData) {
        if (userData.getEmailid() != null && userData.getPassword() != null) {
            userService.saveUser(userData);
            return ResponseEntity.ok().header("userId", String.valueOf(userData.getEmailid())).body(HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserData userData) {
        if (userData.getEmailid() != null && userData.getPassword() != null) {
            UserData user = userService.getUser(userData.getEmailid(), userData.getPassword());
            if (user.getPassword().equalsIgnoreCase(userData.getPassword())) {
                Map<String, Object> response = new HashMap<>();
                response.put("emailId", user.getEmailid());
                response.put("status", HttpStatus.OK);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping(value = "/delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") String userId) {

        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
