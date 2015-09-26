package com.ocunha.election.dao.impl;

import com.ocunha.election.dao.HibernateElectionDaoSupport;
import com.ocunha.election.dao.PositionDao;
import com.ocunha.election.object.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by osnircunha on 9/24/15.
 */
@Repository("positionDao")
public class PositionDaoImpl extends HibernateElectionDaoSupport implements PositionDao {

    public void save(Position position) {
        getSession().save(position);
    }

    public void update(Position position) {
        getSession().merge(position);
    }

    public void delete(Long id) {
        getSession().delete(getSession().get(Position.class, id));
        getSession().flush();
    }

    public Position findById(Long id) {
        return (Position) getSession().get(Position.class, id);
    }

    public List<Position> list() {
        return getSession().createCriteria(Position.class).list();
    }
}
