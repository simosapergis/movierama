package com.example.movierama.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean processRegistration(User user) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        final Optional<User> createdUser = Optional.of(userRepository.save(user));

        if (!createdUser.isPresent())
            throw new RuntimeException("User was not created");

        return true;
    }
}
