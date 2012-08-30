package com.xebia.scrumboard.representation;

import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sprint {
    @JsonProperty("_id")
    ObjectId id;
    String name;
    int weeks;
    List<Task> tasks;

    Sprint() {
    }

    public Sprint(String name, int weeks) {
        this.name = name;
        this.weeks = weeks;
    }

    public String getId() {
        if (id != null)
            return id.toString();
        else
            return null;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }
}
