package com.sqts.sbvms.Dto;

import com.sqts.sbvms.Model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        name = "SearchResponse",
        description = "Details of a vendor returned by the vendor search operation."
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchResponse {

    @Schema(
            description = "Unique identifier of the vendor.",
            example = "12"
    )
    private Long vendorId;

    @Schema(
            description = "Full name of the vendor.",
            example = "Bittu Naik"
    )
    private String vendorName;

    @Schema(
            description = "Email address of the vendor.",
            example = "bittu123@gmail.com"
    )
    private String vendorEmail;

    @Schema(
            description = "Name of the service offered by the vendor.",
            example = "Electrician"
    )
    private String serviceName;

    @Schema(
            description = "Price charged by the vendor for the service.",
            example = "1500"
    )
    private Long price;

    @Schema(
            description = "Estimated duration required to complete the service in HH:mm format.",
            example = "02:00"
    )
    private String duration;

    @Valid
    @Schema(
            description = "Business address of the vendor."
    )
    private Address vendorAddress;
}
