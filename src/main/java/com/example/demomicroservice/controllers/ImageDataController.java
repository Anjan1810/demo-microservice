package com.example.demomicroservice.controllers;

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

    @PostMapping
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        ImageUploadResponse response = imageDataService.uploadProfileImage(file);

//        return ResponseEntity.status(HttpStatus.OK)
//                .body(response);
          return ResponseEntity.ok(HttpStatus.OK);
    }

  /*  @GetMapping("/info/{name}")
    public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
        ImageData image = imageDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }
*/
    @GetMapping("/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name) throws IOException {
        byte[] image = imageDataService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


}
