package com.sqts.sbvms.Repository;

import com.sqts.sbvms.Entity.Booking;
import com.sqts.sbvms.Entity.Vendor;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long userId);
    List<Booking> findAllByVendorId(Long vendorId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
       SELECT b
       FROM Booking b
       WHERE b.vendor = :vendor
       AND b.bookingDate = :bookingDate
       AND b.status = :status
       """)
    List<Booking> findConfirmedBookingsForUpdate(
            @Param("vendor") Vendor vendor,
            @Param("bookingDate") LocalDate bookingDate,
            @Param("status") String status
    );
}
