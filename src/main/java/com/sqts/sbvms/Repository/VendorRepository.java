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

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
       SELECT v
       FROM Vendor v
       WHERE v.skills = :skills
       """)
    List<Vendor> findVendorsWithParticularSkill(
            @Param("skills") String skills
    );
}
