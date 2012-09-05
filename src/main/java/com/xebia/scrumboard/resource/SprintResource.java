package com.xebia.scrumboard.resource;

import com.sun.jersey.api.NotFoundException;
import com.xebia.scrumboard.data.Sprints;
import com.xebia.scrumboard.representation.Sprint;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("sprint")
@Produces(APPLICATION_JSON)
public class SprintResource {
    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        Sprint sprint = Sprints.get(id);

        if (sprint == null)
            throw new NotFoundException();

        return Response.ok(sprint).build();
    }

    @PUT
    @Path("{id}")
    public Response put(Sprint sprint) {
        return post(sprint);
    }

    @POST
    public Response post(Sprint sprint) {
        Sprints.put(sprint);
        return Response.ok(sprint).build();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        Sprints.delete(id);
    }

    @GET
    public Response get() {
        List<Sprint> sprints = Sprints.get();

        GenericEntity<List<Sprint>> entity = new GenericEntity<List<Sprint>>(sprints) {
        };
        return Response.ok(entity).build();
    }
}
