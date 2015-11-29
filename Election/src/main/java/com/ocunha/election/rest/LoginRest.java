package com.ocunha.election.rest;

import com.ocunha.election.annotation.Filter;
import com.ocunha.election.facade.UserFacade;
import com.ocunha.election.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by osnircunha on 9/21/15.
 */
@Controller
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginRest {

    @Autowired
    private UserFacade userFacade;

    @Context
    SecurityContext context;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Filter
    public Response authenticate(@FormParam("username") String username, @FormParam("credential") String credential){
        User user = userFacade.findByName(username);
        System.out.print(context.getUserPrincipal());
        if(user.getPassword().equals(credential)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
