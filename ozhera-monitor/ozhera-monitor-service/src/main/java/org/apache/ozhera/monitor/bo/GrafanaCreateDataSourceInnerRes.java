/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ozhera.monitor.bo;

import lombok.Data;

/**
 * @author zhangxiaowei6
 * @date 2023-02-23
 */
@Data
public class GrafanaCreateDataSourceInnerRes {
    private int id;
    private String uid;
    private int orgId;
    private String name;
    private String type;
    private String typeLogoUrl;
    private String access;
    private String url;
    private String user;
    private String database;
    private boolean basicAuth;
    private String basicAuthUser;
    private boolean withCredentials;
    private boolean isDefault;
    private int version;
    private boolean readOnly;
}
