package com.sqts.sbvms.Controller;

import com.sqts.sbvms.Dto.ApiResponse;
import com.sqts.sbvms.Dto.BookingRequest;
import com.sqts.sbvms.Dto.VendorAssignmentRequest;
import com.sqts.sbvms.Entity.Booking;
import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

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
    @PostMapping("/bookings/assignVendor")
    public ResponseEntity<ApiResponse<Booking>> assignVendor(@Valid @RequestBody VendorAssignmentRequest request){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Booking created",
                        bookingService.assignVendor(request),
                        LocalDateTime.now()),
                HttpStatus.CREATED);
    }
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<ApiResponse<List<Booking>>> showBookingsOfUser(@PathVariable Long userId){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Bookings found.",
                        bookingService.showBookingsOfUser(userId),
                        LocalDateTime.now()),
                HttpStatus.OK);
    }
    @GetMapping("/bookings/vendor/{vendorId}")
    public ResponseEntity<ApiResponse<List<Booking>>> showBookingsOfVendor(@PathVariable Long vendorId){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Bookings found.",
                        bookingService.showBookingsOfVendor(vendorId),
                        LocalDateTime.now()),
                HttpStatus.OK);
    }
    @GetMapping("/bookings/{bookingId}/vendors")
    public ResponseEntity<ApiResponse<List<Vendor>>> showVendorsAvailableForParticularBooking(@PathVariable Long bookingId){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Vendors found.",
                        bookingService.showVendorsAvailableForParticularBooking(bookingId),
                        LocalDateTime.now()),
                HttpStatus.OK);
    }
}
