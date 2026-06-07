package com.sqts.sbvms.Service;

import com.sqts.sbvms.Dto.BookingRequest;
import com.sqts.sbvms.Dto.BookingResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    public BookingResponse createBooking(BookingRequest request){
        BookingResponse response = new BookingResponse();
        return response;
    }
}
