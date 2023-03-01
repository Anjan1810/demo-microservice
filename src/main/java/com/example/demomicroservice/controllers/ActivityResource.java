package com.example.demomicroservice.controllers;

import com.example.demomicroservice.models.ActivityData;
import com.example.demomicroservice.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
public class ActivityResource {
    @Autowired
    ActivityService activityService;

    @GetMapping(value = "/expenditures", params = {"userid", "day", "month", "year"})
    public ResponseEntity<List<ActivityData>> getActivities(@RequestParam String userid, @RequestParam String day, @RequestParam String month, @RequestParam String year) {
        if (userid != null) {
            List<ActivityData> activitydata = activityService.getAllActivities(userid,day,month,year);
            if (!activitydata.equals(null)) {
                return ResponseEntity.ok(activitydata);

            } else {
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/expenditure/add")
    public ResponseEntity<HttpStatus> addExpenditure(@RequestBody ActivityData activityData) {
        if (activityData.getexpenditureName() != null) {
            activityService.saveActivity(activityData);
            return ResponseEntity.ok(HttpStatus.OK);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/expenditure/remove")
    public ResponseEntity<HttpStatus> removeExpenditure(@RequestBody ActivityData activityData) {
        if (activityData.getexpenditureName() != null) {
            activityService.removeExpenditure(activityData);
            return ResponseEntity.ok(HttpStatus.OK);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
