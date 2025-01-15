package com.rental.Repositories;

import com.rental.Entities.RentalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {
}
