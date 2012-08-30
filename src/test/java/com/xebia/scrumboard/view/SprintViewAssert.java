package com.xebia.scrumboard.view;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class SprintViewAssert extends GenericAssert<SprintViewAssert, SprintView> {

    public SprintViewAssert(SprintView actual) {
        super(SprintViewAssert.class, actual);
    }

    public static SprintViewAssert assertThat(SprintView actual) {
        return new SprintViewAssert(actual);
    }

    public SprintViewAssert hasId() {
        Assertions.assertThat(actual.id).isNotNull();
        return this;
    }

    public SprintViewAssert hasName(String name) {
        Assertions.assertThat(actual.name).isEqualTo(name);
        return this;
    }

    public SprintViewAssert hasWeeks(int weeks) {
        Assertions.assertThat(actual.weeks).isEqualTo(weeks);
        return this;
    }
}
