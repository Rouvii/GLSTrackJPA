package org.example.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Rouvi
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Builder
@AllArgsConstructor

public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Package aPackage;


    @ManyToOne
    private Location sourceLocation;
    @ManyToOne
    private Location destinationLocation;

    private LocalDateTime shipmentDate;




}
