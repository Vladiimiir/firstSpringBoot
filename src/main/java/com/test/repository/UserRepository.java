package com.test.repository;

import com.test.model.Address;
import com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User where email = ?1")
    User GetByEmail(String email);
    List<User> getAllByName(String name);
    List<User> getAllByAddress(Address address);
    User getUserByEmailAndPassword(String email, String password);
    @Query("from User where resetPasswordToken =?1")
    User getToken(String token);
}
