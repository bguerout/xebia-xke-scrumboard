package com.xebia.scrumboard;

import com.mongodb.WriteConcern;
import com.xebia.scrumboard.representation.Size;
import com.xebia.scrumboard.representation.Sprint;
import com.xebia.scrumboard.representation.Task;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

import static com.google.common.collect.Lists.newArrayList;

public class Backlog {

    private final MongoCollection collection;

    public Backlog(MongoCollection collection) {
        this.collection = collection;
    }

    public void loadProductOwnerWishes() throws UnknownHostException {

        //----------------------------------------------SPRINT 0-------------------------------------------------------------------

        Sprint sprint0 = new Sprint("Sprint 0 - Warm up", 1);
        sprint0.setTasks(newArrayList(
                new Task(Size.XS, "Prepare your environment and discover MongoDB Shell...")));

        //----------------------------------------------SPRINT 1-------------------------------------------------------------------

        Sprint sprint1 = new Sprint("Sprint 1 - Discover Jongo", 2);
        sprint1.setTasks(newArrayList(
                new Task(Size.XS, "1. Import Project into IntelliJ or a crappy IDE (eg. Eclipse)"),
                new Task(Size.XS, "2. Start your local MongoDB"),
                new Task(Size.XS, "3. Launch tests"),
                new Task(Size.M,  "4. Implement CRUD with Jongo -> com.xebia.scrumboard.data.Sprints"),
                new Task(Size.L,  "5. Implement backoffice"),
                new Task(Size.XL, "6. Generate a task report (Aggregation powa!)"),
                new Task(Size.XS, "7. Be ecstatic")
        ));

        //----------------------------------------------SPRINT 2-------------------------------------------------------------------

        Sprint sprint2 = new Sprint("Sprint 2 - Jersey", 2);
        sprint2.setTasks(newArrayList(
                new Task(Size.XS, "....")));

        collection.save(sprint0, WriteConcern.SAFE);
        collection.save(sprint1, WriteConcern.SAFE);
        collection.save(sprint2, WriteConcern.SAFE);
    }

    public void purge() throws UnknownHostException {
        collection.drop();
    }
}
