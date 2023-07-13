package com.example.contentmanagementsystem.service;

import com.example.contentmanagementsystem.dto.FileContentDto;
import org.springframework.http.codec.multipart.FilePart;


import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {

    FileContentDto saveFileContents(FilePart fileContent) throws IOException;

    FileContentDto updateFileContents(FilePart filePart, String id) throws IOException;

    public FileContentDto rollbackToSnapshot(String entityId, int snapshotVersion);

    Mono<List<FileContentDto>> getFileContentChanges(String id);

    Mono<List<FileContentDto>> getFileContentChanges();

    Mono<String> getFileContentAuditStates();

    Mono<String> getFileContentAuditStates(String id);

//    Mono<String> getFileContentStates(String fileContent1, String fileContent2);

}
