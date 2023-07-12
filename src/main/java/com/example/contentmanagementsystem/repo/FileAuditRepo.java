package com.example.contentmanagementsystem.repo;

import com.example.contentmanagementsystem.entity.FileContent;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

@JaversSpringDataAuditable
public interface FileAuditRepo extends MongoRepository<FileContent, String> {
}
