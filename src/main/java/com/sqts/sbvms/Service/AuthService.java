package com.sqts.sbvms.Service;

import com.sqts.sbvms.Dto.RegisterRequest;
import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Exception.InvalidInputException;
import com.sqts.sbvms.Exception.PasswordMismatchedException;
import com.sqts.sbvms.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User register(RegisterRequest request){
        if(request==null)
            throw new InvalidInputException("Enter valid email and password.");
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new PasswordMismatchedException("Password do not match.");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }
}
