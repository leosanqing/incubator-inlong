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

package org.apache.inlong.manager.dao.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.inlong.manager.common.util.LoginUserUtils;
import org.apache.inlong.manager.dao.entity.BaseDO;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Autofill fields
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();

            LocalDateTime current = LocalDateTime.now();
            if (Objects.isNull(baseDO.getCreateTime())) {
                baseDO.setCreateTime(current);
            }
            if (Objects.isNull(baseDO.getModifyTime())) {
                baseDO.setModifyTime(current);
            }

            String username = LoginUserUtils.getLoginUserDetail().getUserName();
            if (Objects.nonNull(username) && Objects.isNull(baseDO.getCreator())) {
                baseDO.setCreator(username);
            }
            if (Objects.nonNull(username) && Objects.isNull(baseDO.getModifier())) {
                baseDO.setModifier(username);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object modifyTime = getFieldValByName("modifyTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
        }

        Object modifier = getFieldValByName("modifier", metaObject);
        String username = LoginUserUtils.getLoginUserDetail().getUserName();
        if (Objects.nonNull(username) && Objects.isNull(modifier)) {
            setFieldValByName("modifier", username, metaObject);
        }
    }
}
