package com.rental.controllers;

import com.rental.Entities.RentalRequest;
import com.rental.Repositories.RentalRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant/rental-requests")
public class RentalRequestController {

    private final RentalRequestRepository rentalRequestRepository;

    public RentalRequestController(RentalRequestRepository rentalRequestRepository) {
        this.rentalRequestRepository = rentalRequestRepository;
    }

    @GetMapping
    public List<RentalRequest> getAllRentalRequests() {
        return rentalRequestRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<RentalRequest> createRentalRequest(@RequestBody RentalRequest rentalRequest) {
        return ResponseEntity.ok(rentalRequestRepository.save(rentalRequest));
    }
}
