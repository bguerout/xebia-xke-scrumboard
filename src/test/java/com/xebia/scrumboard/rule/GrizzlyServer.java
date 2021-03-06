package com.xebia.scrumboard.rule;

import static com.sun.jersey.api.core.ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS;
import static com.sun.jersey.api.core.ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class GrizzlyServer extends Server {

    HttpServer server;

    @Override
    protected void before() throws Throwable {
        ResourceConfig rc = newConfig();
        server = GrizzlyServerFactory.createHttpServer(uri, rc);
        server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("src/main/webapp"), "/");
    }

    private ResourceConfig newConfig() {
        ResourceConfig rc = new PackagesResourceConfig("com.xebia.scrumboard.resource", "com.xebia.scrumboard.provider");
        rc.getProperties().put(PROPERTY_CONTAINER_REQUEST_FILTERS, new LoggingFilter());
        rc.getProperties().put(PROPERTY_CONTAINER_RESPONSE_FILTERS, new LoggingFilter());
        return rc;
    }

    @Override
    protected void after() {
        server.stop();
    }
}
