/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.manager.common.beans;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class SortingField implements Serializable {

    public static final String ORDER_ASC = "asc";

    public static final String ORDER_DESC = "desc";

    private String field;

    private String order;

    public SortingField(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public SortingField setField(String field) {
        this.field = field;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public SortingField setOrder(String order) {
        this.order = order;
        return this;
    }
}
