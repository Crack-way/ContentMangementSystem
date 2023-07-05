package com.example.contentmanagementsystem.service;

import com.example.contentmanagementsystem.dto.FileContentDto;
import com.example.contentmanagementsystem.entity.FileContent;
import com.example.contentmanagementsystem.entity.FileInfo;
import com.example.contentmanagementsystem.entity.UserHistory;
import com.example.contentmanagementsystem.repo.FileContentRepo;
import com.example.contentmanagementsystem.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
@JaversSpringDataAuditable
public class FileServiceImpl implements FileService {

    private final FileContentRepo fileContentsRepo;


    @Override
    public Flux<FileContent> getFileContents() {
        return null;
    }

    @Override
    public Mono<FileContentDto> saveFileContents(FilePart filePart) {

        FileContentDto fileContentDto = new FileContentDto();
        UserHistory userHistory = new UserHistory();
        userHistory.setCreatedBy("Admin_User");
        userHistory.setCreatedDate(new Date());
        userHistory.setModifiedDate(new Date());
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(filePart.filename());
        fileInfo.setPath("test/this/is/test");
        fileInfo.setUserHistory(userHistory);
        fileContentDto.setFileInfo(fileInfo);

        filePart.content().subscribe(dataBuffer -> {
            // Convert the DataBuffer to byte array
            byte[] contentBytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(contentBytes);

            // Convert the byte array to string
            String contentString = new String(contentBytes, StandardCharsets.UTF_8);
            fileContentDto.setActual_data(contentString);


        });

        return Mono.just(fileContentDto).map(AppUtils::dtoToEntity)
                .flatMap(fileContentsRepo::save).map(AppUtils::entityToDto);

    }

}
