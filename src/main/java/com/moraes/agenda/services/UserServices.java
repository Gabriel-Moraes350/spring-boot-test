package com.moraes.agenda.services;

import com.moraes.agenda.models.User;
import com.moraes.agenda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository repository;

    public Optional<User> findByUsername(String username){
        return repository.findByUsername(username);
    }
}
