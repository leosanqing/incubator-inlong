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

package org.apache.inlong.manager.dao.mybatis.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections.CollectionUtils;
import org.apache.inlong.manager.common.beans.PageRequest;
import org.apache.inlong.manager.common.beans.SortingField;

import java.util.Collection;
import java.util.stream.Collectors;

@UtilityClass
public class MyBatisUtils {

    public static <T> Page<T> buildPage(PageRequest pageParam) {
        return buildPage(pageParam, null);
    }

    public static <T> Page<T> buildPage(PageRequest pageParam, Collection<SortingField> sortingFields) {
        Page<T> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        // sort field
        if (CollectionUtils.isNotEmpty(sortingFields)) {
            page.addOrder(
                    sortingFields.stream()
                            .map(sortingField -> SortingField.ORDER_ASC.equals(sortingField.getOrder())
                                    ? OrderItem.asc(sortingField.getField())
                                    : OrderItem.desc(sortingField.getField())
                            )
                            .collect(Collectors.toList())
            );
        }
        return page;
    }

}
