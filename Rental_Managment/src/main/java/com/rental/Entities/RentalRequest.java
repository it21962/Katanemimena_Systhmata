package com.rental.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rental_requests") // Το όνομα του πίνακα στη βάση μας
public class RentalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Αντιστοιχία με τη στήλη "user_id" στη βάση
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id") // Αντιστοιχία με τη στήλη "property_id" στη βάση
    private Property property;

    @Column(name = "request_date") // Αντιστοιχία με τη στήλη "request_date" στη βάση
    private Date requestDate;

    @Column(name = "status") // Αντιστοιχία με τη στήλη "status" στη βάση
    private String status;

    @Column(name = "request_type") // Αντιστοιχία με τη στήλη "request_type" στη βάση
    private String requestType;

}