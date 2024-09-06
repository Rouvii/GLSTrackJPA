package org.example.persistence.daos.interfaces;

import org.example.persistence.entities.Package;
import org.example.persistence.entities.Shipment;

import java.util.Set;

public interface IShipMentDAO {


    Set<Shipment> getByPackageId(int packageId);
    Set<Shipment> getBySourceLocationId(int sourceLocationId);
    Set<Shipment> getByDestinationLocationId(int destinationLocationId);




}
