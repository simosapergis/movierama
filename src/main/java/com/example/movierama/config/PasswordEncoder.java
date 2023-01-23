package com.example.movierama.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "user1234";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded password is " + encodedPassword);
    }
}
