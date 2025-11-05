package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
//                .userDetailsService(customUserDetailsService)//link between ui and security ...انا حطيط ده علشان سواجر مبيعرفش يلقط تلقاءي ويشوف الداتا من الداتابيز لانه بيتعمل مع يو اي ع عكس البوست مان اسبرينج بيلقطها اوتوماتيك
                .authorizeHttpRequests(auth -> auth

                                .requestMatchers("/user/register","/user/login").permitAll()

                                .requestMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/user/{id}").hasAnyRole("ADMIN", "CUSTOMER")


                                .requestMatchers(HttpMethod.POST, "/monthly/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/monthly/**").hasAnyRole("ADMIN", "CUSTOMER")


                                .requestMatchers(HttpMethod.POST, "/transaction/**").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/transaction/account/{accountId}").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.DELETE, "/transaction/**").hasAnyRole("ADMIN")

                                //Account
                                .requestMatchers(HttpMethod.POST, "/account/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/account").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/account/{account_id}").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.GET, "/account/byuser/{userId}").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.PUT, "/account/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/account/**").hasRole("ADMIN")

                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

//                        .requestMatchers(HttpMethod.GET)
                                .anyRequest().authenticated()
                )
//                .httpBasic(Customizer.withDefaults());
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }


    // AuthenticationManager الجديد في Spring Security 6
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//        return http.build();
//    }
//
//
//
//        // AuthenticationManager الجديد في Spring Security 6
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}

