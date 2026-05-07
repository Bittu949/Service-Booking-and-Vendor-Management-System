package com.sqts.sbvms.Service;

import com.sqts.sbvms.Dto.VendorCreationRequest;
import com.sqts.sbvms.Dto.VendorCreationResponse;
import com.sqts.sbvms.Entity.ServiceCategory;
import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Entity.VendorService;
import com.sqts.sbvms.Enum.Role;
import com.sqts.sbvms.Exception.InvalidInputException;
import com.sqts.sbvms.Exception.ServiceNotFoundException;
import com.sqts.sbvms.Exception.UserAlreadyExistsException;
import com.sqts.sbvms.Exception.WeakPasswordException;
import com.sqts.sbvms.Repository.ServiceCategoryRepository;
import com.sqts.sbvms.Repository.UserRepository;
import com.sqts.sbvms.Repository.VendorRepository;
import com.sqts.sbvms.Repository.VendorServiceRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final VendorServiceRepository vendorServiceRepository;
    public VendorServiceService(VendorRepository vendorRepository,
                                UserRepository userRepository,
                                ServiceCategoryRepository serviceCategoryRepository,
                                VendorServiceRepository vendorServiceRepository) {
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.vendorServiceRepository = vendorServiceRepository;
    }
    @Transactional
    public VendorCreationResponse createVendor(VendorCreationRequest request){
        if(request == null)
            throw new InvalidInputException("Please fill all the details.");
        if(request.getPassword().length() < 8)
            throw new WeakPasswordException("Provided password is weak.");
        if(userRepository.existsByEmail(request.getEmail().trim()))
            throw new UserAlreadyExistsException("Email already registered.");

        ServiceCategory serviceCategory = serviceCategoryRepository.findByServiceNameIgnoreCase(request.getServiceName().trim()).orElseThrow(() -> new ServiceNotFoundException("Provided service not found."));

        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(request.getEmail().trim());
        user.setPassword(request.getPassword().trim());
        user.setRole(Role.VENDOR);
        userRepository.save(user);

        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendorRepository.save(vendor);

        VendorService vendorService = new VendorService();
        vendorService.setVendor(vendor);
        vendorService.setServiceCategory(serviceCategory);
        vendorService.setDuration(request.getDuration());
        vendorService.setPrice(request.getPrice());
        vendorServiceRepository.save(vendorService);

        VendorCreationResponse response = new VendorCreationResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setVendorId(vendor.getId());
        response.setServiceName(serviceCategory.getServiceName());
        response.setPrice(vendorService.getPrice());
        response.setDuration(vendorService.getDuration());

        return response;
    }
}
