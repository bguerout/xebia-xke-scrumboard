package com.xebia.scrumboard.rule;

public class EmbeddedServer {
    public static void main(String[] args) throws Throwable {
        new GrizzlyServer().before();
        while (true);
    }
}
