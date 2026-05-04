package com.sqts.sbvms.Controller;

import com.sqts.sbvms.Dto.ApiResponse;
import com.sqts.sbvms.Entity.Service;
import com.sqts.sbvms.Service.ServicesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ServicesController {
    ServicesService servicesService;
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }
    @PostMapping("/services")
    public ResponseEntity<ApiResponse<Service>> addService(@Valid @RequestBody Service service){
        return new ResponseEntity<>(
                new ApiResponse<>(true,
                        "Service added.",
                        servicesService.addService(service),
                        LocalDateTime.now()),
                HttpStatus.FOUND);
    }
    @GetMapping("/services")
    public ResponseEntity<ApiResponse<List<Service>>> displayServices(){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Services found.",
                        servicesService.displayServices(),
                        LocalDateTime.now()),
                HttpStatus.FOUND);
    }
    @PutMapping("/services/{id}")
    public ResponseEntity<ApiResponse<Service>> updateService(@PathVariable Long id,
                                                              @RequestBody Service updatedService){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Service updated.",
                        servicesService.updateService(id, updatedService),
                        LocalDateTime.now()),
                HttpStatus.FOUND);
    }
}
