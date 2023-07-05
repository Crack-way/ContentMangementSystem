package com.example.contentmanagementsystem.controller;

import com.example.contentmanagementsystem.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MinioController {

    private final MinioService fileService;
    @PostMapping("/upload")
    private ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String bucketName) {

        return new ResponseEntity<>(fileService.upload(file, bucketName), HttpStatus.OK);
    }
}
