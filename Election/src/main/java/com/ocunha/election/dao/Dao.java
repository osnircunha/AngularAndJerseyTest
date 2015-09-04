package com.ocunha.election.dao;

import java.util.List;

/**
 * Created by osnircunha on 8/30/15.
 */
public interface Dao<T> {

    void save(T t);

    void update(T t);

    void delete(T t);

    T findById(Long id);

    List<T> list();

}
