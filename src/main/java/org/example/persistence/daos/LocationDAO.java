package org.example.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.HibernateConfig;
import org.example.persistence.daos.interfaces.IDAO;
import org.example.persistence.daos.interfaces.ILocationDAO;
import org.example.persistence.entities.Location;
import org.example.persistence.entities.Package;

import java.util.Set;

/**
 * @author Rouvi
 */


public class LocationDAO implements IDAO<Location>, ILocationDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    @Override
    public void create(Location location) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
        }

    }

    @Override
    public Location getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Location.class, id);
        }

    }

    @Override
    public Set<Location> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return (Set<Location>) em.createQuery("SELECT l FROM Location l").getResultList();
        }
    }

    @Override
    public void update(Location location) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(location);
            em.getTransaction().commit();
        }

    }

    @Override
    public void delete(Location location) {

    }

    @Override
    public double getLongitude() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Location.class, 1).getLongitude();
        }
    }

    @Override
    public double getLatitude() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Location.class, 1).getLatitude();
        }
    }

    @Override
    public String getAddress() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Location.class, 1).getAddress();
        }
    }

    @Override
    public void setLongitude(double longitude) {
        try (EntityManager em = emf.createEntityManager()) {
            Location location = em.find(Location.class, 1);
            em.getTransaction().begin();
            location.setLongitude(longitude);
            em.getTransaction().commit();
        }
    }

    @Override
    public void setLatitude(double latitude) {
        try (EntityManager em = emf.createEntityManager()) {
            Location location = em.find(Location.class, 1);
            em.getTransaction().begin();
            location.setLatitude(latitude);
            em.getTransaction().commit();
        }
    }

    @Override
    public void setAddress(String address) {
        try (EntityManager em = emf.createEntityManager()) {
            Location location = em.find(Location.class, 1);
            em.getTransaction().begin();
            location.setAddress(address);
            em.getTransaction().commit();
        }

    }

}
