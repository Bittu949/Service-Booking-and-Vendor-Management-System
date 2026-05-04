package com.sqts.sbvms.Entity;

import com.sqts.sbvms.Model.TimeSlot;
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
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
    @ManyToOne
    @JoinColumn(name = "serviceId")
    Service service;
    @ManyToOne
    @JoinColumn(name = "vendorId")
    Vendor vendor;
    LocalDate bookingDate;
    @Embedded
    TimeSlot timeSlot;
}
