/**
 * Mule Twitter Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.twitter;

import org.mule.tck.FunctionalTestCase;

public class NamespaceHandlerTestCase extends FunctionalTestCase {

    @Override
    protected String getConfigResources() {
        return "twitter-namespace-config.xml";
    }

    public void testLoad() throws Exception {
    }
}