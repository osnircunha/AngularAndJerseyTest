package com.ocunha.election.facade;

import java.util.List;

/**
 * Created by osnircunha on 9/24/15.
 */
public interface Facade<T> {

    public void save(T t);

    public void update(T t);

    public void delete(Long id);

    public T findById(Long id);

    public List<T> list();

}
