package com.sqts.sbvms.Service;

import com.sqts.sbvms.Dto.BookingRequest;
import com.sqts.sbvms.Dto.VendorAssignmentRequest;
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

import java.awt.print.Book;
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
                request.getTimeSlot()==null ||
                request.getBookingDate()==null)
            throw new InvalidInputException("Please fill all the fields.");

        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if(userOpt.isEmpty())
            throw new UserNotFoundException("User not found.");

        Optional<com.sqts.sbvms.Entity.Service> serviceOpt = serviceRepository.findById(request.getServiceId());
        if(serviceOpt.isEmpty())
            throw new ServiceNotFoundException("Service not found.");

        Booking booking = new Booking();
        booking.setUser(userOpt.get());
        booking.setService(serviceOpt.get());
        booking.setBookingDate(request.getBookingDate());
        booking.setTimeSlot(request.getTimeSlot());
        booking.setStatus("PENDING");
        return bookingRepository.save(booking);
    }
    @Transactional
    public Booking assignVendor(VendorAssignmentRequest request){

        if(request == null ||
                request.getBookingId() == null ||
                request.getVendorId() == null){
            throw new InvalidInputException("Please fill all the fields.");
        }

        Long bookingId = request.getBookingId();
        Long vendorId = request.getVendorId();

        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);

        if(bookingOpt.isEmpty()){
            throw new BookingNotFoundException("Booking not found.");
        }

        Optional<Vendor> vendorOpt = vendorRepository.findById(vendorId);

        if(vendorOpt.isEmpty()){
            throw new VendorNotFoundException("Vendor not found.");
        }

        Booking booking = bookingOpt.get();
        Vendor vendor = vendorOpt.get();

        // Booking should be pending before assignment
        if(!booking.getStatus().equals("PENDING")){
            throw new InvalidBookingStatusException(
                    "Vendor can only be assigned to pending bookings."
            );
        }

        // Optional: validate vendor skills
        if(vendor.getSkills() == null ||
                !vendor.getSkills().toLowerCase()
                        .contains(booking.getService().getName().toLowerCase())){

            throw new InvalidVendorException(
                    "Vendor does not support this service."
            );
        }

        // Fetch confirmed bookings of vendor for same date
        List<Booking> existingBookings =
                bookingRepository.findConfirmedBookingsForUpdate(
                        vendor,
                        booking.getBookingDate(),
                        "CONFIRMED"
                );

        LocalTime startTime = booking.getTimeSlot().getStartTime();
        LocalTime endTime = booking.getTimeSlot().getEndTime();

        // Check overlap
        boolean overlapExists = existingBookings.stream()
                .anyMatch(
                        b -> startTime.isBefore(b.getTimeSlot().getEndTime()) &&
                                endTime.isAfter(b.getTimeSlot().getStartTime())
                );

        if(overlapExists){
            throw new InvalidTimeSlotException(
                    "Vendor already booked for this time slot."
            );
        }

        // Assign vendor
        booking.setVendor(vendor);
        booking.setStatus("CONFIRMED");

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
    public List<Booking> showBookingsOfVendor(Long vendorId){
        if(vendorId==null)
            throw new InvalidInputException("Vendor Id not provided.");
        List<Booking> bookings = bookingRepository.findAllByVendorId(vendorId);
        if(bookings.isEmpty())
            throw new BookingsNotFoundException("No booking found.");
        return bookings;
    }
    public List<Vendor> showVendorsAvailableForParticularBooking(Long BookingId){

    }
}
