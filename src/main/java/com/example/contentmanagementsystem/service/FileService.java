package com.example.contentmanagementsystem.service;

import com.example.contentmanagementsystem.dto.FileContentDto;
import org.springframework.http.codec.multipart.FilePart;


import reactor.core.publisher.Mono;

import java.io.IOException;

public interface FileService {

    FileContentDto saveFileContents(FilePart fileContent) throws IOException;

    FileContentDto updateFileContents(FilePart filePart, String id) throws IOException;

    public FileContentDto rollbackToSnapshot(String entityId, int snapshotVersion);

    Mono<String> getFileContentChanges(String id);

    Mono<String> getFileContentChanges();

    Mono<String> getFileContentStates();

    Mono<String> getFileContentStates(String login);

    Mono<String> getFileContentStates(String fileContent1, String fileContent2);

}
