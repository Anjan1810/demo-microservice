package com.example.demomicroservice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepo extends JpaRepository<ActivityData, String> {

    @Query("select a from ActivityData  a where a.userId= ?1 and a.day= ?2 and a.month= ?3 and a.year= ?4")
    List<ActivityData> findActivitiesByUser(String userid,String day,String month, String year);

    // @Query("delete a from ActivityData  a where a.userId= ?1 and a.expenditureName= ?2")
    // void deleteExpenditureForUser(String userid,String expenditurename);
    @Modifying
    @Query("DELETE FROM ActivityData a WHERE a.userId = :userId AND a.expenditureName = :expenditureName")
    void deleteExpenditureForUser(@Param("userId") String userId, @Param("expenditureName") String expenditureName);

}
