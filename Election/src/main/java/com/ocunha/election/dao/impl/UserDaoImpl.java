package com.ocunha.election.dao.impl;

import com.ocunha.election.dao.Dao;
import com.ocunha.election.dao.HibernateElectionDaoSupport;
import com.ocunha.election.dao.UserDao;
import com.ocunha.election.object.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by osnircunha on 9/25/15.
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateElectionDaoSupport implements UserDao{

    public void save(User user) {
        getSession().save(user);
    }

    public void update(User user) {
        getSession().merge(user);
    }

    public void delete(Long id) {
        getSession().delete(getSession().get(User.class, id));
        getSession().flush();
    }

    public User findById(Long id) {
        return (User) getSession().get(User.class, id);
    }

    public List<User> list() {
        return getSession().createCriteria(User.class).list();
    }

    public User findByName(String name) {
        return (User) getSession().createQuery("from User u where u.name = :name").setString("name", name).uniqueResult();
    }
}
