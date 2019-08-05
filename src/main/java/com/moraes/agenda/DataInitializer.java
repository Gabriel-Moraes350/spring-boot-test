package com.moraes.agenda;

import com.moraes.agenda.models.User;
import com.moraes.agenda.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    //...

    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //...

        this.users.save(User.builder()
                .username("user@gmail.com")
                .password(this.passwordEncoder.encode("user"))
                .roles(Collections.singletonList("ROLE_USER"))
                .build()
        );

        this.users.save(User.builder()
                .username("admin@gmail.com")
                .password(this.passwordEncoder.encode("admin"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build()
        );

        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}