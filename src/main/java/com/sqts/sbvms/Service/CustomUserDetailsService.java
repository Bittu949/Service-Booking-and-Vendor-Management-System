package com.sqts.sbvms.Service;

import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Exception.UserNotFoundException;
import com.sqts.sbvms.Repository.UserRepository;
import com.sqts.sbvms.Security.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null)
            throw new UserNotFoundException("User not found.");
        return new CustomUserDetails(user);
    }
}
