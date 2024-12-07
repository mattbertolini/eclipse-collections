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
import org.assertj.core.error.ShouldHaveSizeLessThan;

import static java.lang.String.format;

public class ShouldHaveDistinctSizeLessThan extends BasicErrorMessageFactory
{
    public static ErrorMessageFactory shouldHaveDistinctSizeLessThan(Object actual, int actualSize, int expectedMaxSize) {
        return new ShouldHaveDistinctSizeLessThan(actual, actualSize, expectedMaxSize);
    }

    private ShouldHaveDistinctSizeLessThan(Object actual, int actualSize, int expectedSize) {
        super(format("%n" +
                        "Expecting distinct size of:%n" +
                        "  %%s%n" +
                        "to be less than %s but was %s", expectedSize, actualSize),
                actual);
    }
}
