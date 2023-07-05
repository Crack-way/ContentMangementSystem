package com.example.contentmanagementsystem.service;

import com.example.contentmanagementsystem.dto.FileContentDto;
import com.example.contentmanagementsystem.entity.FileContent;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileService {


    Flux<FileContent> getFileContents();

    Mono<FileContentDto> saveFileContents(FilePart fileContent);
}
