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

package org.apache.ozhera.prometheus.agent.test;

import com.google.gson.Gson;
/**
 * @author zhangxiaowei6
 */
public class feishuTest {

    public static final Gson gson = new Gson();

    String body = "{\n" +
            "    \"receiver\":\"web\\\\.hook\",\n" +
            "    \"status\":\"firing\",\n" +
            "    \"alerts\":[\n" +
            "        {\n" +
            "            \"status\":\"firing\",\n" +
            "            \"labels\":{\n" +
            "                \"alert_key\":\"k8s_container_cpu_use_rate\",\n" +
            "                \"alert_op\":\"\\u003e\",\n" +
            "                \"alert_value\":\"0.01\",\n" +
            "                \"alertname\":\"k8s_container_cpu_use_rate-2-k8s_container_cpu_use_rate-1678682933270\",\n" +
            "                \"app_iam_id\":\"null\",\n" +
            "                \"application\":\"2_hera_demo_client\",\n" +
            "                \"calert\":\"k8s容器机CPU使用率\",\n" +
            "                \"container\":\"hera-demo-client-container\",\n" +
            "                \"exceptViewLables\":\"detailRedirectUrl.paramType\",\n" +
            "                \"group_key\":\"localhost:5195\",\n" +
            "                \"image\":\"sha256:e3adf286245db8a00e28fa4a3b37d505e6f49efa8cdd5c7d05c3a73fbfc9501c\",\n" +
            "                \"instance\":\"localhost:5195\",\n" +
            "                \"ip\":\"localhost\",\n" +
            "                \"job\":\"mione-china-cadvisor-k8s\",\n" +
            "                \"name\":\"k8s_hera-demo-client-container_hera-demo-client-59446dd69f-kjdsv_hera-namespace_502ea675-c131-4379-9f3d-f0d7949a9967_0\",\n" +
            "                \"namespace\":\"hera-namespace\",\n" +
            "                \"pod\":\"hera-demo-client-59446dd69f-kjdsv\",\n" +
            "                \"project_id\":\"2\",\n" +
            "                \"project_name\":\"hera-demo-client\",\n" +
            "                \"restartCounts\":\"0\",\n" +
            "                \"send_interval\":\"5m\",\n" +
            "                \"serverEnv\":\"dev\",\n" +
            "                \"system\":\"mione\"\n" +
            "            },\n" +
            "            \"annotations\":{\n" +
            "                \"summary\":\"test\",\n" +
            "                \"title\":\"hera-demo-client\\u0026k8s容器机CPU使用率\"\n" +
            "            },\n" +
            "            \"startsAt\":\"2023-03-13T07:35:33.633Z\",\n" +
            "            \"endsAt\":\"0001-01-01T00:00:00Z\",\n" +
            "            \"generatorURL\":\"http://prometheus-74bb956ff4-ss7t9:9090/graph?g0.expr\\u003drate%28container_cpu_user_seconds_total%7Bapplication%3D%222_hera_demo_client%22%2Cimage%21%3D%22%22%2Csystem%3D%22mione%22%7D%5B1m%5D%29+%2A+100+%3E+0.009999999776482582\\u0026g0.tab\\u003d1\",\n" +
            "            \"fingerprint\":\"cbd34f56f02de7a0\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"groupLabels\":{\n" +
            "        \"alertname\":\"k8s_container_cpu_use_rate-2-k8s_container_cpu_use_rate-1678682933270\",\n" +
            "        \"group_key\":\"localhost:5195\"\n" +
            "    },\n" +
            "    \"commonLabels\":{\n" +
            "        \"alert_key\":\"k8s_container_cpu_use_rate\",\n" +
            "        \"alert_op\":\"\\u003e\",\n" +
            "        \"alert_value\":\"0.01\",\n" +
            "        \"alertname\":\"k8s_container_cpu_use_rate-2-k8s_container_cpu_use_rate-1678682933270\",\n" +
            "        \"app_iam_id\":\"null\",\n" +
            "        \"application\":\"2_hera_demo_client\",\n" +
            "        \"calert\":\"k8s容器机CPU使用率\",\n" +
            "        \"container\":\"hera-demo-client-container\",\n" +
            "        \"exceptViewLables\":\"detailRedirectUrl.paramType\",\n" +
            "        \"group_key\":\"localhost:5195\",\n" +
            "        \"image\":\"sha256:e3adf286245db8a00e28fa4a3b37d505e6f49efa8cdd5c7d05c3a73fbfc9501c\",\n" +
            "        \"instance\":\"localhost:5195\",\n" +
            "        \"ip\":\"localhost\",\n" +
            "        \"job\":\"mione-china-cadvisor-k8s\",\n" +
            "        \"name\":\"k8s_hera-demo-client-container_hera-demo-client-59446dd69f-kjdsv_hera-namespace_502ea675-c131-4379-9f3d-f0d7949a9967_0\",\n" +
            "        \"namespace\":\"hera-namespace\",\n" +
            "        \"pod\":\"hera-demo-client-59446dd69f-kjdsv\",\n" +
            "        \"project_id\":\"2\",\n" +
            "        \"project_name\":\"hera-demo-client\",\n" +
            "        \"restartCounts\":\"0\",\n" +
            "        \"send_interval\":\"5m\",\n" +
            "        \"serverEnv\":\"dev\",\n" +
            "        \"system\":\"mione\"\n" +
            "    },\n" +
            "    \"commonAnnotations\":{\n" +
            "        \"summary\":\"test\",\n" +
            "        \"title\":\"hera-demo-client\\u0026k8s容器机CPU使用率\"\n" +
            "    },\n" +
            "    \"externalURL\":\"http://localhost:30903\",\n" +
            "    \"version\":\"4\",\n" +
            "    \"groupKey\":\"{}/{send_interval\\u003d\\\"5m\\\"}:{alertname\\u003d\\\"k8s_container_cpu_use_rate-2-k8s_container_cpu_use_rate-1678682933270\\\", group_key\\u003d\\\"localhost:5195\\\"}\",\n" +
            "    \"truncatedAlerts\":0\n" +
            "}";

}