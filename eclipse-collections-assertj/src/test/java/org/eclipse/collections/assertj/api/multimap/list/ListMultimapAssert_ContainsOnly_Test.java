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

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.eclipse.collections.impl.tuple.Tuples.pair;

public class ListMultimapAssert_ContainsOnly_Test
{
    @Test
    void passesSingleKeyAndValueWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        ListMultimapAssert.assertThat(multimap).containsOnly(entry("Key", "Value"));
    }

    @Test
    void passesMultipleKeysAndValuesWithEntry()
    {
        ImmutableListMultimap<String, String> multimap =
                Multimaps.immutable.list.with("Key1", "Value1", "Key2", "Value2");
        ListMultimapAssert.assertThat(multimap).containsOnly(entry("Key1", "Value1"), entry("Key2", "Value2"));
    }

    @Test
    void failsWhenAdditionalKeysOrValuesExistWithEntry()
    {
        ImmutableListMultimap<String, String> multimap =
                Multimaps.immutable.list.with("Key1", "Value1", "Key2", "Value2");

        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).containsOnly(entry("Key1", "Value1")))
                .withMessageContaining("to contain only").withMessageContaining("but the following multimap entries were unexpected");
    }

    @Test
    void failsForEmptyMultimapWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.empty();
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).containsOnly(entry("Key", "Value")))
                .withMessageContaining("to contain only");
    }

    @Test
    void failsForNullMultimapWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).containsOnly(entry("Key", "Value")))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    void softAssertionPassesContainsOnlyWithEntry()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        SoftAssertions.assertSoftly(softly -> softly.assertThat(multimap).containsOnly(entry("Key", "Value")));
    }

    @Test
    void passesSingleKeyAndValueWithPair()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        ListMultimapAssert.assertThat(multimap).containsOnly(pair("Key", "Value"));
    }

    @Test
    void passesMultipleKeysAndValuesWithPair()
    {
        ImmutableListMultimap<String, String> multimap =
                Multimaps.immutable.list.with("Key1", "Value1", "Key2", "Value2");
        ListMultimapAssert.assertThat(multimap).containsOnly(pair("Key1", "Value1"), pair("Key2", "Value2"));
    }

    @Test
    void failsWhenAdditionalKeysOrValuesExistWithPair()
    {
        ImmutableListMultimap<String, String> multimap = 
                Multimaps.immutable.list.with("Key1", "Value1", "Key2", "Value2");

        assertThatExceptionOfType(AssertionError.class)
            .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).containsOnly(pair("Key1", "Value1")))
            .withMessageContaining("to contain only").withMessageContaining("but the following multimap entries were unexpected");
    }

    @Test
    void failsForEmptyMultimapWithPair()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.empty();
        assertThatExceptionOfType(AssertionError.class)
            .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).containsOnly(pair("Key", "Value")))
            .withMessageContaining("to contain only");
    }

    @Test
    void failsForNullMultimapWithPair()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
            .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).containsOnly(pair("Key", "Value")))
            .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    void softAssertionPassesContainsOnlyWithPair()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        SoftAssertions.assertSoftly(softly -> softly.assertThat(multimap).containsOnly(pair("Key", "Value")));
    }
}
