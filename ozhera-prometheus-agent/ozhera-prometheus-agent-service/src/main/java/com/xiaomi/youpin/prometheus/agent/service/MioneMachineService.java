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
package com.xiaomi.youpin.prometheus.agent.service;

import com.xiaomi.youpin.prometheus.agent.domain.Ips;
import com.xiaomi.youpin.prometheus.agent.service.api.MioneMachineServiceExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MioneMachineService {

    @Autowired
    private MioneMachineServiceExtension mioneMachineServiceExtension;

//    @NacosValue(value = "${mione.machine.port}", autoRefreshed = true)
//    private String machinePort;
//
//    @NacosValue(value = "${mione.container.port}", autoRefreshed = true)
//    private String containerPort;

    /*@Reference(interfaceClass = QuotaService.class, group = "${ref.quota.service.group}", check = false)
    private QuotaService quotaService;

    @Reference(interfaceClass = ResourceService.class, group = "${ref.quota.service.group}", check = false)
    private ResourceService resourceService;*/

    public List<Ips> queryMachineList(String type) {
       /* List<String> result = new ArrayList<>();
        List<String> ips = new ArrayList<>();
        try {
            Result<List<ResourceBo>> resourceResult = resourceService.list();
            List<ResourceBo> data = resourceResult.getData();
            data.forEach((resource) -> {
                if (resource.getLables() != null && Objects.equals(resource.getLables().get("type"), "docker")) {
                    ips.add(resource.getIp());
                }
            });
            if (ips.size() > 0) {
                for (String ip : ips) {
                    if ("1".equals(type)) {
                        result.add(ip + ":" + machinePort);
                    } else {
                        result.add(ip + ":" + containerPort);
                    }
                }
            }
        } catch (Exception e) {
            log.error("quotaService.resourceService接口失败：", e);
        }
        List<Ips> defaultResult = new ArrayList<>();
        Ips ips2 = new Ips();
        ips2.setTargets(result);
        defaultResult.add(ips2);
        return defaultResult;
*/
        return mioneMachineServiceExtension.queryMachineList(type);
    }
}
