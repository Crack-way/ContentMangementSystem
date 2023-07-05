package com.example.contentmanagementsystem.exception;



public class BucketDoesNotExistException extends RuntimeException{

    public BucketDoesNotExistException(String str){
        super(str);
    }
}
