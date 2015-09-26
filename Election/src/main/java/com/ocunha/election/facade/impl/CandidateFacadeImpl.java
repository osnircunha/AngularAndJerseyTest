package com.ocunha.election.facade.impl;

import com.ocunha.election.dao.CandidateDao;
import com.ocunha.election.dao.Dao;
import com.ocunha.election.facade.CandidateFacade;
import com.ocunha.election.facade.Facade;
import com.ocunha.election.object.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by osnircunha on 8/30/15.
 */
@Service("candidateFacade")
public class CandidateFacadeImpl implements CandidateFacade{

    @Autowired
    @Qualifier("candidateDao")
    private CandidateDao candidateDao;

    public void save(Candidate candidate) {
        candidateDao.save(candidate);
    }

    public void update(Candidate candidate) {
        candidateDao.update(candidate);
    }

    public void delete(Long id) {
        candidateDao.delete(id);
    }

    public Candidate findById(Long id) {
        return candidateDao.findById(id);
    }

    public List<Candidate> list() {
        return candidateDao.list();
    }

}

