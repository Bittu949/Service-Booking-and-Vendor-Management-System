package com.sqts.sbvms.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    String name;
    @Email(message = "Invalid email address")
    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    String confirmPassword;
}
