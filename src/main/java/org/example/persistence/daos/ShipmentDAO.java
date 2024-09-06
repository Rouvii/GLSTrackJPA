// src/main/java/org/example/persistence/daos/ShipmentDAO.java
package org.example.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.HibernateConfig;
import org.example.persistence.daos.interfaces.IDAO;
import org.example.persistence.daos.interfaces.IShipMentDAO;
import org.example.persistence.entities.Shipment;

import java.util.HashSet;
import java.util.Set;

public class ShipmentDAO implements IDAO<Shipment>, IShipMentDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    @Override
    public void create(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(shipment);
            em.getTransaction().commit();
        }
    }

    @Override
    public Shipment getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Shipment.class, id);
        }
    }

    @Override
    public Set<Shipment> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return new HashSet<>(em.createQuery("SELECT s FROM Shipment s", Shipment.class).getResultList());
        }
    }

    @Override
    public void update(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(shipment);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.contains(shipment) ? shipment : em.merge(shipment));
            em.getTransaction().commit();
        }
    }


    @Override
    public Set<Shipment> getByPackageId(int packageId) {
        try (EntityManager em = emf.createEntityManager()) {
            return new HashSet<>(em.createQuery("SELECT s FROM Shipment s WHERE s.aPackage.id = :packageId", Shipment.class)
                    .setParameter("packageId", packageId)
                    .getResultList());
        }
    }

    @Override
    public Set<Shipment> getBySourceLocationId(int sourceLocationId) {
        try (EntityManager em = emf.createEntityManager()) {
            return new HashSet<>(em.createQuery("SELECT s FROM Shipment s WHERE s.sourceLocation.id = :sourceLocationId", Shipment.class)
                    .setParameter("sourceLocationId", sourceLocationId)
                    .getResultList());
        }
    }


    @Override
    public Set<Shipment> getByDestinationLocationId(int destinationLocationId) {
        try (EntityManager em = emf.createEntityManager()) {
            return new HashSet<>(em.createQuery("SELECT s FROM Shipment s WHERE s.destinationLocation.id = :destinationLocationId", Shipment.class)
                    .setParameter("destinationLocationId", destinationLocationId)
                    .getResultList());
        }
    }
}