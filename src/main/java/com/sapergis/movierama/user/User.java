package com.sapergis.movierama.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity(name = "m_user")
@Table
@NoArgsConstructor
@Data
public class User {
    @Id
    @SequenceGenerator(name = "m_user_sequence", sequenceName = "m_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_user_sequence")
    private Long id;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Size(min = 5, max = 45, message = "Email length should be between 5 and 45 characters")
    @NotEmpty(message = "Email cannot be empty")
    @Column(unique = true, length = 45)
    private String email;
    @Size(min = 4, max = 64, message = "Password length should be between 4 and 64 characters" )
    @NotEmpty(message = "Password cannot be empty")
    @Column(length = 64)
    private String password;
    @Size(max = 20, message = "Name length should not exceed 20 characters" )
    @NotEmpty(message = "Name cannot be empty")
    @Column(nullable = false)
    private String name;
    @Size(max = 20, message = "Name length should not exceed 20 characters" )
    @NotEmpty(message = "Lastname cannot be empty")
    @Column(nullable = false)
    private String lastName;

    public User(String email, String password, String name, String lastName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }


}
