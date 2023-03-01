package com.example.demomicroservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ActivityData {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    private String userId;
    private String expenditureName;
    private String expenditureAmount;
    private String day;
    private String month;
    private String year;


    public ActivityData() {

    }

    public ActivityData(String userid, String expenditureName, String expenditureAmount) {
        this.userId = userid;
        this.expenditureName = expenditureName;
        this.expenditureAmount = expenditureAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getexpenditureName() {
        return expenditureName;
    }

    public void setexpenditureName(String expenditureName) {
        this.expenditureName = expenditureName;
    }

    public String getexpenditureAmount() {
        return expenditureAmount;
    }

    public void setexpenditureAmount(String expenditureAmount) {
        this.expenditureAmount = expenditureAmount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
