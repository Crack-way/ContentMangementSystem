//package com.example.contentmanagementsystem.controller;
//import com.example.contentmanagementsystem.service.MinioService;
//import io.minio.errors.MinioException;
//import io.minio.messages.Item;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.compress.utils.IOUtils;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLConnection;
//import java.nio.file.Path;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class MinioController {
//
//    private final MinioService minioService;
//
//    @PostMapping("/upload")
//    private ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String bucketName) {
//
//        return new ResponseEntity<>(minioService.upload(file, bucketName), HttpStatus.OK);
//    }
//
//    @GetMapping
//    public List<Item> testMinio() throws MinioException {
//        return minioService.list();
//    }
//
//    @GetMapping("/{object}")
//    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws MinioException, IOException {
//        InputStream inputStream = minioService.get(Path.of(object));
//        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
//        //buy and ADC which will act as CDN to store images, assets. Thanks E-sewa
//        // Set the content type and attachment header.
//        response.addHeader("Content-disposition", "attachment;filename=" + object);
//        response.setContentType(URLConnection.guessContentTypeFromName(object));
//
//        // Copy the stream to the response's output stream.
//        IOUtils.copy(inputStream, response.getOutputStream());
//        response.flushBuffer();
//    }
//
//    @PostMapping
//    public void addAttachement(@RequestParam("file") MultipartFile file) {
//        Path path = Path.of(file.getOriginalFilename());
//        try {
//            minioService.upload(path, file.getInputStream(), file.getContentType());
//        } catch (MinioException e) {
//            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
//        } catch (IOException e) {
//
//
//
//
//}
