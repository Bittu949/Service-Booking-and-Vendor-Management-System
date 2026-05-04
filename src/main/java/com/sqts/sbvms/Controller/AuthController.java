package com.sqts.sbvms.Controller;

import com.sqts.sbvms.Dto.LoginRequest;
import com.sqts.sbvms.Dto.RegisterRequest;
import com.sqts.sbvms.Dto.ApiResponse;
import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterRequest request){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Account created successfully.",
                        authService.register(request),
                        LocalDateTime.now()),
                HttpStatus.CREATED);
    }

}
