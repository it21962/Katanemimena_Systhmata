package com.rental.Repositories;

import com.rental.Entities.Property;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Property p WHERE p.owner.id = :ownerId")
    void deleteByUserId(@Param("ownerId") Long ownerId);
}