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

import org.assertj.core.api.Condition;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ListMultimapAssert_HasKeySatisfying_Test
{
    @Test
    public void passes()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        ListMultimapAssert
                .assertThat(multimap)
                .hasKeySatisfying(new Condition<>(s -> s.startsWith("Key"), "Key starts with"));
    }

    @Test
    public void failsNoSuchKey()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert
                        .assertThat(multimap)
                        .hasKeySatisfying(new Condition<>(
                                s -> s.startsWith("NonExistentKey"),
                                "Key starts with NonExistentKey")))
                .withMessageContaining("Expecting actual")
                .withMessageContaining("to contain a key satisfying")
                .withMessageContaining("Key starts with NonExistentKey");
    }

    @Test
    public void failsConditionNotMet()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert
                        .assertThat(multimap)
                        .hasKeySatisfying(new Condition<>(s -> s.startsWith("NonValue"), "Key starts with NonValue")))
                .withMessageContaining("Expecting actual")
                .withMessageContaining("to contain a key satisfying")
                .withMessageContaining("Key starts with NonValue");
    }

    @Test
    public void failsNullMultimap()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert
                        .assertThat(multimap)
                        .hasKeySatisfying(new Condition<>(s -> s.startsWith("Key"), "Key starts with")))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    public void softAssertionPasses()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        SoftAssertions.assertSoftly(softly -> softly
                .assertThat(multimap)
                .hasKeySatisfying(new Condition<>(s -> s.startsWith("Key"), "Key starts with Key")));
    }
}
