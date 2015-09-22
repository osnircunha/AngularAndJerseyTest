package com.ocunha.election.rest;

import com.ocunha.election.object.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by osnircunha on 9/21/15.
 */
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRest {

    @POST
    public Response authenticate(User user){

        if(user.getPassword().equals("123")) {
            user.setPassword("***");
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
