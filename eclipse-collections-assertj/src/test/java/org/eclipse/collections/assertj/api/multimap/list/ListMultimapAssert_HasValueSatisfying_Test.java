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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ListMultimapAssert_HasValueSatisfying_Test
{
    @Test
    void passesValueSatisfying()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "ExpectedValue");
        ListMultimapAssert.assertThat(multimap).hasValueSatisfying(new Condition<>(value -> value.startsWith("ExpectedValue"), "starts with ExpectedValue"));
    }

    @Test
    void failsValueNotSatisfying()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "UnexpectedValue");
        assertThatExceptionOfType(AssertionError.class)
            .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).hasValueSatisfying(new Condition<>(value -> value.startsWith("ExpectedValue"), "starts with ExpectedValue")))
            .withMessageContaining("ExpectedValue");
    }

    @Test
    void failsNullMultimap()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
            .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).hasValueSatisfying(new Condition<>(value -> value.startsWith("ExpectedValue"), "starts with ExpectedValue")))
            .withMessageContaining("Expecting actual not to be null");
    }
}
