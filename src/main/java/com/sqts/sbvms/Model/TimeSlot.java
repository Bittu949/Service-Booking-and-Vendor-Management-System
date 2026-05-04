package com.sqts.sbvms.Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class TimeSlot {
    LocalTime startTime;
    LocalTime endTime;
    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
