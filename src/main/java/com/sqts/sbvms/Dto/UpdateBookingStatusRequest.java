package com.sqts.sbvms.Dto;

import com.sqts.sbvms.Enum.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateBookingStatusRequest {
    @NotNull
    private BookingStatus status;
}
