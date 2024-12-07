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
import static org.assertj.core.error.ShouldContainValue.shouldContainValue;
import static org.assertj.core.error.ShouldContainValues.shouldContainValues;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldHaveSizeBetween.shouldHaveSizeBetween;
import static org.assertj.core.error.ShouldHaveSizeGreaterThan.shouldHaveSizeGreaterThan;
import static org.assertj.core.error.ShouldHaveSizeGreaterThanOrEqualTo.shouldHaveSizeGreaterThanOrEqualTo;
import static org.assertj.core.error.ShouldHaveSizeLessThan.shouldHaveSizeLessThan;
import static org.assertj.core.error.ShouldHaveSizeLessThanOrEqualTo.shouldHaveSizeLessThanOrEqualTo;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.error.ShouldNotContainKeys.shouldNotContainKeys;
import static org.eclipse.collections.assertj.error.ShouldHaveDistinctSize.shouldHaveDistinctSize;
import static org.eclipse.collections.assertj.error.ShouldHaveDistinctSizeGreaterThan.shouldHaveDistinctSizeGreaterThan;
import static org.eclipse.collections.assertj.error.ShouldHaveDistinctSizeGreaterThanOrEqualTo.shouldHaveDistinctSizeGreaterThanOrEqualTo;

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

    /**
     * Verifies that the actual {@link Multimap} contains the given keys.
     * <p>
     * Example:
     * <pre>{@code
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key2", "Value2");
     *
     * // assertion will pass
     * assertThat(multimap).containsKeys("Key1", "Key2");
     *
     * // assertion will fail
     * assertThat(multimap).containsKeys("Key3");
     * }</pre>
     *
     * @param keys the keys that are expected to be present in the {@link Multimap}.
     * @return this assertion object.
     * @throws AssertionError if the actual {@link Multimap} does not contain the given keys.
     */
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

    // This method is protected in order to be proxied for SoftAssertions / Assumptions.
    // The public method for it (the one not ending with "ForProxy") is marked as final and annotated with @SafeVarargs
    // in order to avoid compiler warning in user code
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

    public SELF hasDistinctSize(int expected)
    {
        this.isNotNull();
        int actualSize = this.actual.sizeDistinct();
        if (actualSize == expected)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveDistinctSize(this.actual, actualSize, expected));
    }

    public SELF hasDistinctSizeGreaterThan(int boundary)
    {
        this.isNotNull();
        int actualSize = this.actual.sizeDistinct();
        if (actualSize > boundary)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveDistinctSizeGreaterThan(this.actual, actualSize, boundary));
    }

    public SELF hasDistinctSizeGreaterThanOrEqualTo(int boundary)
    {
        this.isNotNull();
        int actualSize = this.actual.sizeDistinct();
        if (actualSize >= boundary)
        {
            return this.myself;
        }
        throw this.assertionError(shouldHaveDistinctSizeGreaterThanOrEqualTo(this.actual, actualSize, boundary));
    }

    public SELF hasKeySatisfying(Condition<? super KEY> keyCondition)
    {
        this.isNotNull();
        requireNonNull(keyCondition, "The condition to evaluate should not be null");

        if (this.actual.keysView().anySatisfy(keyCondition::matches))
        {
            return this.myself;
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

    /**
     * Verifies that the number of key-value entry pairs in the {@link Multimap} is between the given boundaries
     * (inclusive).
     * <p>
     * Example:
     * <pre>{@code
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key1", "Value2", "Key2", "Value3");
     *
     * // assertion will pass
     * assertThat(multimap).hasSizeBetween(1, 4)
     *                     .hasSizeBetween(2, 3);
     *
     * // assertions will fail
     * assertThat(multimap).hasSizeBetween(4, 5);
     * }</pre>
     *
     * @param lowerBoundary  the lower boundary compared to which actual size should be greater than or equal to.
     * @param higherBoundary the higher boundary compared to which actual size should be less than or equal to.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual size of the {@link Multimap} is not between the given boundaries.
     */
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
     * Verifies that the number of key-value entry pairs in the {@link Multimap} is greater than the specified boundary.
     * <p>
     * Example:
     * <pre>{@code
     * Multimap<String, String> multimap = Multimaps.mutable.list.with("Key1", "Value1", "Key1", "Value2", "Key2", "Value3");
     *
     * // assertion will pass
     * assertThat(multimap).hasSizeGreaterThan(1);
     *
     * // assertions will fail
     * assertThat(multimap).hasSizeGreaterThan(3);
     * assertThat(multimap).hasSizeGreaterThan(4);
     * }</pre>
     *
     * @param boundary the size that the actual number of key-value pairs should exceed.
     * @return {@code this} assertion object.
     * @throws AssertionError if the actual size of the {@link Multimap} is not greater than the specified boundary.
     */
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

    public SELF hasValueSatisfying(Condition<? super VALUE> valueCondition)
    {
        this.isNotNull();
        requireNonNull(valueCondition, "The condition to evaluate should not be null");

        if (this.actual.valuesView().anySatisfy(valueCondition::matches))
        {
            return this.myself;
        }
        throw this.assertionError(shouldContainValue(this.actual, valueCondition));
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
