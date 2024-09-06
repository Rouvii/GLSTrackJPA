package org.example.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.HibernateConfig;
import org.example.persistence.entities.Location;
import org.example.persistence.entities.Package;
import org.example.persistence.entities.Shipment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentDAOTest {

    static ShipmentDAO shipmentDAO;

    static EntityManagerFactory emfTest;

    Package p1, p2;
    Location l1, l2;
    @Enumerated(EnumType.STRING)
    private Package.DeliveryStatus deliveryStatus;

    public enum DeliveryStatus {
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }


    @BeforeAll
   static void setUpAll() {
        emfTest = HibernateConfig.getEntityManagerFactoryForTest();
        shipmentDAO = new ShipmentDAO();
    }
@BeforeEach
    void setUp() {
        try(EntityManager em = emfTest.createEntityManager()) {

            p1 = Package.builder().trackingNumber("123").senderName("Kevin").receiverName("Rouvi").deliveryStatus(Package.DeliveryStatus.PENDING).build();
            p2 = Package.builder().trackingNumber("124").senderName("Fred 2").receiverName("Fred 1").deliveryStatus(Package.DeliveryStatus.PENDING).build();
            l1 = Location.builder().latitude(123.123).longitude(123.123).address("123 Main St").build();
            l2 = Location.builder().latitude(32.123).longitude(45.123).address("456 Elm St").build();

            em.getTransaction().begin();
            em.createNativeQuery("DROP TABLE Shipment").executeUpdate();
            em.createNativeQuery("DROP TABLE Location").executeUpdate();
            em.createNativeQuery("DROP TABLE Package").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(l1);
            em.persist(l2);
            em.getTransaction().commit();
        }



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {

            Shipment shipment = Shipment.builder().aPackage(p1).sourceLocation(l1).destinationLocation(l2).shipmentDate(java.time.LocalDateTime.now()).build();
            shipmentDAO.create(shipment);
            assertTrue(shipment.getId() > 0);


    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getByPackageId() {
    }

    @Test
    void getBySourceLocationId() {
    }

    @Test
    void getByDestinationLocationId() {
    }
}