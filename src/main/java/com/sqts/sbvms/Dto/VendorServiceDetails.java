package com.sqts.sbvms.Dto;

import com.sqts.sbvms.Entity.ServiceCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorServiceDetails {
    private Long price;
    private Duration duration;
    private ServiceCategory serviceCategory;
}
