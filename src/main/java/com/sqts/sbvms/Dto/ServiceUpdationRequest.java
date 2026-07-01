package com.sqts.sbvms.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "ServiceUpdationRequest",
        description = "Request payload for updating the price or estimated duration of a vendor's assigned service. Only the provided fields will be updated."
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceUpdationRequest {

    @Schema(
            description = "Updated service price.",
            example = "1800"
    )
    @Positive
    private Long price;

    @Schema(
            description = "Updated estimated duration in HH:mm format.",
            example = "03:00"
    )
    private String duration;
}