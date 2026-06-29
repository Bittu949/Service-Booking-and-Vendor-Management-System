package com.sqts.sbvms.Dto;

import com.sqts.sbvms.Enum.VendorStatus;
import com.sqts.sbvms.Model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorProfileResponse {

    private Long vendorId;
    private String name;
    private String email;
    private String phoneNumber;

    private Address vendorAddress;

    private Integer experienceYears;
    private String description;

    private VendorStatus status;
}
