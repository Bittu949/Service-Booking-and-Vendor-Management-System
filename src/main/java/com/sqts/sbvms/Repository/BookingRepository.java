package com.sqts.sbvms.Repository;

import com.sqts.sbvms.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
