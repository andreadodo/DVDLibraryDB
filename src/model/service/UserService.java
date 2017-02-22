package model.service;

import model.entities.UserItemEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by andrea on 20/02/17.
 */
public class UserService {
    private static UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DVDdb");

    private UserService() {
    }

    public boolean loginUser (UserItemEntity userItemEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<UserItemEntity> UserDb = entityManager.createQuery("SELECT UserItem from UserItemEntity UserItem").getResultList();

        for (UserItemEntity userItem: UserDb) {
            if (userItem.equals(userItemEntity)){
                entityManager.close();
                return true;
            }
        }
        return false;
    }
}
