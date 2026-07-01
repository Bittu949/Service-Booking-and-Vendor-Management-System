package com.sqts.sbvms.Exception;

public class InvalidBookingDateException extends RuntimeException{
    public InvalidBookingDateException(String message){
        super(message);
    }
}
