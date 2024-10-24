/*
 * Copyright (C) 2020 Xiaomi Corporation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.ozhera.monitor.bo;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RulePromQLTemplateInfo {

    private int id;
    private String name;
    private String promql;
    private Integer type;
    private String remark;
    private String creater;
    private Integer status;
    private long createTime;
    private long updateTime;

}