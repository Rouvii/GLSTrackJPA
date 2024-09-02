package org.example.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.HibernateConfig;
import org.example.persistence.entities.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Rouvi
 */


public class PackageDAO implements IDAO<Package> {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    @Override
    public void create(Package aPackage) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(aPackage);
            em.getTransaction().commit();
        }
    }

    @Override
    public Package getById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Package.class, id);
        }
    }

    //The dumb way of doing it
   /* public List<Package> getByTrackingNumber(String trackingNumber) {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Package> query = em.createQuery("SELECT p FROM Package p", Package.class);
            List<Package> packages = query.getResultList();
            List<Package> packageList = new ArrayList<>();
            packages.forEach(apackage ->{
                if(apackage.getTrackingNumber().equals(trackingNumber)){
                    packageList.add(apackage);
                }
            });
            return packageList;

        }
    }
    */
    public Package getByTrackingNumber(String trackingNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Package> query = em.createQuery("SELECT p FROM Package p WHERE p.trackingNumber = :trackingNumber", Package.class);
            query.setParameter("trackingNumber", trackingNumber);
            Package result = query.getSingleResult();
            System.out.println(result);
            return result;
        }
    }

    @Override
    public Set<Package> getAll() {
        return null;
    }

    @Override
    public void update(Package aPackage) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(aPackage);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Package aPackage) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(aPackage);
            em.getTransaction().commit();
        }
    }

    public static void main(String[] args) {

    }
/*
    public void setDeliveryStatus(String number, Package.DeliveryStatus deliveryStatus) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Package aPackage = getByTrackingNumber(number);
            aPackage.setDeliveryStatus(deliveryStatus);
            em.merge(aPackage);
            em.getTransaction().commit();
        }
    }
*/


}
