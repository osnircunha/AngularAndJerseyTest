package com.ocunha.election;

import com.ocunha.election.dao.impl.CandidateDao;
import com.ocunha.election.object.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by osnircunha on 8/30/15.
 */
@Component
public class CandidateFacade {

    @Autowired
    private CandidateDao candidateDao;

    public void save(Candidate candidate) {
        candidateDao.save(candidate);
    }

    public void update(Candidate candidate) {
        candidateDao.update(candidate);
    }

    public void delete(Candidate candidate) {
        candidateDao.delete(candidate);
    }

    public Candidate findById(Long id) {
        return candidateDao.findById(id);
    }

    public List<Candidate> list() {
        return candidateDao.list();
    }

    public void setCandidateDao(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }
}

