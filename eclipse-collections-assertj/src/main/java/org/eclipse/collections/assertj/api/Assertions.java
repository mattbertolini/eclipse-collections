/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.assertj.api;

import org.assertj.core.util.CheckReturnValue;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.assertj.api.multimap.bag.BagMultimapAssert;
import org.eclipse.collections.assertj.api.multimap.list.ListMultimapAssert;
import org.eclipse.collections.assertj.api.multimap.set.SetMultimapAssert;

@CheckReturnValue
public final class Assertions
{
    private Assertions()
    {
        throw new UnsupportedOperationException("Utility class");
    }

    public static <KEY, VALUE> BagMultimapAssert<KEY, VALUE> assertThat(BagMultimap<KEY, VALUE> actual)
    {
        return BagMultimapAssert.assertThat(actual);
    }

    public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual)
    {
        return ListMultimapAssert.assertThat(actual);
    }

    public static <KEY, VALUE> SetMultimapAssert<KEY, VALUE> assertThat(SetMultimap<KEY, VALUE> actual)
    {
        return SetMultimapAssert.assertThat(actual);
    }
}
