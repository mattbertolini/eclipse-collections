/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.assertj.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

import static java.lang.String.format;

/**
 * Creates an error message indicating that an assertion that verifies that a value have certain distinct size failed.
 */
public class ShouldHaveDistinctSize extends BasicErrorMessageFactory
{
    /**
     * Creates a new {@code ShouldHaveDistinctSize}.
     *
     * @param actual       the actual value in the failed assertion.
     * @param actualSize   the distinct size of {@code actual}.
     * @param expectedSize the expected size.
     * @return the created {@code ErrorMessageFactory}.
     */
    public static ErrorMessageFactory shouldHaveDistinctSize(Object actual, int actualSize, int expectedSize)
    {
        return new ShouldHaveDistinctSize(actual, actualSize, expectedSize);
    }

    private ShouldHaveDistinctSize(Object actual, int actualSize, int expectedSize)
    {
        super(format("%nExpected distinct size: %s but was: %s in:%n%s", expectedSize, actualSize, "%s"), actual);
    }
}
