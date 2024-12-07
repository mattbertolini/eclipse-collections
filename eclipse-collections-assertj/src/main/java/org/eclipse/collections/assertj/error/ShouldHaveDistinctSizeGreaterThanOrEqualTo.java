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
import org.assertj.core.error.ShouldHaveSizeGreaterThanOrEqualTo;

import static java.lang.String.format;

public class ShouldHaveDistinctSizeGreaterThanOrEqualTo extends BasicErrorMessageFactory
{
    public static ErrorMessageFactory shouldHaveDistinctSizeGreaterThanOrEqualTo(Object actual, int actualSize, int expectedMinSize) {
        return new ShouldHaveDistinctSizeGreaterThanOrEqualTo(actual, actualSize, expectedMinSize);
    }

    private ShouldHaveDistinctSizeGreaterThanOrEqualTo(Object actual, int actualSize, int expectedSize) {
        super(format("%n" +
                        "Expecting distinct size of:%n" +
                        "  %%s%n" +
                        "to be greater than or equal to %s but was %s", expectedSize, actualSize),
                actual);
    }
}
