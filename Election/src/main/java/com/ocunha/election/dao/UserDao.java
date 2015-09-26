package com.ocunha.election.dao;

import com.ocunha.election.object.User;

/**
 * Created by osnircunha on 9/25/15.
 */
public interface UserDao extends Dao<User>{

    User findByName(String name);
}
