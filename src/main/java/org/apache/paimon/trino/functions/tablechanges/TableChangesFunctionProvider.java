/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.paimon.trino.functions.tablechanges;

import org.apache.paimon.trino.TrinoMetadataFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.trino.plugin.base.classloader.ClassLoaderSafeConnectorTableFunction;
import io.trino.spi.function.table.ConnectorTableFunction;

import static java.util.Objects.requireNonNull;

/** TableChangesFunctionProvider. */
public class TableChangesFunctionProvider implements Provider<ConnectorTableFunction> {
    private final TrinoMetadataFactory trinoMetadataFactory;

    @Inject
    public TableChangesFunctionProvider(TrinoMetadataFactory trinoMetadataFactory) {
        this.trinoMetadataFactory =
                requireNonNull(trinoMetadataFactory, "trinoMetadataFactory is null");
    }

    @Override
    public ConnectorTableFunction get() {
        return new ClassLoaderSafeConnectorTableFunction(
                new TableChangesFunction(trinoMetadataFactory), getClass().getClassLoader());
    }
}
