package com.example.demo.config;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initUsers() {
        return args -> {
            if (userRepository.count() == 3) {

//                User user = new User();
//                user.setName("Boss");
//                user.setEmail("boss@gmail.com");
//                user.setPassword(passwordEncoder.encode("admin123"));
//                user.setRole(User.Role.ADMIN);
//                userRepository.save(user);


                User user1 = new User();
                user1.setName("aml");
                user1.setPassword(passwordEncoder.encode("aml111"));
                user1.setEmail("aml@gmail.com");
                user1.setRole(User.Role.CUSTOMER);
                userRepository.save(user1);
            }

        };

    }
}
