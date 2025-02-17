package com.rental.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "properties") // Το όνομα του πίνακα στη βάση μας
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // Αντιστοιχία με τη στήλη "owner_id" στη βάση
    private User owner;

    @Column(name = "title", nullable = false) // Αντιστοιχία με τη στήλη "title" στη βάση
    private String title;

    @Column(name = "description") // Αντιστοιχία με τη στήλη "description" στη βάση
    private String description;

    @Column(name = "status", nullable = false) // Αντιστοιχία με τη στήλη "status" στη βάση
    private String status;

    public Long getOwnerId() {
        return owner != null ? owner.getId() : null;
    }
}
