package com.xebia.scrumboard.representation;

import org.fest.assertions.Condition;
import org.fest.assertions.IteratorAssert;

public class IterableAssert<T> extends IteratorAssert {

    public static <T> IterableAssert<T> assertThat(Iterable<T> actual) {
        return new IterableAssert<T>(actual);
    }

    private final Iterable<T> actualIterable;

    public IterableAssert(Iterable<T> actual) {
        super(actual.iterator());
        actualIterable = actual;
    }

    public IterableAssert<T> eachSatisfies(Condition<T> condition) {

        long notMatching = 0;
        StringBuilder notMatchingDescription = new StringBuilder();
        long index = 0;
        for (T value : actualIterable) {
            if (!condition.matches(value)) {
                notMatching++;
                notMatchingDescription //
                        .append("Element ") //
                        .append(index) //
                        .append(", [") //
                        .append(value.toString()) //
                        .append("] does not match condition: ") //
                        .append(condition.description()) //
                        .append("\n") //
                ;
            }
            index++;
        }

        if (notMatching == 0) {
            return this;
        }

        failIfCustomMessageIsSet();
        throw failure(notMatching + " element(s) in the iterable not matching the provided condition:\n" + notMatchingDescription.toString());
    }

}
