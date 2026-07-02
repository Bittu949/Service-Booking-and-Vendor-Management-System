package com.sqts.sbvms.Config;

import com.sqts.sbvms.Entity.User;
import com.sqts.sbvms.Enum.Role;
import com.sqts.sbvms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StartupConfig {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Bean
    public ApplicationRunner adminInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.existsByRole(Role.ADMIN)) {
                return;
            }

            User user = new User();
            user.setName(adminName);
            user.setEmail(adminEmail);
            user.setPassword(passwordEncoder.encode(adminPassword));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        };
    }
}