package com.sqts.sbvms.Dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class TimeSlot {
    LocalTime startTime;
    LocalTime endTime;
}
