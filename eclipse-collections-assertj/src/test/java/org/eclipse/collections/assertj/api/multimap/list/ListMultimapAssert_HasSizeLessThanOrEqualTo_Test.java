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

public class ListMultimapAssert_HasSizeLessThanOrEqualTo_Test
{
    @Test
    public void passesLessThan()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key1", "Value1");
        ListMultimapAssert.assertThat(multimap).hasSizeLessThanOrEqualTo(2);
    }

    @Test
    public void passesEqual()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key1", "Value1");
        ListMultimapAssert.assertThat(multimap).hasSizeLessThanOrEqualTo(1);
    }

    @Test
    public void failsGreater()
    {
        ImmutableListMultimap<String, String> multimap =
                Multimaps.immutable.list.with("Key1", "Value1", "Key2", "Value2");
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).hasSizeLessThanOrEqualTo(1))
                .withMessageContaining("Expecting size of")
                .withMessageContaining("to be less than or equal to 1 but was 2");
    }

    @Test
    public void failsNullMultimap()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).hasSizeLessThanOrEqualTo(1))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    public void softAssertionPasses()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key1", "Value1");
        SoftAssertions.assertSoftly(softly -> softly.assertThat(multimap).hasSizeLessThanOrEqualTo(2));
    }
}
