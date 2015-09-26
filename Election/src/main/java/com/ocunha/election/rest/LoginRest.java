package com.ocunha.election.rest;

import com.ocunha.election.facade.UserFacade;
import com.ocunha.election.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by osnircunha on 9/21/15.
 */
@Controller
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginRest {

    @Autowired
    private UserFacade userFacade;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticate(@FormParam("username") String username, @FormParam("credential") String credential){
        User user = userFacade.findByName(username);
        if(user.getPassword().equals(credential)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
