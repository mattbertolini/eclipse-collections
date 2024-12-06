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

import java.util.Map;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Condition;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.list.fixed.ArrayAdapter;
import org.eclipse.collections.impl.tuple.Tuples;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.assertj.core.error.ShouldContain.shouldContain;
import static org.assertj.core.error.ShouldContainKey.shouldContainKey;
import static org.assertj.core.error.ShouldContainKeys.shouldContainKeys;
import static org.assertj.core.error.ShouldContainValues.shouldContainValues;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldHaveSizeBetween.shouldHaveSizeBetween;
import static org.assertj.core.error.ShouldHaveSizeGreaterThan.shouldHaveSizeGreaterThan;
import static org.assertj.core.error.ShouldHaveSizeGreaterThanOrEqualTo.shouldHaveSizeGreaterThanOrEqualTo;
import static org.assertj.core.error.ShouldHaveSizeLessThan.shouldHaveSizeLessThan;
import static org.assertj.core.error.ShouldHaveSizeLessThanOrEqualTo.shouldHaveSizeLessThanOrEqualTo;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.error.ShouldNotContainKeys.shouldNotContainKeys;

/**
 * Base class for all implementations of assertions for {@link Multimap}s.
 *
 * @param <SELF>   the "self" type of this assertion class.
 * @param <ACTUAL> the type of the "actual" value.
 * @param <KEY>    the type of keys in the Multimap.
 * @param <VALUE>  the type of values in the Multimap.
 */
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

    @SafeVarargs
    public final SELF contains(Pair<KEY, VALUE>... entries)
    {
        return this.containsForProxy(entries);
    }

    @SafeVarargs
    public final SELF contains(Map.Entry<KEY, VALUE>... entries)
    {
        @SuppressWarnings("unchecked")
        Pair<KEY, VALUE>[] pairs = ArrayAdapter.adapt(entries).collect(Tuples::pairFrom).toArray(Pair[]::new);
        return this.containsForProxy(pairs);
    }

    @SuppressWarnings("MethodCanBeVariableArityMethod")
    protected SELF containsForProxy(Pair<KEY, VALUE>[] entries)
    {
        this.isNotNull();
        MutableList<Pair<KEY, VALUE>> entriesNotFound = ArrayAdapter
                .adapt(entries)
                .reject(entry -> this.actual.containsKeyAndValue(entry.getOne(), entry.getTwo()));
        if (entriesNotFound.isEmpty())
        {
            return this.myself;
        }
        throw this.assertionError(shouldContain(this.actual, entries, entriesNotFound));
    }

    public SELF containsValues(VALUE... values)
    {
        this.isNotNull();
        MutableList<VALUE> valuesNotFound = ArrayAdapter.adapt(values).reject(this.actual::containsValue);
        if (valuesNotFound.isEmpty())
        {
            return this.myself;
        }
        throw this.assertionError(shouldContainValues(this.actual, valuesNotFound.toSet()));
    }

    public SELF doesNotContainKey(KEY key)
    {
        return this.doesNotContainKeys(key);
    }

    @SafeVarargs
    public final SELF doesNotContainKeys(KEY... keys)
    {
        return this.doesNotContainKeysForProxy(keys);
    }

    @SuppressWarnings("MethodCanBeVariableArityMethod")
    protected SELF doesNotContainKeysForProxy(KEY[] keys)
    {
        this.isNotNull();
        MutableList<KEY> keysFound = ArrayAdapter.adapt(keys).select(this.actual::containsKey);
        if (keysFound.isEmpty())
        {
            return this.myself;
        }
        throw this.assertionError(shouldNotContainKeys(this.actual, keysFound.toSet()));
    }

    public SELF hasKeySatisfying(Condition<? super KEY> keyCondition)
    {
        this.isNotNull();
        requireNonNull(keyCondition, "The condition to evaluate should not be null");

        for (KEY key : this.actual.keySet())
        {
            if (keyCondition.matches(key))
            {
                return this.myself;
            }
        }

        throw this.assertionError(shouldContainKey(this.actual, keyCondition));
    }

    /**
     * Verifies that the number of key-value entry pairs in the {@link Multimap} is equal to the given one.
     * <p>
     * Example:
     * <pre>{@code
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key", "Value1", "Key", "Value2");
     *
     * // assertion will pass
     * assertThat(multimap).hasSize(2);
     *
     * // assertions will fail
     * assertThat(multimap).hasSize(0);
     * assertThat(multimap).hasSize(1);
     * }</pre>
     *
     * @param expected the expected size of the {@link Multimap}.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual size of the {@link Multimap} is not equal to the expected size.
     */
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

    public SELF hasSizeGreaterThan(int boundary)
    {
        this.isNotNull();
        int actualSize = this.actual.size();
        if (actualSize > boundary)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveSizeGreaterThan(this.actual, actualSize, boundary));
    }

    /**
     * Verifies that the number of key-value entry pairs in the {@link Multimap} is greater than or equal to the
     * boundary.
     * <p>
     * Example:
     * <pre>{@code
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
     *
     * // assertions will pass
     * assertThat(multimap).hasSizeGreaterThanOrEqualTo(1)
     *                     .hasSizeGreaterThanOrEqualTo(2);
     *
     * // assertions will fail
     * assertThat(multimap).hasSizeGreaterThanOrEqualTo(3);
     * assertThat(multimap).hasSizeGreaterThanOrEqualTo(5);
     * }</pre>
     *
     * @param boundary the minimum size (inclusive) the {@link Multimap} should have.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual size of the {@link Multimap} is less than the expected size.
     */
    public SELF hasSizeGreaterThanOrEqualTo(int boundary)
    {
        this.isNotNull();
        int actualSize = this.actual.size();
        if (actualSize >= boundary)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveSizeGreaterThanOrEqualTo(this.actual, actualSize, boundary));
    }

    /**
     * Verifies that the number of key-value entry pairs in the {@link Multimap} is less than the boundary.
     * <p>
     * Example:
     * <pre>{@code
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
     *
     * // assertion will pass
     * assertThat(multimap).hasSizeLessThan(3);
     *
     * // assertions will fail
     * assertThat(multimap).hasSizeLessThan(1);
     * assertThat(multimap).hasSizeLessThan(2);
     * }</pre>
     *
     * @param boundary the maximum size (exclusive) the {@link Multimap} should have.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual size of the {@link Multimap} is not less than the expected size.
     */
    public SELF hasSizeLessThan(int boundary)
    {
        this.isNotNull();
        int actualSize = this.actual.size();
        if (actualSize < boundary)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveSizeLessThan(this.actual, actualSize, boundary));
    }

    /**
     * Verifies that the number of key-value entry pairs in the {@link Multimap} is less than or equal to the boundary.
     * <p>
     * Example:
     * <pre>{@code
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
     *
     * // assertion will pass
     * assertThat(multimap).hasSizeLessThanOrEqualTo(2)
     *                     .hasSizeLessThanOrEqualTo(3);
     *
     * // assertions will fail
     * assertThat(multimap).hasSizeLessThanOrEqualTo(0);
     * assertThat(multimap).hasSizeLessThanOrEqualTo(1);
     * }</pre>
     *
     * @param boundary the maximum expected size of the {@link Multimap}.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual size of the {@link Multimap} is greater than the expected size.
     */
    public SELF hasSizeLessThanOrEqualTo(int boundary)
    {
        this.isNotNull();
        int actualSize = this.actual.size();
        if (actualSize <= boundary)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveSizeLessThanOrEqualTo(this.actual, actualSize, boundary));
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
