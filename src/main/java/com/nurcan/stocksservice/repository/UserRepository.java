package com.nurcan.stocksservice.repository;

import com.nurcan.stocksservice.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = ?1")
    User findByUsername(String emailAddress);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String emailAddress);
}
