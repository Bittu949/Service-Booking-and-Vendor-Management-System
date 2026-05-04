package com.sqts.sbvms.Controller;

import com.sqts.sbvms.Dto.ApiResponse;
import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Service.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class VendorController {
    VendorService vendorService;
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    @PostMapping("/vendor")
    public ResponseEntity<ApiResponse<Vendor>> createVendor(@Valid @RequestBody Vendor vendor){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Vendor created successfully.",
                        vendorService.createVendor(vendor),
                        LocalDateTime.now()),
                HttpStatus.CREATED);
    }
    @GetMapping("/vendor")
    public ResponseEntity<ApiResponse<List<Vendor>>> displayVendors(){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Data found.",
                        vendorService.displayVendors(),
                        LocalDateTime.now()),
                HttpStatus.OK);
    }
}
