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

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.assertj.api.multimap.AbstractMultimapAssert_HasDistinctSize_Contract;
import org.eclipse.collections.impl.factory.Multimaps;

public class SetMultimapAssert_HasDistinctSize_Test
        implements AbstractMultimapAssert_HasDistinctSize_Contract<String, String, SetMultimap<String, String>, SetMultimapAssert<String, String>>
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
    public SetMultimapAssert<String, String> assertion(SetMultimap<String, String> testData)
    {
        return SetMultimapAssert.assertThat(testData);
    }

    @Override
    public SetMultimapAssert<String, String> softAssertion(
            SoftAssertions softAssertions,
            SetMultimap<String, String> testData)
    {
        return softAssertions.assertThat(testData);
    }

    @Override
    public int expectedSize()
    {
        return 5;
    }
}
