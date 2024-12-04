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
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;

import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.assertj.core.error.ShouldContainKeys.shouldContainKeys;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldHaveSizeBetween.shouldHaveSizeBetween;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;

public abstract class AbstractMultimapAssert<SELF extends AbstractMultimapAssert<SELF, ACTUAL, KEY, VALUE>, ACTUAL extends Multimap<KEY, VALUE>, KEY, VALUE>
        extends AbstractObjectAssert<SELF, ACTUAL>
{
    protected AbstractMultimapAssert(ACTUAL actual, Class<?> selfType)
    {
        super(actual, selfType);
    }

    public SELF containsKeys(KEY... keys)
    {
        this.isNotNull();
        MutableList<KEY> keysNotFound = ArrayAdapter.adapt(keys).reject(this.actual::containsKey);
        if (keysNotFound.isEmpty())
        {
            return this.myself;
        }
        throw this.assertionError(shouldContainKeys(this.actual, keysNotFound.toSet()));
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

    public SELF hasSizeBetween(int lowerBoundary, int higherBoundary)
    {
        this.isNotNull();
        int actualSize = this.actual.size();
        if (actualSize >= lowerBoundary && actualSize <= higherBoundary)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveSizeBetween(this.actual, actualSize, lowerBoundary, higherBoundary));
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

    /**
     * Verifies that the {@link Multimap} is not empty.
     * <p>
     * Example:
     * <pre>{@code
     * // assertion will pass
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key", "Value");
     * assertThat(multimap).isNotEmpty();
     *
     * // assertion will fail
     * assertThat(Multimaps.mutable.list.empty()).isNotEmpty();
     * }</pre>
     *
     * @throws AssertionError if the {@link Multimap} of values is empty.
     */
    public SELF isNotEmpty()
    {
        this.isNotNull();
        if (!this.actual.isEmpty())
        {
            return this.myself;
        }
        throw this.assertionError(shouldNotBeEmpty());
    }

    /**
     * Verifies that the {@link Multimap} is null or empty.
     * <p>
     * Example:
     * <pre>{@code
     * // assertions that will pass
     * Multimap<String, String> multimap = null;
     * assertThat(multimap).isNullOrEmpty();
     *
     * Multimap<String, String> emptyMultimap = Multimaps.mutable.list.empty();
     * assertThat(emptyMultimap).isNullOrEmpty();
     *
     * // assertion will fail
     * Multimap<String, String> multimapWithElements = Multimaps.mutable.list.with("Key", "Value");
     * assertThat(multimapWithElements).isNullOrEmpty();
     * }</pre>
     *
     * @throws AssertionError if the {@link Multimap} is neither null nor empty.
     */
    public void isNullOrEmpty()
    {
        if (this.actual == null || this.actual.isEmpty())
        {
            return;
        }
        throw this.assertionError(shouldBeNullOrEmpty(this.actual));
    }
}
