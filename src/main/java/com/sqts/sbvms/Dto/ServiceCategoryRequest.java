package com.sqts.sbvms.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceCategoryRequest {

    @NotBlank
    private String serviceName;

    @NotBlank
    private String description;
}
