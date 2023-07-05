package com.example.contentmanagementsystem.dto;


import com.example.contentmanagementsystem.entity.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileContentDto {

    private String id;
    private String actual_data;
    private FileInfo fileInfo;
}
