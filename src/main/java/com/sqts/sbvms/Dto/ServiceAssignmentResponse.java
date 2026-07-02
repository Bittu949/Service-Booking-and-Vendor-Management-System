package com.sqts.sbvms.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;

@Schema(
        name = "ServiceAssignmentResponse",
        description = "Response returned after successfully assigning a service category to a vendor."
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceAssignmentResponse {

    @Schema(
            description = "Unique identifier of the vendor.",
            example = "12"
    )
    private Long vendorId;

    @Schema(
            description = "Name of the assigned service category.",
            example = "Electrician"
    )
    private String serviceCategoryName;

    @Schema(
            description = "Price charged by the vendor for the assigned service.",
            example = "1500"
    )
    private Long price;

    @Schema(
            description = "Estimated duration required to complete the service.",
            example = "PT2H"
    )
    private Duration duration;
}
