package org.example.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.HibernateConfig;
import org.example.persistence.daos.interfaces.IDAO;
import org.example.persistence.entities.Package;

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
            return  query.setParameter("trackingNumber", trackingNumber). getSingleResult();
        }

    }



    @Override
    public Set<Package> getAll() {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Package> query = em.createQuery("SELECT p FROM Package p", Package.class);
            return query.getResultList().stream().collect(java.util.stream.Collectors.toSet());


        }

    }

    @Override
    public void update(Package aPackage) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Package existingPackage = em.find(Package.class, aPackage.getId());
            if(existingPackage != null){
                existingPackage.setDeliveryStatus(aPackage.getDeliveryStatus());
                em.merge(aPackage);
            }
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



    public void setDeliveryStatus(String number, Package.DeliveryStatus deliveryStatus) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Package aPackage = getByTrackingNumber(number);
            aPackage.setDeliveryStatus(deliveryStatus);
            em.merge(aPackage);
            em.getTransaction().commit();
        }
    }





}
