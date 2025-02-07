/*
 * Copyright (c) 2025 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.assertj.api.multimap.bag;

import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.assertj.api.SoftAssertions;
import org.eclipse.collections.assertj.api.multimap.AbstractMultimapAssert_HasSize_Contract;
import org.eclipse.collections.impl.factory.Multimaps;

public class BagMultimapAssert_HasSize_Test implements AbstractMultimapAssert_HasSize_Contract<BagMultimap<String, String>, BagMultimapAssert<String, String>>
{
    @Override
    public BagMultimap<String, String> testInput()
    {
        MutableBagMultimap<String, String> multimap = Multimaps.mutable.bag.of();
        multimap.putAll("TOS", Bags.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
        multimap.putAll("TNG", Bags.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
        multimap.putAll("DS9", Bags.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
        multimap.putAll("VOY", Bags.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
        multimap.putAll("ENT", Bags.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
        return multimap;
    }

    @Override
    public BagMultimap<String, String> emptyInput()
    {
        return Multimaps.immutable.bag.empty();
    }

    @Override
    public BagMultimapAssert<String, String> assertion(BagMultimap<String, String> testData)
    {
        return BagMultimapAssert.assertThat(testData);
    }

    @Override
    public BagMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, BagMultimap<String, String> testData)
    {
        return softAssertions.assertThat(testData);
    }

    @Override
    public int expectedSize()
    {
        return 38;
    }
}
