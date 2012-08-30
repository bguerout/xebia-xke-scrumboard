package com.xebia.scrumboard.representation;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class SprintAssert extends GenericAssert<SprintAssert, Sprint> {

    public SprintAssert(Sprint actual) {
        super(SprintAssert.class, actual);
    }

    public static SprintAssert assertThat(Sprint actual) {
        return new SprintAssert(actual);
    }

    public SprintAssert hasId() {
        Assertions.assertThat(actual.id).isNotNull();
        return this;
    }

    public SprintAssert hasName(String name) {
        Assertions.assertThat(actual.name).isEqualTo(name);
        return this;
    }

    public SprintAssert hasWeeks(int weeks) {
        Assertions.assertThat(actual.weeks).isEqualTo(weeks);
        return this;
    }
}
