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

import java.util.function.Consumer;

import org.assertj.core.api.AbstractSoftAssertions;
import org.assertj.core.api.SoftAssertionsProvider;

public final class SoftAssertions extends AbstractSoftAssertions implements EclipseCollectionsSoftAssertionsProvider
{
    public SoftAssertions()
    {
        // Do nothing
    }

    public static void assertSoftly(Consumer<SoftAssertions> softly)
    {
        SoftAssertionsProvider.assertSoftly(SoftAssertions.class, softly);
    }
}
