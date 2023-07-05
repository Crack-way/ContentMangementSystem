package com.example.contentmanagementsystem.repo;

import com.example.contentmanagementsystem.entity.FileContent;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


@JaversSpringDataAuditable
public interface FileContentRepo extends ReactiveMongoRepository<FileContent, String> {
}
