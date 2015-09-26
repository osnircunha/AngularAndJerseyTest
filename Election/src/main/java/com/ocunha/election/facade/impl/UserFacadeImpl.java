package com.ocunha.election.facade.impl;

import com.ocunha.election.dao.UserDao;
import com.ocunha.election.facade.UserFacade;
import com.ocunha.election.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by osnircunha on 9/25/15.
 */
@Service("userFacade")
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserDao userDao;

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public List<User> list() {
        return userDao.list();
    }
}
