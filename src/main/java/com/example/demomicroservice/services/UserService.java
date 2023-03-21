package com.example.demomicroservice.services;

import com.example.demomicroservice.models.UserData;
import com.example.demomicroservice.models.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepository;
    public void saveUser(UserData user) {
        userRepository.save(user);
    }
    public UserData getUser(String username,String password) {
       // return userRepository.findById(username).get();
        return userRepository.findUser(username,password);
    }

    public void deleteUser(String username) {
        // return userRepository.findById(username).get();
        userRepository.deleteUser(username);
    }

}
