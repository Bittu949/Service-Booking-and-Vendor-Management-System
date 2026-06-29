package com.sqts.sbvms.Exception;

public class DuplicateServiceException extends RuntimeException{
    public DuplicateServiceException(String message){
        super(message);
    }
}
