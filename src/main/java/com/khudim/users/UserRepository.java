package com.khudim.users;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Beaver.
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserRepository() {
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll",User.class);
        return query.getResultList();
    }


    public User getUser(String name){
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Argument 'name' must not be NULL.");
        }
        return entityManager.find(User.class, name);
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public void deleteUser(String userName) {
        entityManager.remove(getUser(userName));
    }

    public void edit(User user) {
        entityManager.merge(user);
    }
}
