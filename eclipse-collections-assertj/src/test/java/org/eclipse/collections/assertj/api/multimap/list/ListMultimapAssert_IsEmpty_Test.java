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

class ListMultimapAssert_IsEmpty_Test
{
    @Test
    public void passes()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.empty();
        ListMultimapAssert.assertThat(multimap).isEmpty();
    }

    @Test
    void failsNotEmpty()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.with("Key", "Value");
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).isEmpty())
                .withMessageContaining("Expecting empty but was: {Key=[Value]}");
    }

    @Test
    void failsNullMultimap()
    {
        ImmutableListMultimap<String, String> multimap = null;
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> ListMultimapAssert.assertThat(multimap).isEmpty())
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    void softAssertionPasses()
    {
        ImmutableListMultimap<String, String> multimap = Multimaps.immutable.list.empty();
        SoftAssertions.assertSoftly(softly -> softly.assertThat(multimap).isEmpty());
    }
}
