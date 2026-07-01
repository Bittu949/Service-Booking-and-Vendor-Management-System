package com.sqts.sbvms.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Schema(
        name = "VendorServiceRegistrationRequest",
        description = "Represents a service offered by the vendor along with its price and estimated duration."
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorServiceRegistrationRequest {

    @Schema(
            description = "Unique identifier of the service category offered by the vendor.",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull
    private Long serviceCategoryId;

    @Schema(
            description = "Price charged by the vendor for this service.",
            example = "1500",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull
    @Positive
    private Long price;

    @Schema(
            description = "Estimated time required to complete the service in HH:mm format.",
            example = "02:00",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String duration;
}