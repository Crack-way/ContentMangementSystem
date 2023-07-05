package com.example.contentmanagementsystem.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    private String name;

    private String path;

    private String size;

    private String content_type;

    private UserHistory userHistory;

}
