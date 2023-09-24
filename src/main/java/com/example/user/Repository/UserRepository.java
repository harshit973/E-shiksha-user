package com.example.user.Repository;

import com.example.user.Constants.EntityConstants;
import com.example.user.Entity.User;
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
    void updateUser(Long id, String name, String email, Integer gender, String password);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = EntityConstants.deleteUser)
    void deleteUser(Long id);

}
