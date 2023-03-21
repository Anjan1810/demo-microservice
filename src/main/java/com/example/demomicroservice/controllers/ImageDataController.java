package com.example.demomicroservice.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.demomicroservice.models.ImageUploadResponse;
import com.example.demomicroservice.services.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageDataController {
    @Autowired
    private ImageDataService imageDataService;

    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("userid") String userid, @RequestParam("image") MultipartFile file) throws IOException {
        ImageUploadResponse response = imageDataService.uploadProfileImage(userid, file);

//        return ResponseEntity.status(HttpStatus.OK)
//                .body(response);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/{name}")
    public ResponseEntity<?> getImageByName(@PathVariable("name") String name) throws IOException {
        byte[] image = imageDataService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping(value = "/deleteImages/{userId}")
    public ResponseEntity<HttpStatus> deleteUserImages(@PathVariable("userId") String userId) throws IOException {

        try {
            imageDataService.deleteUserImages(userId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
