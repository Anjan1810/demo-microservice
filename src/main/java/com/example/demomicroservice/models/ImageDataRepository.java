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
    @Query("UPDATE ImageData a SET a.filepath = :path WHERE a.name = 'profile'")
    void insertProfilepic(@Param("path") String path);

    @Query("Select Count(*) from ImageData a where a.name='profile'")
    int checkifAlreadyPresent();
}
