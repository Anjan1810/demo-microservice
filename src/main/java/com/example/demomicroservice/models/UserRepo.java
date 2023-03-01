package com.example.demomicroservice.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserData,String> {

}
