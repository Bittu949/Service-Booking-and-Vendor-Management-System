package com.sqts.sbvms.Controller;

import com.sqts.sbvms.Dto.ApiResponse;
import com.sqts.sbvms.Dto.BookingRequest;
import com.sqts.sbvms.Entity.Booking;
import com.sqts.sbvms.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@RestController
public class BookingController {
    BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/bookings")
    public ResponseEntity<ApiResponse<Booking>> createBooking(@Valid @RequestBody BookingRequest request){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Booking created",
                        bookingService.createBooking(request),
                        LocalDateTime.now()),
                HttpStatus.CREATED);
    }
}
