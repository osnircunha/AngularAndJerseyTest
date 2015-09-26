package com.ocunha.election.dao;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by osnircunha on 8/30/15.
 */
public abstract class HibernateElectionDaoSupport {

    private SessionFactory sessionFactory;
    private Session session;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        if(session == null){
            this.session = sessionFactory.openSession();
        }
        return session;
    }
}
