package com.example.demomicroservice.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demomicroservice.models.ImageData;
import com.example.demomicroservice.models.ImageDataRepository;
import com.example.demomicroservice.models.ImageUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class ImageDataService {

    private final String FOLDER_PATH = "C:\\Users\\Animesh\\JustappImages\\";
    private final String EC2_PATH = "/home/ec2-user/images/";



    @Autowired
    private ImageDataRepository imageDataRepository;

    public ImageUploadResponse uploadProfileImage(String userid, MultipartFile file) throws IOException {

        String modifiedId = userid.replaceAll("\"", "");
     //   String filepath = FOLDER_PATH + modifiedId;
     //   String finalpath=filepath.replaceAll("\"", "");

        String ec_2filepath = EC2_PATH + modifiedId;
        String finalpath = ec_2filepath.replaceAll("\"", "");


        ///////////////////////////////////////////////////


        int count = imageDataRepository.checkifAlreadyPresent(modifiedId);
        if (count > 0) {
            imageDataRepository.insertProfilepic(modifiedId,
                    finalpath);
        } else {
            imageDataRepository.save(ImageData.builder()
                    .name("profile")
                    .userId(modifiedId)
                    .type(file.getContentType())
                    .filepath(finalpath).build());
        }


        file.transferTo(new File(finalpath));

        ////////////////////////////////////
      /*  InputStream inputStream = new FileInputStream(new File(finalpath));

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        metadata.setContentLength(204800);

        String key = "images/" + modifiedId;

        //  PutObjectRequest request = new PutObjectRequest("moneytrackappbucket", "images/image.jpg", inputStream, metadata);
        PutObjectRequest request = new PutObjectRequest("moneytrackappbucket", key, inputStream, metadata);

        s3Client.putObject(request);
        inputStream.close();*/

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());

    }


    @Transactional
    public byte[] getImage(String name) throws IOException {
       // String userIdWithoutCom = name.replace(".com", "");
        String modifiedId = name.replaceAll("\"", "");
        ImageData dbImage = imageDataRepository.findbyUser(modifiedId);
        String filepath = dbImage.getFilepath();
        byte[] image = Files.readAllBytes(new File(filepath).toPath());
        return image;
    }
    public void deleteUserImages(String username) {
        // return userRepository.findById(username).get();
        imageDataRepository.deleteUserImages(username);
    }


}
