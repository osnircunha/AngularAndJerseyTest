package com.ocunha.rest;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by osnircunha on 8/13/15.
 */
@Path("candidates")
public class UserRest {

    Map<String, Integer> candidates = new HashMap<String, Integer>();

    @GET
    public Response getCandidates(@QueryParam("name") String name) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return Response.status(Response.Status.OK).entity(mapper.writeValueAsString(candidates)).build();
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    public Response addCandidate(@QueryParam("name") String name, @QueryParam("votes") Integer votes) {
        if(name != null && !"".equals(name.trim())) {
            candidates.put(name, votes);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


}
