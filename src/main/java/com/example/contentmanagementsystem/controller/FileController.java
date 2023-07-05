package com.example.contentmanagementsystem.controller;

import com.example.contentmanagementsystem.dto.FileContentDto;
import com.example.contentmanagementsystem.service.FileService;
import lombok.RequiredArgsConstructor;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@JaversSpringDataAuditable
@RequestMapping("/file-api")
public class FileController {

    private final FileService fileService;


    @PostMapping("/save")
    public Mono<ResponseEntity<FileContentDto>> saveFileContent(FilePart file) {

        return fileService.saveFileContents(file)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}




