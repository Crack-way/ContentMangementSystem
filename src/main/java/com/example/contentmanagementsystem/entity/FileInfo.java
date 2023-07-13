package com.example.contentmanagementsystem.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffIgnore;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    private String name;

    private String path;

    private int size;

    private String content_type;


}
