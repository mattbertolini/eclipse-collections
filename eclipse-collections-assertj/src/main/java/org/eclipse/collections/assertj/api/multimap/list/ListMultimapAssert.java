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

import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.assertj.api.multimap.AbstractMultimapAssert;

public class ListMultimapAssert<KEY, VALUE>
        extends AbstractMultimapAssert<ListMultimapAssert<KEY, VALUE>, ListMultimap<KEY, VALUE>, KEY, VALUE>
{
    public static <KEY, VALUE> ListMultimapAssert<KEY, VALUE> assertThat(ListMultimap<KEY, VALUE> actual)
    {
        return new ListMultimapAssert<>(actual);
    }

    public ListMultimapAssert(ListMultimap<KEY, VALUE> actual)
    {
        super(actual, ListMultimapAssert.class);
    }
}
