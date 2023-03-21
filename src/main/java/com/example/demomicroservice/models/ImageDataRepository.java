package com.example.demomicroservice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE ImageData a SET a.filepath = :path WHERE a.name = 'profile' and a.userId = :userid")
    void insertProfilepic(@Param("userid") String userid, @Param("path") String path);

    @Query("Select Count(*) from ImageData a where a.name='profile' and a.userId = :userid")
    int checkifAlreadyPresent(@Param("userid") String userid);

    @Query("Select a from ImageData a where  a.userId = :userid")
    ImageData findbyUser(@Param("userid") String userid);
    @Transactional
    @Modifying
    @Query("DELETE FROM  ImageData a WHERE a.userId= :userId")
    void deleteUserImages(@Param("userId") String userId);

}
