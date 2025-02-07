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

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.assertj.api.multimap.AbstractMultimapAssert_HasDistinctSize_Contract;
import org.eclipse.collections.impl.factory.Multimaps;

class ListMultimapAssert_HasDistinctSize_Test implements AbstractMultimapAssert_HasDistinctSize_Contract<String, String, ListMultimap<String, String>, ListMultimapAssert<String, String>>
{
    @Override
    public ListMultimap<String, String> testInput()
    {
        MutableListMultimap<String, String> multimap = Multimaps.mutable.list.of();
        multimap.putAll("TOS", Lists.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
        multimap.putAll("TNG", Lists.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
        multimap.putAll("DS9", Lists.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
        multimap.putAll("VOY", Lists.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
        multimap.putAll("ENT", Lists.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
        return multimap;
    }

    @Override
    public ListMultimap<String, String> emptyInput()
    {
        return Multimaps.immutable.list.empty();
    }

    @Override
    public ListMultimapAssert<String, String> assertion(ListMultimap<String, String> testData)
    {
        return ListMultimapAssert.assertThat(testData);
    }

    @Override
    public ListMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, ListMultimap<String, String> testData)
    {
        return softAssertions.assertThat(testData);
    }

    @Override
    public int expectedSize()
    {
        return 5;
    }
}
