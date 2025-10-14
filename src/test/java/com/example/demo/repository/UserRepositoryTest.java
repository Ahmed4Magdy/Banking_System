package com.example.demo.repository;

import com.example.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest //Spring Boot test that loads only the JPA layer and uses an in-memory H2 database by default.
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup() {

        user = new User(null,"Ahmed Magdy", "ahmed@gmail.com", "21312", User.Role.CUSTOMER, null);



    }


    @Test
    void CreateUser() {

        userRepository.save(user);

        Optional<User> user1 = userRepository.findByEmail("ahmed@gmail.com");
        Optional<User> user2 = userRepository.findByName("Ahmed Magdy");
        Optional<User> user3 = userRepository.findById(1L);


        assertThat(user1).isPresent();
        assertThat(user2).isPresent();
        assertThat(user3).isPresent();


    }




}
