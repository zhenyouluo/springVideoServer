package com.khudim.services;


import com.khudim.main.Webm;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("webmService")
@Transactional
public class WebmService {



    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public WebmService() {
    }


    public List<Webm> getAll(){

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Webm");
        return query.list();
    }

    public void add(Webm webm){
        Session session = sessionFactory.getCurrentSession();

        session.save(webm);

    }

    public Webm get(int id){
        Session session = sessionFactory.getCurrentSession();

        Webm webm = (Webm)session.get(Webm.class,id);

        return webm;
    }
}
