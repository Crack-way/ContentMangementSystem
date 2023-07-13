package com.example.contentmanagementsystem.controller;

import com.example.contentmanagementsystem.dto.FileContentDto;
import com.example.contentmanagementsystem.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file-api")
public class FileController {

    private final FileService fileService;


    @PostMapping("/save")
    public ResponseEntity<FileContentDto> saveFileContent(FilePart file) throws IOException {

        return ResponseEntity.ok().body(fileService.saveFileContents(file));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<FileContentDto> updateFileContent(FilePart file, @PathVariable String id) throws IOException {

        return ResponseEntity.ok().body(fileService.updateFileContents(file, id));
    }


    @GetMapping("/roll")
    public FileContentDto rollbackToSnapshot(@RequestParam("entityId") String entityId, @RequestParam("snapshotVersion") int snapshotVersion) {

        return fileService.rollbackToSnapshot(entityId, snapshotVersion);

    }

    @GetMapping("/file")
    public Mono<List<FileContentDto>> getFileContentChanges() {

        return fileService.getFileContentChanges();
    }

    @GetMapping("/file/{id}")
    public Mono<List<FileContentDto>> getFileContentChanges(@PathVariable String id) {

        return fileService.getFileContentChanges(id);
    }

    @GetMapping("/fileContent/states")
    public Mono<String> getFileContentStates() {

        return fileService.getFileContentAuditStates();
    }

    @GetMapping("/fileContent/{id}/states")
    public Mono<String> getFileContentStates(@PathVariable String id) {

        return fileService.getFileContentAuditStates(id);
    }



}




