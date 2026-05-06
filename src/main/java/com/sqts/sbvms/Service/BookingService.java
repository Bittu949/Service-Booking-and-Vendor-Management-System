package com.sqts.sbvms.Service;

import com.sqts.sbvms.Dto.BookingRequest;
import com.sqts.sbvms.Entity.Booking;
import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Exception.InvalidInputException;
import com.sqts.sbvms.Exception.UserNotFoundException;
import com.sqts.sbvms.Repository.BookingRepository;
import com.sqts.sbvms.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    BookingRepository bookingRepository;
    UserRepository userRepository;
    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }
    public Booking createBooking(BookingRequest request){
        if(request==null ||
                request.getUserId()==null ||
                request.getServiceId()==null ||
                request.getVendorId()==null ||
                request.getTimeSlot()==null ||
                request.getBookingDate()==null)
            throw new InvalidInputException("Please fill all the fields.");

        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if(userOpt.isEmpty())
            throw new UserNotFoundException("User not found.");
    }
}
