package com.example.contentmanagementsystem.repo;

import com.example.contentmanagementsystem.dto.FileContentDto;
import com.example.contentmanagementsystem.entity.FileContent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface FileContentRepo extends ReactiveMongoRepository<FileContent, String> {

    Mono<FileContentDto> findByFileName(String FileName);



}
