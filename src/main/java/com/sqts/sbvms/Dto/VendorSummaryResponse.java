package com.sqts.sbvms.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorSummaryResponse {
    private Long vendorId;
    private String vendorName;
    private String vendorEmail;
    private Long totalAssignedServices;
}
