package com.sqts.sbvms.Dto;

import com.sqts.sbvms.Enum.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateBookingStatusResponse {
    private Long bookingId;
    private BookingStatus previousStatus;
    private BookingStatus currentStatus;
}
