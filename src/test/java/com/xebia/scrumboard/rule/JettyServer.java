package com.xebia.scrumboard.rule;

import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer extends Server {
    static org.eclipse.jetty.server.Server server;

    @Override
    protected void before() throws Throwable {
        server = new org.eclipse.jetty.server.Server(port);
        server.setHandler(new WebAppContext("src/main/webapp", "/"));
        server.start();

    }

    @Override
    protected void after() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
