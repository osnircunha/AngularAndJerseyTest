package com.ocunha.election.rest;

import com.ocunha.election.annotation.Filter;
import com.ocunha.election.facade.CandidateFacade;
import com.ocunha.election.object.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by osnircunha on 8/13/15.
 */
@Controller
@Path("/candidates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CandidateRest {

    @Autowired
    private CandidateFacade candidateFacadeImpl;

    @GET
    @Path("/{id}")
    @Filter
    public Response getCandidate(@PathParam("id") String id) {
        Candidate candidate = candidateFacadeImpl.findById(Long.valueOf(id));
        return Response.ok(candidate).build();
    }

    @GET
    public Response getCandidates() {
        List<Candidate> candidates = candidateFacadeImpl.list();
        return Response.ok(candidates).build();
    }

    @POST
    public Response addCandidate(Candidate candidate) {
        candidateFacadeImpl.save(candidate);
        return Response.status(Response.Status.OK).build();

    }

    @PUT
    public Response updateCandidate(Candidate candidate) {
        candidateFacadeImpl.update(candidate);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCandidate(@PathParam("id") String id){
        candidateFacadeImpl.delete(Long.valueOf(id));
        return Response.status(Response.Status.OK).build();
    }

}
