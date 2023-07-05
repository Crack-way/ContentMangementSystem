package com.example.contentmanagementsystem.service;


import com.example.contentmanagementsystem.exception.BucketDoesNotExistException;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    public String upload(MultipartFile multipartFile, String bucketName) {

        try {

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if (found) {

                minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(multipartFile.getOriginalFilename()).stream(
                                multipartFile.getInputStream(), multipartFile.getSize(), -1).
                        contentType(multipartFile.getContentType())
                        .build());
                return "File Successfully uploaded.";
            } else {
                throw new BucketDoesNotExistException(bucketName + "Doesnt Exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "File fail to uploaded";
    }

}
