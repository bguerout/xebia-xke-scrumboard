package com.xebia.scrumboard.view;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SprintView {
    @JsonProperty("_id")
    String id;
    String name;
    int weeks;
    List<TaskView> tasks;

    SprintView() {
    }

    public SprintView(String name, int weeks, TaskView... tasks) {
        this.name = name;
        this.weeks = weeks;
        this.tasks = new ArrayList<TaskView>();
        for (TaskView task : tasks)
            this.tasks.add(task);
    }

    public String getId() {
        return id;
    }

    public void setPrice(int weeks) {
        this.weeks = weeks;
    }

    public List<TaskView> getTasks() {
        return tasks;
    }
}
