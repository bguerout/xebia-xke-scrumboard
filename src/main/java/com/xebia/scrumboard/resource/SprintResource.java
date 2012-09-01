package com.xebia.scrumboard.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.xebia.scrumboard.representation.Sprint;

@Path("sprint")
@Produces(APPLICATION_JSON)
public class SprintResource {

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response put(Sprint sprint) {
        return post(sprint);
    }

    @POST
    public Response post(Sprint sprint) {
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
    }

    @GET
    public Response get() {
        return Response.ok().build();
    }
}
