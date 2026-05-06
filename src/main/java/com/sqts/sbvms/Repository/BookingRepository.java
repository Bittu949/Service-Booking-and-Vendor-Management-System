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
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
       SELECT b
       FROM Booking b
       WHERE b.vendor = :vendor
       AND b.bookingDate = :bookingDate
       """)
    List<Booking> findBookingsForUpdate(
            @Param("vendor") Vendor vendor,
            @Param("bookingDate") LocalDate bookingDate
    );
    List<Booking> findAllByUserId(Long userId);
}
