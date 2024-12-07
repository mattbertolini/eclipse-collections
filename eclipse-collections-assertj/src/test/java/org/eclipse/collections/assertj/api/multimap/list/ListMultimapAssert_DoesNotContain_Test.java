/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.assertj.api.multimap.list;

import java.util.Map;

import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ListMultimapAssert_DoesNotContain_Test
{
    @Test
    void passesMultivalueWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key1", "Value1");
        ListMultimapAssert.assertThat(multimap).doesNotContain(Map.entry("Key1", "Value2"));
    }

    @Test
    void passesDifferentKeyWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key1", "Value1");
        ListMultimapAssert.assertThat(multimap).doesNotContain(Map.entry("Key2", "Value2"));
    }

    @Test
    void failsSameEntry()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).doesNotContain(Map.entry("Key", "Value")))
                .withMessageContaining("Expecting")
                .withMessageContaining("not to contain")
                .withMessageContaining("but found");
    }

    @Test
    void failsNullMultimapWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).doesNotContain(Map.entry("Key", "Value")))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    void softAssertionPassesWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        SoftAssertions.assertSoftly(softly -> softly
                .assertThat(multimap)
                .doesNotContain(Map.entry("NonExistingKey", "NonExistingValue")));
    }

    @Test
    void passesMultivalueWithPair()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key1", "Value1");
        ListMultimapAssert.assertThat(multimap).doesNotContain(Tuples.pair("Key1", "Value2"));
    }

    @Test
    void passesDifferentKeyWithPair()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key1", "Value1");
        ListMultimapAssert.assertThat(multimap).doesNotContain(Tuples.pair("Key2", "Value2"));
    }

    @Test
    void failsSamePair()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).doesNotContain(Tuples.pair("Key", "Value")))
                .withMessageContaining("Expecting")
                .withMessageContaining("not to contain")
                .withMessageContaining("but found");
    }

    @Test
    void failsNullMultimapWithPair()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).doesNotContain(Tuples.pair("Key", "Value")))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    void softAssertionPassesWithPair()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        SoftAssertions.assertSoftly(softly -> softly
                .assertThat(multimap)
                .doesNotContain(Tuples.pair("NonExistingKey", "NonExistingValue")));
    }
}
