package com.ocunha.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by osnircunha on 8/13/15.
 */
@Path("hello")
public class UserRest {

    @GET
    public Response getMsg() {

        String output = "Hello jow";
        return Response.status(200).entity(output).build();
    }
}
