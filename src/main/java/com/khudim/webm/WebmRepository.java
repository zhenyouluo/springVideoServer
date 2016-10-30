package com.khudim.webm;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Beaver.
 */
@Component
public class WebmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public WebmRepository() {
    }

    public List<Webm> getAllWebm() {
        return entityManager.createNamedQuery("Webm.findAll", Webm.class).getResultList();
    }

    public void addWebm(Webm webm) {
        entityManager.merge(webm);
    }

    public Webm getWebm(int id) {
        return null;
    }

    public Webm getWebmPath(String path) {
        try {
            return entityManager.createNamedQuery("Webm.findByPath", Webm.class)
                    .setParameter("path", path)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
