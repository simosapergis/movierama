package com.example.movierama.config;

import com.example.movierama.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailService(BCryptPasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("spring@spring.com")
                .password(passwordEncoder.encode("user1234"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        .requestMatchers("/", "/movies/**", "/register/**", "registration_success/**", "/process_register/**", "/error/**", "/webjars/**", "/resources/**", "/css/**", "/js/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                                        .and()
                                        .formLogin()
                                        .permitAll()
                                        .defaultSuccessUrl("/movies")
                                        .usernameParameter("email")
                                        .permitAll()
                                        .and()
                                        .logout()
                                        .logoutSuccessUrl("/movies").permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .httpBasic(withDefaults())
                .authenticationProvider(authenticationProvider())
                .cors()
                .and()
                .csrf()
                .disable();

        return http.build();
    }


    //@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/webjars/**","/resources/**","/css/**", "/js/**", "/register/**", "registration_success/**", "/process_register/**", "/error/**");
    }

}
