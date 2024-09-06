package org.example;

import org.example.persistence.daos.LocationDAO;
import org.example.persistence.daos.PackageDAO;
import org.example.persistence.daos.ShipmentDAO;
import org.example.persistence.entities.Location;
import org.example.persistence.entities.Package;
import org.example.persistence.entities.Shipment;

public class Main {
    public static void main(String[] args) {
        PackageDAO packageDAO = new PackageDAO();
        LocationDAO locationDAO = new LocationDAO();
        ShipmentDAO shipmentDAO = new ShipmentDAO();
        packageDAO.create(Package.builder().trackingNumber("124")
                .senderName("Rouvi")
                .receiverName("Fred")
                .deliveryStatus(Package.DeliveryStatus.PENDING)
                .build());



        Location source = new Location();
        source.setLatitude(123.123);
        source.setLongitude(123.123);
        source.setAddress("123 Main St");
        locationDAO.create(source);

        Location destination = new Location();
        destination.setLatitude(32.123);
        destination.setLongitude(45.123);
        destination.setAddress("456 Elm St");
        locationDAO.create(destination);

        Shipment shipment = new Shipment();
        shipment.setSourceLocation(source);
        shipment.setDestinationLocation(destination);
        shipment.setShipmentDate(java.time.LocalDateTime.now());
       shipment.setAPackage(packageDAO.getByTrackingNumber("124"));
        shipmentDAO.create(shipment);



        //packageDAO.setDeliveryStatus("126", Package.DeliveryStatus.IN_TRANSIT);
        // packageDAO.getByTrackingNumber("126");
        // packageDAO.delete(packageDAO.getByTrackingNumber("126"));
    }

}