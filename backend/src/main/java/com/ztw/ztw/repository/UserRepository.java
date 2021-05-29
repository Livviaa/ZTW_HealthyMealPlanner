package com.ztw.ztw.repository;

import com.ztw.ztw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "SELECT DISTINCT email FROM Users u",
            nativeQuery = true)
    List<String> getAllEmails();

    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    User findByEmail(String email);
}
