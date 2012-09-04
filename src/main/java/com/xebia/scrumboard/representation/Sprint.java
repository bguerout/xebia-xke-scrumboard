package com.xebia.scrumboard.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class Sprint {
    @JsonProperty("_id")
    ObjectId id;
    String name;
    int weeks;
    List<Task> tasks;

    /**
     * Jackson constructor
     */
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

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean hasTaskWithSize(Size size) {
        for (Task task : tasks) {
            if (task.getSize().equals(size)) {
                return true;
            }
        }
        return false;
    }
}
