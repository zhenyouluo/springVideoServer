package com.khudim.services;

import com.khudim.main.Users;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



@Service("userService")
@Transactional
public class UserService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public UserService() {
    }

    public List<Users> getAll(){
        logger.debug("Retrieving all users");

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM Users");

        return query.list();
    }

    public Users get(int id){
        Session session = sessionFactory.getCurrentSession();

        Users user = (Users)session.get(Users.class,id);

        return user;
    }

    public void add(Users user){
        Session session = sessionFactory.getCurrentSession();

        session.save(user);

    }

    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Users user = (Users)session.get(Users.class,id);
        session.delete(user);
    }

    public void edit(Users user){
        Session session = sessionFactory.getCurrentSession();
        Users existingUser = (Users)session.get(Users.class, user.getId());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        session.save(existingUser);
    }




}
