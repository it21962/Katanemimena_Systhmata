package com.rental.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Όνομα του πίνακα στη βάση μας.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Όνομα χρήστη
    private String email;
    private String password;

    private String role; // Ρόλος χρήστη (tenant, owner, admin)
}
