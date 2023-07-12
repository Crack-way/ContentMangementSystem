package com.example.contentmanagementsystem.exception;

public class FileDoesNotExistException extends  RuntimeException{

    public FileDoesNotExistException(String msg){

        super(msg);
    }
}
