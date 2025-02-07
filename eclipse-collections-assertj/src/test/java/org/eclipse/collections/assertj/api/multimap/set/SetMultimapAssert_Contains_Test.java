/*
 * Copyright (c) 2025 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.assertj.api.multimap.set;

import java.util.Map;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.assertj.api.multimap.AbstractMultimapAssert_Contains_Contract;
import org.eclipse.collections.impl.factory.Multimaps;

import static java.util.Map.entry;
import static org.eclipse.collections.impl.tuple.Tuples.pair;

public class SetMultimapAssert_Contains_Test implements AbstractMultimapAssert_Contains_Contract<String, String, SetMultimap<String, String>, SetMultimapAssert<String, String>>
{
    @Override
    public SetMultimap<String, String> testInput()
    {
        MutableSetMultimap<String, String> multimap = Multimaps.mutable.set.of();
        multimap.putAll("TOS", Sets.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
        multimap.putAll("TNG", Sets.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
        multimap.putAll("DS9", Sets.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
        multimap.putAll("VOY", Sets.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
        multimap.putAll("ENT", Sets.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
        return multimap;
    }

    @Override
    public SetMultimap<String, String> emptyInput()
    {
        return Multimaps.immutable.set.empty();
    }

    @Override
    public SetMultimapAssert<String, String> assertion(SetMultimap<String, String> testInput)
    {
        return SetMultimapAssert.assertThat(testInput);
    }

    @Override
    public SetMultimapAssert<String, String> softAssertion(
            SoftAssertions softAssertions,
            SetMultimap<String, String> testInput)
    {
        return softAssertions.assertThat(testInput);
    }

    @Override
    public Pair<String, String>[] expectedPairs()
    {
        return new Pair[] {pair("TNG", "Picard"), pair("DS9", "Sisko")};
    }

    @Override
    public Map.Entry<String, String>[] expectedEntries()
    {
        return new Map.Entry[] {entry("TNG", "Picard"), entry("DS9", "Sisko")};
    }

    @Override
    public Pair<String, String> missingPair()
    {
        return pair("VOY", "Kes");
    }

    @Override
    public Map.Entry<String, String> missingEntry()
    {
        return entry("VOY", "Kes");
    }
}
