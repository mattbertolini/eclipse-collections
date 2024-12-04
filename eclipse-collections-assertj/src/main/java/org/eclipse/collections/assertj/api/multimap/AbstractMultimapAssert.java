/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.assertj.api.multimap;

import org.assertj.core.api.AbstractObjectAssert;
import org.eclipse.collections.api.multimap.Multimap;

import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;

public abstract class AbstractMultimapAssert<SELF extends AbstractMultimapAssert<SELF, ACTUAL, KEY, VALUE>,
        ACTUAL extends Multimap<KEY, VALUE>,
        KEY,
        VALUE>
        extends AbstractObjectAssert<SELF, ACTUAL>
{
    protected AbstractMultimapAssert(ACTUAL actual, Class<?> selfType)
    {
        super(actual, selfType);
    }

    public SELF hasSize(int expected)
    {
        this.isNotNull();
        int actualSize = this.actual.size();
        if (actualSize == expected)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveSize(this.actual, actualSize, expected));
    }

    /**
     * Verifies that the {@link Multimap} is empty.
     * <p>
     * Example:
     * <pre>{@code
     * // assertion will pass
     * assertThat(Multimaps.mutable.list.empty()).isEmpty();
     *
     * // assertion will fail
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key", "Value");
     * assertThat(multimap).isEmpty();
     * }</pre>
     *
     * @throws AssertionError if the {@link Multimap} of values is not empty.
     */
    public void isEmpty()
    {
        this.isNotNull();
        if (!this.actual.isEmpty())
        {
            throw this.assertionError(shouldBeEmpty(this.actual));
        }
    }

    public SELF isNotEmpty()
    {
        this.isNotNull();
        if (!this.actual.isEmpty())
        {
            return this.myself;
        }
        throw this.assertionError(shouldNotBeEmpty());
    }
}
