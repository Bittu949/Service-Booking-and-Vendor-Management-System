package com.sqts.sbvms.Exception;

public class VendorServiceNotFoundException extends RuntimeException{
    public VendorServiceNotFoundException(String message){
        super(message);
    }
}
