package com.sqts.sbvms.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "PendingVendorServiceResponse",
        description = "Service details submitted by the vendor during registration."
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PendingVendorServiceResponse {

    @Schema(
            description = "Name of the requested service.",
            example = "Electrical"
    )
    private String serviceName;

    @Schema(
            description = "Price quoted by the vendor.",
            example = "1500"
    )
    private Long price;

    @Schema(
            description = "Estimated service duration in HH:mm format.",
            example = "02:00"
    )
    private String estimatedDuration;
}