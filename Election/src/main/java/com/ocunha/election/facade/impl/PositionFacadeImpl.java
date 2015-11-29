package com.ocunha.election.facade.impl;

import com.ocunha.election.dao.Dao;
import com.ocunha.election.dao.PositionDao;
import com.ocunha.election.facade.Facade;
import com.ocunha.election.facade.PositionFacade;
import com.ocunha.election.object.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by osnircunha on 9/24/15.
 */
@Service("positionFacade")
public class PositionFacadeImpl implements PositionFacade{

    @Autowired
    @Qualifier("positionDao")
    private PositionDao positionDao;

    public void save(Position position) {
        positionDao.save(position);
    }

    public void update(Position position) {
        positionDao.update(position);
    }

    public void delete(Long id) {
        positionDao.delete(id);
    }

    public Position findById(Long id) {
        return positionDao.findById(id);
    }

    public List<Position> list() {
        return positionDao.list();
    }
}
