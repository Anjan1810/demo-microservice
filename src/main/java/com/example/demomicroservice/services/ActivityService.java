package com.example.demomicroservice.services;

import com.example.demomicroservice.models.ActivityData;
import com.example.demomicroservice.models.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ActivityService {
    @Autowired
    private ActivityRepo activityRepo;

    public void saveActivity(ActivityData user) {
        activityRepo.save(user);
    }

    public void removeExpenditure(ActivityData expenditure) {
        activityRepo.deleteExpenditureForUser(expenditure.getUserId(),expenditure.getexpenditureName());
    }

    public ActivityData getActivity(String username) {
        return activityRepo.findById(username).get();
    }

    public List<ActivityData> getAllActivities(String username,String day, String month,String year) {
        ArrayList<String> list = new ArrayList<>();
        list.add(username);
        return activityRepo.findActivitiesByUser(username,day,month,year);
    }

}
