package org.example;

import org.example.persistence.daos.PackageDAO;
import org.example.persistence.entities.Package;

public class Main {
    public static void main(String[] args) {
        PackageDAO packageDAO = new PackageDAO();
       /* packageDAO.create(Package.builder().trackingNumber("124")
                .senderName("Rouvi")
                .receiverName("Fred")
                .deliveryStatus(Package.DeliveryStatus.PENDING)
                .build());
        */

        //packageDAO.setDeliveryStatus("126", Package.DeliveryStatus.IN_TRANSIT);
        // packageDAO.getByTrackingNumber("126");
        // packageDAO.delete(packageDAO.getByTrackingNumber("126"));
    }

}