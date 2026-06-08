package com.sqts.sbvms.Controller;

import com.sqts.sbvms.Dto.ApiResponse;
import com.sqts.sbvms.Dto.BookingRequest;
import com.sqts.sbvms.Dto.BookingResponse;
import com.sqts.sbvms.Service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/bookings")
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@RequestBody BookingRequest request){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Booking created successfully.",
                        bookingService.createBooking(request),
                        LocalDateTime.now()),
                HttpStatus.CREATED);
    }
}
