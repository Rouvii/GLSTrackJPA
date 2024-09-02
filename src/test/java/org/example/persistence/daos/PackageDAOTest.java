package org.example.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.HibernateConfig;
import org.example.persistence.entities.Package;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PackageDAOTest {

    private static PackageDAO packageDao;
    private static EntityManagerFactory emfTest;
   // private static EntityManager entityManager;
    Package aPackage = new Package();
    @BeforeAll
    static void setUpAll() {
        emfTest = HibernateConfig.getEntityManagerFactoryForTest();
        packageDao = new PackageDAO();
       // entityManager = emfTest.createEntityManager();
    }


    @BeforeEach
    public void setUp() {
        EntityManager entityManager = emfTest.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Package").executeUpdate();
        entityManager.persist(aPackage);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @AfterAll
    public static void tearDown() {
       // entityManager.close();
        emfTest.close();
    }

    @Test
    public void testPersistPackage() {
        Package pkg = new Package();
        pkg.setTrackingNumber("ABC123");
        pkg.setSenderName("Sender");
        pkg.setReceiverName("Receiver");
        pkg.setDeliveryStatus(Package.DeliveryStatus.PENDING);

        packageDao.create(pkg);
        assertTrue(pkg.getId()>0);

        // Retrieve the package from the database and assert its existence
        /*
        Package retrievedPackage = entityManager.find(Package.class, pkg.getId());
        Assertions.assertNotNull(retrievedPackage);
        Assertions.assertEquals("ABC123", retrievedPackage.getTrackingNumber());
        */

    }

    // Implement other test methods for CRUD operations
    @Test
    public void testGetById() {
        Package pkg = new Package();
        pkg.setTrackingNumber("XYZ123");
        pkg.setSenderName("Sender");
        pkg.setReceiverName("Receiver");
        pkg.setDeliveryStatus(Package.DeliveryStatus.PENDING);

        packageDao.create(pkg);

        Package retrievedPackage = packageDao.getById(pkg.getId());
        assertNotNull(retrievedPackage);
        assertEquals("XYZ123", retrievedPackage.getTrackingNumber());
    }
}