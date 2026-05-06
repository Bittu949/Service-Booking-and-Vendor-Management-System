package com.sqts.sbvms.Service;

import com.sqts.sbvms.Dto.BookingRequest;
import com.sqts.sbvms.Entity.Booking;
import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Exception.*;
import com.sqts.sbvms.Repository.BookingRepository;
import com.sqts.sbvms.Repository.ServiceRepository;
import com.sqts.sbvms.Repository.UserRepository;
import com.sqts.sbvms.Repository.VendorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    BookingRepository bookingRepository;
    UserRepository userRepository;
    ServiceRepository serviceRepository;
    VendorRepository vendorRepository;
    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          ServiceRepository serviceRepository,
                          VendorRepository vendorRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.vendorRepository = vendorRepository;
    }
    @Transactional
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

        Optional<com.sqts.sbvms.Entity.Service> serviceOpt = serviceRepository.findById(request.getServiceId());
        if(serviceOpt.isEmpty())
            throw new ServiceNotFoundException("Service not found.");

        Optional<Vendor> vendorOpt = vendorRepository.findById(request.getVendorId());
        if(vendorOpt.isEmpty())
            throw new VendorNotFoundException("Vendor not found.");

        List<Booking> existingBookings =
                bookingRepository.findBookingsForUpdate(
                        vendorOpt.get(),
                        request.getBookingDate()
                );

        LocalTime startTime = request.getTimeSlot().getStartTime();
        LocalTime endTime = request.getTimeSlot().getEndTime();

        if(startTime==null || endTime==null)
            throw new InvalidTimeSlotException("Start time and end time are required.");

        if (!startTime.isBefore(endTime)) {
            throw new InvalidTimeSlotException(
                    "Start time must be before end time."
            );
        }

        boolean overlapExists = existingBookings.stream()
                                            .anyMatch(
                                                    b -> startTime.isBefore(b.getTimeSlot().getEndTime()) &&
                                                            endTime.isAfter(b.getTimeSlot().getStartTime()));
        if (overlapExists) {
            throw new InvalidTimeSlotException(
                    "Vendor already booked for this time slot."
            );
        }
        Booking booking = new Booking();
        booking.setUser(userOpt.get());
        booking.setService(serviceOpt.get());
        booking.setVendor(vendorOpt.get());
        booking.setBookingDate(request.getBookingDate());
        booking.setTimeSlot(request.getTimeSlot());
        return bookingRepository.save(booking);
    }
    public List<Booking> showBookingsOfUser(Long userId){
        if(userId==null)
            throw new InvalidInputException("User Id not provided.");
        List<Booking> bookings = bookingRepository.findAllByUserId(userId);
        if(bookings.isEmpty())
            throw new BookingsNotFoundException("No booking found.");
        return bookings;
    }
}
