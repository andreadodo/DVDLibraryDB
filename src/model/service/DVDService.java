package model.service;

import model.entities.DvdItemEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 * Created by andrea on 20/02/17.
 */
public class DVDService {
    private static DVDService ourInstance = new DVDService();

    public static DVDService getInstance() {
        return ourInstance;
    }

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DVDdb");

    private DVDService() {
    }

    public boolean addDvd(DvdItemEntity dvdItemEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<DvdItemEntity> DVDCollection = getAllDvd();

        for (DvdItemEntity dvd: DVDCollection) {
            if (dvd.equals(dvdItemEntity)){
                entityManager.close();
                return true;
            }
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dvdItemEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("TRANSACTION ERROR:" + e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return false;
    }

    public void editDvd(DvdItemEntity dvdItemEntity){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(dvdItemEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("TRANSACTION ERROR:" + e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }

    public void delDvd(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(DvdItemEntity.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("TRANSACTION ERROR:" + e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }
    public DvdItemEntity getDvd(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        DvdItemEntity dvd = entityManager.find(DvdItemEntity.class, id);
        entityManager.close();
        return dvd;
    }

    public List<DvdItemEntity> getAllDvd() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<DvdItemEntity> DVDCollection;
        DVDCollection = entityManager.createQuery("SELECT DvdItem from DvdItemEntity DvdItem").getResultList();
        entityManager.close();
        return DVDCollection;
    }
}
