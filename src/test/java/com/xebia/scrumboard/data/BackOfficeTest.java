package com.xebia.scrumboard.data;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
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

    private Backlog backlog;

    @BeforeClass
    public static void connectToDatabase() throws Exception {
        Mongo mongo = new Mongo("127.0.0.1", 27017);
        DB db = mongo.getDB("xebia");
        jongo = new Jongo(db);
    }

    @Before
    public void setUp() throws Exception {

        MongoCollection collection = jongo.getCollection("sprints");

        backlog = new Backlog(collection);
        backlog.loadProductOwnerWishes();
    }

    @After
    public void tearDown() throws Exception {
        backlog.purge();
    }

    @Test
    public void canFindXLTasks() throws Exception {

        List<Sprint> sprints = BackOffice.findSprintsWithXLTasks();

        assertThat(sprints).isNotNull();
        assertThat(sprints).isNotEmpty();
        IterableAssert.assertThat(sprints).eachSatisfies(new SprintWithXLTaskCondition());
    }

    @Test
    public void canRemoveXLTasks() throws Exception {

        List<Sprint> sprints = BackOffice.findSprintsWithXLTasks();
        assertThat(sprints).isNotEmpty();
        String id = sprints.get(0).getId();

        BackOffice.removeXLTasks(id);

        List<Sprint> result = BackOffice.findSprintsWithXLTasks();
        IterableAssert.assertThat(result).eachSatisfies(new SprintWithXLTaskCondition());
    }

    @Test
    public void canGenerateATaskReportAsCSV() throws Exception {

        List<DBObject> report = BackOffice.generateTaskReport();

        assertThat(report).isNotEmpty();
        DBObject task = report.get(0);
        assertThat(task.get("name")).isNotNull();
        assertThat(task.get("sprint")).isNotNull();
        assertThat(task.get("size")).isNotNull();
    }

    private static class SprintWithXLTaskCondition extends Condition<Sprint> {
        @Override
        public boolean matches(Sprint value) {
            return value.hasTaskWithSize(Size.XL);
        }
    }
}


