package com.xebia.scrumboard.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    private String name;
    private Size size;

    @JsonCreator
    public Task(@JsonProperty("size") Size size, @JsonProperty("name") String name) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }
}
