package com.example.contentmanagementsystem.utils.file;


import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class GetMime {

    public static String getMime(FilePart filePart) throws IOException{

        File convFile=new File(filePart.filename());
        filePart.transferTo(convFile).subscribe();
        return Files.readString(convFile.toPath());
    }
}
