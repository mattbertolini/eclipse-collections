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

import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.assertj.api.multimap.list.ListMultimapAssert;

public final class Assertions
{
    private Assertions()
    {
        throw new UnsupportedOperationException("Utility class");
    }

    public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual)
    {
        return ListMultimapAssert.assertThat(actual);
    }
}
