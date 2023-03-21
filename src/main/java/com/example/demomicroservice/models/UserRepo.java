package com.example.demomicroservice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<UserData, String> {

    @Query("select a from UserData  a where a.emailid= ?1 and a.password= ?2")
    UserData findUser(String emailid, String password);

    @Modifying
    @Query("DELETE FROM  UserData a WHERE a.emailid= :userId")
    void deleteUser(@Param("userId") String userId);

}
