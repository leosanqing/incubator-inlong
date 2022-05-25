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

package org.apache.inlong.manager.common.pojo.sink;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.inlong.manager.common.auth.DefaultAuthentication;
import org.apache.inlong.manager.common.enums.DataFormat;
import org.apache.inlong.manager.common.pojo.sink.ck.ClickHouseSink;
import org.apache.inlong.manager.common.pojo.sink.es.ElasticsearchSink;
import org.apache.inlong.manager.common.pojo.sink.hbase.HBaseSink;
import org.apache.inlong.manager.common.pojo.sink.hive.HiveSink;
import org.apache.inlong.manager.common.pojo.sink.iceberg.IcebergSink;
import org.apache.inlong.manager.common.pojo.sink.kafka.KafkaSink;
import org.apache.inlong.manager.common.pojo.sink.postgres.PostgresSink;
import org.apache.inlong.manager.common.pojo.stream.StreamNode;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.apache.inlong.manager.common.enums.SinkType.SINK_CLICKHOUSE;
import static org.apache.inlong.manager.common.enums.SinkType.SINK_ELASTICSEARCH;
import static org.apache.inlong.manager.common.enums.SinkType.SINK_HBASE;
import static org.apache.inlong.manager.common.enums.SinkType.SINK_HIVE;
import static org.apache.inlong.manager.common.enums.SinkType.SINK_ICEBERG;
import static org.apache.inlong.manager.common.enums.SinkType.SINK_KAFKA;
import static org.apache.inlong.manager.common.enums.SinkType.SINK_POSTGRES;

/**
 * Stream sink info.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "sinkType", visible = true)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = ClickHouseSink.class, name = SINK_CLICKHOUSE),
        @JsonSubTypes.Type(value = ElasticsearchSink.class, name = SINK_ELASTICSEARCH),
        @JsonSubTypes.Type(value = HBaseSink.class, name = SINK_HBASE),
        @JsonSubTypes.Type(value = HiveSink.class, name = SINK_HIVE),
        @JsonSubTypes.Type(value = IcebergSink.class, name = SINK_ICEBERG),
        @JsonSubTypes.Type(value = KafkaSink.class, name = SINK_KAFKA),
        @JsonSubTypes.Type(value = PostgresSink.class, name = SINK_POSTGRES),
})
@ApiModel("Stream sink info")
public abstract class StreamSink extends StreamNode {

    @ApiModelProperty("Sink id")
    private Integer id;

    @ApiModelProperty("Inlong group id")
    private String inlongGroupId;

    @ApiModelProperty("Inlong stream id")
    private String inlongStreamId;

    @ApiModelProperty("Sink type, including: HIVE, ICEBERG, etc.")
    private String sinkType;

    @ApiModelProperty("Sink name, unique in one stream.")
    private String sinkName;

    @ApiModelProperty("Sink description")
    private String description;

    @ApiModelProperty("Inlong cluster name")
    private String inlongClusterName;

    @ApiModelProperty("Data node name")
    private String dataNodeName;

    @ApiModelProperty("Sort task name")
    private String sortTaskName;

    @ApiModelProperty("Sort consumer group")
    private String sortConsumerGroup;

    @ApiModelProperty(value = "Whether to enable create sink resource? 0: disable, 1: enable. default is 1",
            notes = "Such as create Hive table")
    private Integer enableCreateResource = 1;

    @ApiModelProperty("Backend operation log")
    private String operateLog;

    @ApiModelProperty("Status")
    private Integer status;

    @ApiModelProperty("Previous SimpleSourceStatus")
    private Integer previousStatus;

    @ApiModelProperty("Creator")
    private String creator;

    @ApiModelProperty("Modifier")
    private String modifier;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @ApiModelProperty("Sink field list")
    private List<SinkField> fieldList;

    @ApiModelProperty("Properties for sink")
    private Map<String, Object> properties;

    @JsonIgnore
    @ApiModelProperty("Data format type for stream sink")
    private DataFormat dataFormat = DataFormat.NONE;

    @JsonIgnore
    @ApiModelProperty("Authentication info if needed")
    private DefaultAuthentication authentication;

    public SinkRequest genSinkRequest() {
        return null;
    }

}
