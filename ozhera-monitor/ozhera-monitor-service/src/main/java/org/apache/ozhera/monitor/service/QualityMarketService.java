/*
 *  Copyright (C) 2020 Xiaomi Corporation
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.apache.ozhera.monitor.service;

import org.apache.ozhera.monitor.result.Result;


public interface QualityMarketService {
    
    Result createMarket(String user, String marketName, String serviceList, String remark);
    
    Result searchMarket(String user, int id);
    
    //改
    Result updateMarket(String user, int id, String serviceList, String marketName, String remark);
    
    //删
    Result deleteMarket(String user, Integer id);
    
    
    //查列表
    Result searchMarketList(String user, int pageSize, int pageNo, String creator, String marketName,
            String serviceName);
    
}


