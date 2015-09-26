package com.ocunha.election.facade;

import com.ocunha.election.object.User;

/**
 * Created by osnircunha on 9/25/15.
 */
public interface UserFacade extends Facade<User> {

    User findByName(String name);
}
