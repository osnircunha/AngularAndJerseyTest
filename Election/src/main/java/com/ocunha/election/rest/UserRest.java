package com.ocunha.election.rest;

import com.ocunha.election.CandidateFacade;
import com.ocunha.election.object.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by osnircunha on 8/13/15.
 */
@Component
@Path("/candidates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {

    @Autowired
    private CandidateFacade candidateFacade;

    @GET
    @Path("/test")
    public String test(){
        return "Hello";
    }

    @GET
    public Response getCandidate(@QueryParam("id") String id) {
        if(id != null){
            Candidate candidate = candidateFacade.findById(Long.valueOf(id));
            return Response.ok(candidate).build();
        } else {
            List<Candidate> candidates = candidateFacade.list();
            return Response.ok(candidates).build();
        }
    }

    @POST
    public Response addCandidate(Candidate candidate) {
        candidateFacade.save(candidate);
        return Response.status(Response.Status.OK).build();

    }


}
