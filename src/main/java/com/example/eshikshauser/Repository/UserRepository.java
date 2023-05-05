package com.example.eshikshauser.Repository;

import com.example.eshikshauser.Constants.EntityConstants;
import com.example.eshikshauser.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = EntityConstants.updateUser)
    void updateUser(String name, String email, Integer gender,String password);
}
