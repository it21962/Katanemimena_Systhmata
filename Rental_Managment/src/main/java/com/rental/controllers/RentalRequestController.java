package com.rental.controllers;

import com.rental.Entities.Property;
import com.rental.Entities.RentalRequest;
import com.rental.Repositories.PropertyRepository;
import com.rental.Repositories.RentalRequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tenant/rental-requests")
public class RentalRequestController {

    private final RentalRequestRepository rentalRequestRepository;
    private final PropertyRepository propertyRepository;

    public RentalRequestController(RentalRequestRepository rentalRequestRepository, PropertyRepository propertyRepository) {
        this.rentalRequestRepository = rentalRequestRepository;
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public List<RentalRequest> getAllRentalRequests() {
        return rentalRequestRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<RentalRequest> createRentalRequest(@RequestBody RentalRequest rentalRequest) {
        System.out.println("Νέα αίτηση: " + rentalRequest);

        if (rentalRequest.getUser() == null || rentalRequest.getProperty() == null) {
            return ResponseEntity.badRequest().build();
        }

        RentalRequest savedRequest = rentalRequestRepository.save(rentalRequest);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<List<RentalRequest>> getRentalRequestsByUser(@RequestParam Long userId) {
        List<RentalRequest> userRequests = rentalRequestRepository.findByUserId(userId);
        return ResponseEntity.ok(userRequests);
    }

    @GetMapping("/listings")
    public ResponseEntity<List<RentalRequest>> getListingRequests() {
        List<RentalRequest> listings = rentalRequestRepository.findByRequestType("listing");
        return ResponseEntity.ok(listings);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RentalRequest> updateRequestStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        return rentalRequestRepository.findById(id).map(rentalRequest -> {
            String newStatus = request.get("status");
            rentalRequest.setStatus(newStatus);
            rentalRequestRepository.save(rentalRequest);

            //Αν η αίτηση εγκρίνεται, ενημερώνουμε και το ακίνητο
            if ("approved".equals(newStatus)) {
                Property property = rentalRequest.getProperty();
                if (property != null) {
                    property.setStatus("rented");
                    propertyRepository.save(property);
                }
            }
            return ResponseEntity.ok(rentalRequest);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/owner")
    public ResponseEntity<List<RentalRequest>> getRentalRequestsByOwner(@RequestParam Long ownerId) {
        List<RentalRequest> requests = rentalRequestRepository.findByPropertyOwnerId(ownerId);
        return ResponseEntity.ok(requests);
    }
}