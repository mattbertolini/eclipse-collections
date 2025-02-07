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

import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.assertj.api.multimap.AbstractMultimapAssert;

public class SetMultimapAssert<KEY, VALUE> extends AbstractMultimapAssert<SetMultimapAssert<KEY, VALUE>, SetMultimap<KEY, VALUE>, KEY, VALUE>
{
    public static <KEY, VALUE> SetMultimapAssert<KEY, VALUE> assertThat(SetMultimap<KEY, VALUE> actual)
    {
        return new SetMultimapAssert<>(actual);
    }

    public SetMultimapAssert(SetMultimap<KEY, VALUE> actual)
    {
        super(actual, SetMultimapAssert.class);
    }
}
