package com.ocunha.election.dao.impl;

import com.ocunha.election.dao.Dao;
import com.ocunha.election.dao.HibernateElectionDaoSupport;
import com.ocunha.election.object.Candidate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by osnircunha on 8/30/15.
 */
@Component
public class CandidateDao extends HibernateElectionDaoSupport implements Dao<Candidate> {

    public void save(Candidate candidate) {
        getSession().save(candidate);
    }

    public void update(Candidate candidate) {
        getSession().update(candidate);
    }

    public void delete(Candidate candidate) {
        getSession().delete(candidate);
    }

    public Candidate findById(Long id) {
        return (Candidate) getSession().get(Candidate.class, id);
    }

    public List<Candidate> list() {
        return getSession().createQuery("from Candidate c").list();
    }
}
