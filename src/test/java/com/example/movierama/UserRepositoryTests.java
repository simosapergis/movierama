package com.example.movierama;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.movierama.user.User;
import com.example.movierama.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUser() {
        final User user = new User();
        user.setEmail("sapergis1722@test.com");
        user.setPassword("asdf1234");
        user.setName("Simos");
        user.setLastName("Apergis");

        final User savedUser = userRepository.save(user);

        final User existUser = testEntityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
}
