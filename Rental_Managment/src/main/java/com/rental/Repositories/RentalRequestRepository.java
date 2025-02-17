package com.rental.Repositories;

import com.rental.Entities.RentalRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {

    List<RentalRequest> findByUserId(Long userId);
    List<RentalRequest> findByRequestType(String requestType);

    @Modifying
    @Transactional
    @Query("DELETE FROM RentalRequest r WHERE r.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);


    @Modifying
    @Query("DELETE FROM RentalRequest r WHERE r.property.id IN (SELECT p.id FROM Property p WHERE p.owner.id = :ownerId)")
    void deleteByPropertyOwnerId(@Param("ownerId") Long ownerId);

    @Query("SELECT r FROM RentalRequest r WHERE r.property.owner.id = :ownerId")
    List<RentalRequest> findByPropertyOwnerId(@Param("ownerId") Long ownerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM RentalRequest r WHERE r.property.id = :propertyId")
    void deleteByPropertyId(@Param("propertyId") Long propertyId);
}