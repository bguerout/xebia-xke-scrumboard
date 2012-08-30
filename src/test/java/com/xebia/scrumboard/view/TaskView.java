package com.xebia.scrumboard.view;

public class TaskView {
    String name, developer, size;

    TaskView() {
    }

    public TaskView(String name, String developer, String size) {
        this.name = name;
        this.developer = developer;
        this.size = size;
    }
}
