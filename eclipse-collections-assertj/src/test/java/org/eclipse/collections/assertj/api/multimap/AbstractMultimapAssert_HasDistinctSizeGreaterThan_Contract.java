/*
 * Copyright (c) 2025 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.assertj.api.multimap;

import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public interface AbstractMultimapAssert_HasDistinctSizeGreaterThan_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>>
{
    I testInput();

    I emptyInput();

    A assertion(I testData);

    A softAssertion(SoftAssertions softAssertions, I testData);

    int expectedGreaterThanSize();

    default I nullInput()
    {
        return null;
    }

    @Test
    default void passesWhenDistinctSizeIsGreater()
    {
        this.assertion(this.testInput()).hasDistinctSizeGreaterThan(this.expectedGreaterThanSize());
    }

    @Test
    default void failsWhenInputIsEmpty()
    {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> this
                        .assertion(this.emptyInput())
                        .hasDistinctSizeGreaterThan(this.expectedGreaterThanSize()))
                .withMessageContaining("Expecting distinct size of")
                .withMessageContaining("to be greater than " + this.expectedGreaterThanSize());
    }

    @Test
    default void failsNullMultimap()
    {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> this
                        .assertion(this.nullInput())
                        .hasDistinctSizeGreaterThan(this.expectedGreaterThanSize()))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    default void softAssertionPasses()
    {
        SoftAssertions.assertSoftly(softly -> this.softAssertion(softly, this.testInput()).hasDistinctSizeGreaterThan(this.expectedGreaterThanSize()));
    }
}