package com.sqts.sbvms.Controller;

import com.sqts.sbvms.Dto.ApiResponse;
import com.sqts.sbvms.Dto.VendorCreationRequest;
import com.sqts.sbvms.Dto.VendorCreationResponse;
import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Service.VendorServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class VendorServiceController {
    VendorServiceService vendorServiceService;
    public VendorServiceController(VendorServiceService vendorServiceService) {
        this.vendorServiceService = vendorServiceService;
    }
    @PostMapping("/vendor")
    public ResponseEntity<ApiResponse<VendorCreationResponse>> createVendor(@Valid @RequestBody VendorCreationRequest request){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Vendor created successfully.",
                        vendorServiceService.createVendor(request),
                        LocalDateTime.now()),
                HttpStatus.CREATED);
    }
}
