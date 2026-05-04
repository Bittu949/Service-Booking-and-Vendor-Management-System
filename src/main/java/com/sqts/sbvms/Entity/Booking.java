package com.sqts.sbvms.Entity;

import com.sqts.sbvms.Dto.TimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    User userId;
    @ManyToOne
    Service serviceId;
    @ManyToOne
    Vendor vendorId;
    LocalDate bookingDate;
    @Embedded
    TimeSlot timeSlot;
}
