package com.sqts.sbvms.Service;

import com.sqts.sbvms.Dto.LoginRequest;
import com.sqts.sbvms.Dto.RegisterRequest;
import com.sqts.sbvms.Dto.VendorRegistrationRequest;
import com.sqts.sbvms.Dto.VendorRegistrationResponse;
import com.sqts.sbvms.Entity.ServiceCategory;
import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Entity.Vendor;
import com.sqts.sbvms.Entity.VendorService;
import com.sqts.sbvms.Enum.Role;
import com.sqts.sbvms.Enum.VendorStatus;
import com.sqts.sbvms.Exception.*;
import com.sqts.sbvms.Repository.ServiceCategoryRepository;
import com.sqts.sbvms.Repository.UserRepository;
import com.sqts.sbvms.Repository.VendorRepository;
import com.sqts.sbvms.Repository.VendorServiceRepository;
import com.sqts.sbvms.Security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final VendorServiceRepository vendorServiceRepository;
    private final JwtService jwtService;
    private final VendorRepository vendorRepository;
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       VendorRepository vendorRepository,
                       ServiceCategoryRepository serviceCategoryRepository,
                       VendorServiceRepository vendorServiceRepository){
        this.userRepository =  userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.vendorRepository = vendorRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.vendorServiceRepository = vendorServiceRepository;
    }
    public String register(RegisterRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        if(user != null)
            throw new UserAlreadyExistsException("User already exists.");

        if(!request.getPassword().equals(request.getConfirmPassword()))
            throw new InvalidInputException("Password not matching.");

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.CUSTOMER);

        userRepository.save(newUser);
        return "User registered successfully.";
    }
    public String login(LoginRequest request){
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword());
        Authentication authenticatedUser = authenticationManager.authenticate(authentication);
        UserDetails userDetails = ((UserDetails) authenticatedUser.getPrincipal());

        User user = userRepository.findByEmail(userDetails.getUsername());
        if(user.getRole() == Role.VENDOR) {
            Vendor vendor = vendorRepository.findByUserId(user.getId())
                    .orElseThrow(() -> new VendorNotFoundException("Vendor not found."));

            if (vendor.getStatus() == VendorStatus.PENDING_APPROVAL)
                throw new VendorApprovalPendingException(
                        "Your registration request is pending admin approval.");

            if (vendor.getStatus() == VendorStatus.REJECTED)
                throw new VendorRegistrationRejectedException(
                        "Your registration request has been rejected by the administrator.");

            if (vendor.getStatus() == VendorStatus.INACTIVE)
                throw new VendorAccountInactiveException(
                        "Your account has been deactivated.");
        }

        return jwtService.generateToken(userDetails);
    }
    public VendorRegistrationResponse registerVendor(VendorRegistrationRequest request) {
        if (request == null)
            throw new InvalidInputException("Please fill all the details.");

        if (request.getPassword().trim().length() < 8)
            throw new WeakPasswordException("Provided password is weak.");

        if (userRepository.existsByEmail(request.getEmail().trim()))
            throw new UserAlreadyExistsException(
                    "Email already registered.");

        if (vendorRepository.existsByPhoneNumber(request.getPhoneNumber().trim()))
            throw new VendorAlreadyExistsException(
                    "Phone number already registered.");

        if (vendorRepository.existsByAadhaarNumber(request.getAadhaarNumber().trim()))
            throw new VendorAlreadyExistsException(
                    "Aadhaar number already registered.");

        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(request.getEmail().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword().trim()));
        user.setRole(Role.VENDOR);

        userRepository.save(user);

        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendor.setStatus(VendorStatus.PENDING_APPROVAL);
        vendor.setVendorAddress(request.getVendorAddress());
        vendor.setExperienceYears(request.getExperienceYears());
        vendor.setDescription(request.getDescription());
        vendor.setPhoneNumber(request.getPhoneNumber().trim());
        vendor.setAadhaarNumber(request.getAadhaarNumber());
        vendor.setAadhaarFrontImage(request.getAadhaarFrontImage());
        vendor.setAadhaarBackImage(request.getAadhaarBackImage());
        vendor.setVerificationDocument(request.getVerificationDocument());

        vendorRepository.save(vendor);

        ServiceCategory serviceCategory = serviceCategoryRepository
                .findById(request.getService().getServiceCategoryId())
                .orElseThrow(() ->
                        new ServiceNotFoundException("Service not found."));

        String durationText = request.getService().getDuration().trim();

        Duration duration;

        try {

            LocalTime time = LocalTime.parse(durationText);

            duration = Duration.ofHours(time.getHour())
                    .plusMinutes(time.getMinute());

        } catch (DateTimeParseException ex) {

            throw new InvalidDurationException(
                    "Duration must be in HH:mm format (e.g. 02:30).");
        }

        if (duration.isZero()) {
            throw new InvalidDurationException(
                    "Duration must be greater than zero.");
        }

        VendorService vendorService = new VendorService();

        vendorService.setVendor(vendor);
        vendorService.setServiceCategory(serviceCategory);
        vendorService.setPrice(request.getService().getPrice());
        vendorService.setDuration(duration);

        vendorServiceRepository.save(vendorService);

        VendorRegistrationResponse response = new VendorRegistrationResponse();
        response.setVendorId(vendor.getId());
        response.setVendorName(user.getName());
        response.setStatus(vendor.getStatus());

        return response;
    }
}
