package com.xebia.scrumboard.data;

import static com.xebia.scrumboard.representation.SprintAssert.assertThat;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.xebia.scrumboard.representation.Sprint;
import com.xebia.scrumboard.representation.SprintAssert;

public class SprintsTest {

    @Test
    public void shouldNotGetUnexisting() {
        Sprint sprint = Sprints.get("1");
        assertThat(sprint).isNull();
    }

    @Test
    public void shouldPost() {
        Sprint sprint = new Sprint("release", 2);
        sprint = Sprints.put(sprint);
        assertThat(sprint).hasId();

        sprint = Sprints.get(sprint.getId());
        SprintAssert.assertThat(sprint).hasWeeks(2).hasName("release");
    }

    @Test
    public void shouldPut() {
        Sprint sprint = new Sprint("release", 2);
        sprint = Sprints.put(sprint);
        assertThat(sprint).hasId();

        sprint.setWeeks(3);
        Sprints.put(sprint);

        sprint = Sprints.get(sprint.getId());
        SprintAssert.assertThat(sprint).hasWeeks(3).hasName("release");
    }

    @Test
    public void shouldDelete() {
        Sprint sprint = new Sprint("release", 2);
        sprint = Sprints.put(sprint);
        assertThat(sprint).isNotNull();

        Sprints.delete(sprint.getId());

        sprint = Sprints.get(sprint.getId());
        assertThat(sprint).isNull();
    }

    @Test
    public void shouldList() {
        deleteAll();

        Sprints.put(new Sprint("beta", 3));
        Sprints.put(new Sprint("release", 2));

        List<Sprint> sprints = Sprints.get();
        assertThat(sprints).hasSize(2);
    }

    private void deleteAll() {
        List<Sprint> sprints = Sprints.get();
        for (Sprint sprint : sprints)
            Sprints.delete(sprint.getId());
    }
}
