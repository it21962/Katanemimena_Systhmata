package com.rental.controllers;

import com.rental.Entities.User;
import com.rental.Repositories.UserRepository;
import com.rental.Repositories.PropertyRepository;
import com.rental.Repositories.RentalRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")  // 🔹 Σωστό prefix για το API
public class UserController {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final RentalRequestRepository rentalRequestRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          PropertyRepository propertyRepository,
                          RentalRequestRepository rentalRequestRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.rentalRequestRepository = rentalRequestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        System.out.println("📩 Νέα προσπάθεια εγγραφής: " + user.getName());

        Map<String, String> response = new HashMap<>();

        // 🔹 Έλεγχος για "owner" και "tenant" στο όνομα
        if ((user.getName().toLowerCase().contains("owner") && "tenant".equals(user.getRole())) ||
                (user.getName().toLowerCase().contains("tenant") && "owner".equals(user.getRole()))) {
            response.put("success", "false");
            response.put("message", "Το όνομα χρήστη δεν μπορεί να περιέχει 'owner' αν επιλέξατε Ενοικιαστής ή 'tenant' αν επιλέξατε Ιδιοκτήτης!");
            return ResponseEntity.badRequest().body(response);
        }

        // 🔹 Έλεγχος αν ο χρήστης υπάρχει ήδη
        if (userRepository.findByName(user.getName()) != null) {
            response.put("success", "false");
            response.put("message", "Το όνομα χρήστη υπάρχει ήδη.");
            return ResponseEntity.badRequest().body(response);
        }

        // 🔹 Κρυπτογράφηση κωδικού
        if (passwordEncoder != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            System.out.println("⚠️ PasswordEncoder είναι null!");
        }

        // 🔹 Αποθήκευση στη βάση
        userRepository.save(user);
        System.out.println("✅ Εγγραφή επιτυχής για: " + user.getName());

        response.put("success", "true");
        response.put("message", "Εγγραφή επιτυχής!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByNameAndId(@RequestParam String name, @RequestParam Long id) {
        System.out.println("🔍 Αναζήτηση χρήστη: " + name + " με ID: " + id);
        User user = userRepository.findByNameAndId(name, id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/users/all")  // 🔹 Σωστό endpoint για την ανάκτηση χρηστών
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("📦 Ανάκτηση όλων των χρηστών...");
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long userId) {
        Map<String, String> response = new HashMap<>();

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            response.put("success", "false");
            response.put("message", "❌ Ο χρήστης δεν βρέθηκε!");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userOptional.get();

        // 🔹 Διαγραφή όλων των αιτήσεων ενοικίασης (rental_requests)
        rentalRequestRepository.deleteByUserId(userId);

        // 🔹 Διαγραφή όλων των ακινήτων (properties) που ανήκουν στον χρήστη
        propertyRepository.deleteByUserId(userId);

        // 🔹 Διαγραφή του ίδιου του χρήστη
        userRepository.deleteById(userId);

        response.put("success", "true");
        response.put("message", "✅ Ο χρήστης και όλα τα δεδομένα του διαγράφηκαν!");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get-current-user-id")
    public ResponseEntity<Map<String, Long>> getCurrentUserId(@RequestParam String username) {
        User user = userRepository.findByName(username);

        if (user != null) {
            Map<String, Long> response = new HashMap<>();
            response.put("userId", user.getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
