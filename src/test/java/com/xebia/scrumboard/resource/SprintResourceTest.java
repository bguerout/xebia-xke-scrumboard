package com.xebia.scrumboard.resource;

import static com.sun.jersey.api.client.ClientResponse.Status.NOT_FOUND;
import static com.xebia.scrumboard.view.SprintViewAssert.assertThat;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.xebia.scrumboard.provider.JacksonMapperProvider;
import com.xebia.scrumboard.rule.Server;
import com.xebia.scrumboard.view.SprintView;
import com.xebia.scrumboard.view.TaskView;

public class SprintResourceTest {

    @ClassRule
    public static Server server = Server.create();
    public static Client client;

    private static String uri = server.uri + "/sprint";

    @Before
    public void createJacksonCustomClient() {
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonMapperProvider.class);
        client = Client.create(cc);
    }

    @Test
    public void shouldNotGetUnexisting() {
        ClientResponse response = get("1");
        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.getStatusCode());
    }

    @Test
    public void shouldPost() {
        SprintView sprint = new SprintView("release", 2, new TaskView("refactor", "bob", "M"));
        sprint = post(sprint);
        assertThat(sprint).hasId();

        sprint = get(sprint);
        assertThat(sprint).hasWeeks(2).hasName("release");
        assertThat(sprint.getTasks()).hasSize(1);
    }

    @Test
    public void shouldPut() {
        SprintView sprint = new SprintView("release", 2);
        sprint = post(sprint);
        assertThat(sprint).hasId();

        sprint.setPrice(3);
        put(sprint);

        sprint = get(sprint);
        assertThat(sprint).hasWeeks(3).hasName("release");
    }

    @Test
    public void shouldDelete() {
        SprintView sprint = new SprintView("release", 2);
        sprint = post(sprint);
        assertThat(sprint).isNotNull();

        delete(sprint);

        ClientResponse response = get(sprint.getId());
        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.getStatusCode());
    }

    @Test
    public void shouldList() {
        deleteAll();

        post(new SprintView("beta", 3));
        post(new SprintView("release", 2));

        List<SprintView> sprints = get();
        assertThat(sprints).hasSize(2);
    }

    /*
     * Helpers
     */

    public List<SprintView> get() {
        return client.resource(uri).accept(APPLICATION_JSON).get(new GenericType<List<SprintView>>() {
        });
    }

    public SprintView get(SprintView sprint) {
        String id = sprint.getId();
        return get(id).getEntity(SprintView.class);
    }

    public ClientResponse get(String id) {
        return client.resource(uri).path(id).accept(APPLICATION_JSON).get(ClientResponse.class);
    }

    public SprintView post(SprintView sprint) {
        return client.resource(uri).accept(APPLICATION_JSON).entity(sprint, APPLICATION_JSON).post(SprintView.class);
    }

    public ClientResponse put(SprintView sprint) {
        String id = sprint.getId();
        return client.resource(uri).path(id).accept(APPLICATION_JSON).entity(sprint, APPLICATION_JSON).put(ClientResponse.class);
    }

    public ClientResponse delete(SprintView sprint) {
        String id = sprint.getId();
        return client.resource(uri).path(id).delete(ClientResponse.class);
    }

    public void deleteAll() {
        List<SprintView> sprints = get();
        for (SprintView sprint : sprints)
            delete(sprint);
    }
}
