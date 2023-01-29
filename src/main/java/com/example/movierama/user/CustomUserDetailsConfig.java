package com.example.movierama.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomUserDetailsConfig {

    @Bean(name = "auth")
    public Authenticatable isUserAuthenticated(){
        return CustomUserDetails::isUserAuthenticated;
    }

    @Bean(name = "authenticated")
    public AuthenticatableUserDetails authenticated() {
       return ()-> CustomUserDetails.getAuthenticatedUser().getName()
               .concat(" ")
               .concat(CustomUserDetails.getAuthenticatedUser().getLastName());
    }

    @Bean(name = "authenticatedUserId")
    public AuthenticatedUserId getId() {
        return ()-> CustomUserDetails.getAuthenticatedUser().getId();
    }


    private interface Authenticatable {
        boolean isUserAuthenticated();
    }

    private interface AuthenticatableUserDetails {
        String getAuthenticatedUserFullName();
    }

    private interface AuthenticatedUserId {
        Long getId();
    }
}
