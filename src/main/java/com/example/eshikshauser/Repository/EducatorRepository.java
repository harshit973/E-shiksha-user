package com.example.eshikshauser.Repository;

import com.example.eshikshauser.Constants.EntityConstants;
import com.example.eshikshauser.Entity.Educator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;

public interface EducatorRepository extends JpaRepository<Educator, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = EntityConstants.updateEducator)
    void updateRating(Long id, Integer rating);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = EntityConstants.deleteEducator)
    void deleteEducator(Long id);
    @Query(nativeQuery = true,value = EntityConstants.findEducatorByUserEmail)
    List<Tuple> findEducatorByUserEmail(String email);
}
