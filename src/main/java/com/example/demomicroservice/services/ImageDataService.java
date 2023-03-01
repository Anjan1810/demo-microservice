package com.example.demomicroservice.services;

import com.example.demomicroservice.models.ImageData;
import com.example.demomicroservice.models.ImageDataRepository;
import com.example.demomicroservice.models.ImageUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageDataService {

    private final String FOLDER_PATH = "C:\\Users\\Animesh\\JustappImages";

    @Autowired
    private ImageDataRepository imageDataRepository;

    public ImageUploadResponse uploadProfileImage(MultipartFile file) throws IOException {
        String filepath = FOLDER_PATH + "\\" + file.getOriginalFilename();

        int count = imageDataRepository.checkifAlreadyPresent();
        if (count > 0) {
            imageDataRepository.insertProfilepic(
                    filepath);
        }
        else{
            imageDataRepository.save(ImageData.builder()
                    .name("profile")
                    .type(file.getContentType())
                    .filepath(filepath).build());
        }



        file.transferTo(new File(filepath));

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());

    }

    /*  @Transactional
      public ImageData getInfoByImageByName(String name) {
  //        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
  //
  //        return ImageData.builder()
  //                .name(dbImage.get().getName())
  //                .type(dbImage.get().getType())
  //                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

      }
  */
    @Transactional
    public byte[] getImage(String name) throws IOException {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        String filepath = dbImage.get().getFilepath();
        byte[] image = Files.readAllBytes(new File(filepath).toPath());
        return image;
    }


}
