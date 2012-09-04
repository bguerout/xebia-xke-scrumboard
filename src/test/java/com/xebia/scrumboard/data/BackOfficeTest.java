package com.xebia.scrumboard.data;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import com.xebia.scrumboard.Backlog;
import com.xebia.scrumboard.representation.IterableAssert;
import com.xebia.scrumboard.representation.Size;
import com.xebia.scrumboard.representation.Sprint;
import org.fest.assertions.Condition;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class BackOfficeTest {

    private static Jongo jongo;
    private BackOffice bo;
    private MongoCollection collection;

    @BeforeClass
    public static void connectToDatabase() throws Exception {
        Mongo mongo = new Mongo("127.0.0.1", 27017);
        DB db = mongo.getDB("xebia");
        jongo = new Jongo(db);
    }

    @Before
    public void setUp() throws Exception {

        collection = jongo.getCollection("sprints");
        bo = new BackOffice(collection);
        new Backlog(collection).loadProductOwnerWishes();
    }

    @After
    public void tearDown() throws Exception {
        collection.drop();
    }

    @Test
    public void canFindXLTasks() throws Exception {

        List<Sprint> sprints = bo.findSprintsByTaskSize(Size.XL);

        assertThat(sprints).isNotNull();
        assertThat(sprints).isNotEmpty();
        IterableAssert.assertThat(sprints).eachSatisfies(new Condition<Sprint>() {
            @Override
            public boolean matches(Sprint value) {
                return value.hasTaskWithSize(Size.XL);
            }
        });
    }

    @Test
    public void canRemoveXLTasks() throws Exception {

        bo.removeTasksBySize(Size.XL);

        assertThat(bo.findSprintsByTaskSize(Size.XL)).isEmpty();
    }

    @Test
    public void canGenerateATaskReport() throws Exception {

        List<String> report = bo.generateTaskReport(Size.XL);

        assertThat(report).isNotEmpty();
        DBObject task = (DBObject)JSON.parse(report.get(0));
        assertThat(task.get("task")).isNotNull();
        assertThat(task.get("sprint")).isEqualTo("Sprint 1 - Discover Jongo");
        assertThat(task.get("size")).isEqualTo(Size.XL.name());
    }
}


