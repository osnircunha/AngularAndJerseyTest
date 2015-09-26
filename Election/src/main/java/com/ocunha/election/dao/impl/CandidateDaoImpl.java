package com.ocunha.election.dao.impl;

import com.ocunha.election.dao.CandidateDao;
import com.ocunha.election.dao.Dao;
import com.ocunha.election.dao.HibernateElectionDaoSupport;
import com.ocunha.election.object.Candidate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by osnircunha on 8/30/15.
 */
@Repository("candidateDao")
public class CandidateDaoImpl extends HibernateElectionDaoSupport implements CandidateDao {

    public void save(Candidate candidate) {
        getSession().save(candidate);
    }

    public void update(Candidate candidate) {
        getSession().merge(candidate);
    }

    public void delete(Long id) {
        getSession().delete(getSession().get(Candidate.class, id));
        getSession().flush();
    }

    public Candidate findById(Long id) {
        return (Candidate) getSession().get(Candidate.class, id);
    }

    public List<Candidate> list() {
        return getSession().createCriteria(Candidate.class).list();
    }
}
