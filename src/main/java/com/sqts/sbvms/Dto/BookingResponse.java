package com.sqts.sbvms.Dto;

import com.sqts.sbvms.Enum.BookingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingResponse {
    private Long bookingId;
    private String customerName;
    private String vendorName;
    private String serviceName;
    private LocalDate bookingDate;
    private String timeSlot;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
