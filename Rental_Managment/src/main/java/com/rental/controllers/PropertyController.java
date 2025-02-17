package com.rental.controllers;

import com.rental.Entities.Property;
import com.rental.Entities.User;
import com.rental.Repositories.PropertyRepository;
import com.rental.Repositories.RentalRequestRepository;
import com.rental.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/owner/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final RentalRequestRepository rentalRequestRepository;

    public PropertyController(PropertyRepository propertyRepository, UserRepository userRepository, RentalRequestRepository rentalRequestRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.rentalRequestRepository = rentalRequestRepository;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        return ResponseEntity.ok(propertyRepository.save(property));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        //Διαγραφή rental requests που σχετίζονται με το ακίνητο
        rentalRequestRepository.deleteByPropertyId(id);

        //Διαγραφή του ίδιου του ακινήτου
        propertyRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-property")
    public ResponseEntity<Map<String, String>> addProperty(@RequestBody Map<String, Object> payload) {
        Map<String, String> response = new HashMap<>();

        //Ανάκτηση δεδομένων από το request
        String title = (String) payload.get("title");
        String description = (String) payload.get("description");
        String status = "available";
        Long ownerId = payload.get("ownerId") != null ? Long.valueOf(payload.get("ownerId").toString()) : null;

        if (title == null || description == null || ownerId == null) {
            response.put("success", "false");
            response.put("message", "Όλα τα πεδία είναι υποχρεωτικά!");
            return ResponseEntity.badRequest().body(response);
        }

        //Ανάκτηση του Owner από τη βάση
        Optional<User> ownerOptional = userRepository.findById(ownerId);
        if (!ownerOptional.isPresent()) {
            response.put("success", "false");
            response.put("message", "❌ Ο ιδιοκτήτης δεν βρέθηκε στη βάση!");
            return ResponseEntity.badRequest().body(response);
        }

        User owner = ownerOptional.get();

        //Δημιουργία και αποθήκευση του ακινήτου
        Property property = new Property();
        property.setTitle(title);
        property.setDescription(description);
        property.setStatus(status);
        property.setOwner(owner);

        propertyRepository.save(property);

        response.put("success", "true");
        response.put("message", "Το ακίνητο προστέθηκε επιτυχώς!");
        return ResponseEntity.ok(response);
    }
}

