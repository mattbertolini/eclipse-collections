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

import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ListMultimapAssert_HasSizeGreaterThanOrEqualTo_Test
{
    @Test
    public void passesEqual()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        ListMultimapAssert.assertThat(multimap).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    public void passesGreaterThan()
    {
        ImmutableListMultimap<String, String> multimap =
                Multimaps.immutable.list.with("Key1", "Value1", "Key2", "Value2");
        ListMultimapAssert.assertThat(multimap).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    public void failsEmpty()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.empty();
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).hasSizeGreaterThanOrEqualTo(1))
                .withMessageContaining("Expecting size of")
                .withMessageContaining("to be greater than or equal to 1 but was 0");
    }

    @Test
    public void failsNullMultimap()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).hasSizeGreaterThanOrEqualTo(1))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    public void softAssertionPasses()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        SoftAssertions.assertSoftly(softly -> softly.assertThat(multimap).hasSizeGreaterThanOrEqualTo(1));
    }
}
