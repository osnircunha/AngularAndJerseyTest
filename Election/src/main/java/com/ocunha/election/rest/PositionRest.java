package com.ocunha.election.rest;

import com.ocunha.election.facade.PositionFacade;
import com.ocunha.election.facade.impl.PositionFacadeImpl;
import com.ocunha.election.object.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by osnircunha on 9/24/15.
 */
@Controller
@Path("/positions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PositionRest {

    @Autowired
    private PositionFacade positionFacadeImpl;

    @GET
    public Response getPosition(@QueryParam("id") String id){
        if(id != null && !"".equals(id)){
            Position position =   positionFacadeImpl.findById(Long.valueOf(id));
            return Response.status(Response.Status.OK).entity(position).build();
        } else {
            List<Position> positions = positionFacadeImpl.list();
            return Response.status(Response.Status.OK).entity(positions).build();
        }
    }

    @POST
    public Response addPosition(Position position){
        positionFacadeImpl.save(position);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    public Response updatePosition(Position position){
        positionFacadeImpl.update(position);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePosition(@PathParam("id") Long id){
        positionFacadeImpl.delete(id);
        return Response.status(Response.Status.OK).build();
    }

}
