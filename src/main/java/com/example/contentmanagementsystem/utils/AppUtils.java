package com.example.contentmanagementsystem.utils;

import com.example.contentmanagementsystem.dto.FileContentDto;
import com.example.contentmanagementsystem.entity.FileContent;
import org.springframework.beans.BeanUtils;
public class AppUtils {

    public static FileContentDto entityToDto(FileContent fileContent) {


        FileContentDto fileContentDto = new FileContentDto();
        BeanUtils.copyProperties(fileContent, fileContentDto);
        return fileContentDto;
    }

    public static FileContent dtoToEntity(FileContentDto fileContentDto) {

        FileContent fileContent = new FileContent();
        BeanUtils.copyProperties(fileContentDto, fileContent);
        return fileContent;
    }
}
