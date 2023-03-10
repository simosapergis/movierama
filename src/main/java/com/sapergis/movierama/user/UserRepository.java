package com.sapergis.movierama.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    @Query("SELECT u FROM m_user u WHERE u.email = ?1")
    User findByEmail(String email);
}
