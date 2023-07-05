package com.example.contentmanagementsystem.repo;

import com.example.contentmanagementsystem.entity.FileInfo;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@JaversSpringDataAuditable
public interface FileInfoRepo extends ReactiveMongoRepository<FileInfo, String> {
}
