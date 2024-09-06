package org.example.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double latitude;

    private double longitude;

    private String address;

    @OneToMany(mappedBy = "sourceLocation")
    private Set<Shipment> shipmentsSource = new HashSet<>();
    @OneToMany(mappedBy = "destinationLocation")
    private Set<Shipment> shipmentsDestination = new HashSet<>();

}
