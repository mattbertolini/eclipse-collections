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

import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Test;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.eclipse.collections.impl.tuple.Tuples.pair;

public class ListMultimapAssert_Contains_Test
{
    @Test
    public void containsPairsPasses()
    {
        ListMultimap<String, String> multimap = Multimaps.mutable.list.with(
                "key1", "value1",
                "key2", "value2"
        );

        ListMultimapAssert.assertThat(multimap).contains(pair("key1", "value1"), pair("key2", "value2"));
    }

    @Test
    void containsPairsFails()
    {
        ListMultimap<String, String> multimap = Multimaps.mutable.list.with(
                "key1", "value1"
        );

        assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
                        ListMultimapAssert.assertThat(multimap).contains(pair("key1", "value3")))
                .withMessageContaining("Expecting")
                .withMessageContaining("but could not find the following element(s)")
                .withMessageContaining("[key1:value3]");
    }

    @Test
    void nullActualWithPair()
    {
        ListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
                        ListMultimapAssert.assertThat(multimap).contains(pair("key1", "value2")))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    void softAssertionsWithPairPasses()
    {
        ListMultimap<String, String> multimap = Multimaps.mutable.list.with(
                "key1", "value1",
                "key2", "value2"
        );

        SoftAssertions.assertSoftly(softly -> softly
                .assertThat(multimap)
                .contains(pair("key1", "value1"), pair("key2", "value2")));
    }

    @Test
    public void containsEntriesPasses()
    {
        ListMultimap<String, String> multimap = Multimaps.mutable.list.with(
                "key1", "value1",
                "key2", "value2"
        );

        ListMultimapAssert.assertThat(multimap).contains(entry("key1", "value1"), entry("key2", "value2"));
    }

    @Test
    void containsEntriesFails()
    {
        ListMultimap<String, String> multimap = Multimaps.mutable.list.with(
                "key1", "value1"
        );

        assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
                        ListMultimapAssert.assertThat(multimap).contains(entry("key1", "value3")))
                .withMessageContaining("Expecting")
                .withMessageContaining("but could not find the following element(s)")
                .withMessageContaining("[key1:value3]");
    }

    @Test
    void nullActualWithEntry()
    {
        ListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
                        ListMultimapAssert.assertThat(multimap).contains(entry("key1", "value2")))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    void softAssertionsWithEntryPasses()
    {
        ListMultimap<String, String> multimap = Multimaps.mutable.list.with(
                "key1", "value1",
                "key2", "value2"
        );

        SoftAssertions.assertSoftly(softly -> softly
                .assertThat(multimap)
                .contains(entry("key1", "value1"), entry("key2", "value2")));
    }
}
