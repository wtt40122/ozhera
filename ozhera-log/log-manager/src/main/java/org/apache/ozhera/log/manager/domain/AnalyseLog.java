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
package org.apache.ozhera.log.manager.domain;

import org.apache.ozhera.log.manager.dao.MilogLogstoreDao;
import org.apache.ozhera.log.manager.domain.analyse.AggrCalcu;
import org.apache.ozhera.log.manager.mapper.MilogAnalyseGraphMapper;
import org.apache.ozhera.log.manager.model.bo.CalcuAggrParam;
import org.apache.ozhera.log.manager.model.dto.LogAnalyseDataDTO;
import org.apache.ozhera.log.manager.model.pojo.MilogAnalyseGraphDO;
import org.apache.ozhera.log.manager.model.pojo.MilogLogStoreDO;
import org.apache.ozhera.log.manager.model.vo.LogAnalyseDataPreQuery;
import org.apache.ozhera.log.manager.model.vo.LogAnalyseDataQuery;
import org.apache.ozhera.log.manager.model.vo.LogQuery;
import org.apache.ozhera.log.manager.service.extension.common.CommonExtensionService;
import org.apache.ozhera.log.manager.service.extension.common.CommonExtensionServiceFactory;
import com.xiaomi.youpin.docean.anno.Service;
import com.xiaomi.youpin.docean.plugin.es.EsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AnalyseLog {
    @Resource
    private MilogLogstoreDao logstoreDao;

    @Resource
    private EsCluster esCluster;

    @Resource
    private MilogAnalyseGraphMapper graphMapper;

    @Resource
    private AggrCalcu aggrCalcu;

    private CommonExtensionService commonExtensionService;

    public void init() {
        commonExtensionService = CommonExtensionServiceFactory.getCommonExtensionService();
    }


    public LogAnalyseDataDTO getData(LogAnalyseDataPreQuery query) throws IOException {
        return getData(query.getStoreId(), query.getFieldName(), query.getTypeCode(), query.getGraphParam(), query.getStartTime(), query.getEndTime());
    }

    public LogAnalyseDataDTO getData(LogAnalyseDataQuery query) throws IOException {
        MilogAnalyseGraphDO graph = graphMapper.selectById(query.getGraphId());
        return getData(graph.getStoreId(), graph.getFieldName(), graph.getGraphType(), graph.getGraphParam(), query.getStartTime(), query.getEndTime());
    }

    private LogAnalyseDataDTO getData(Long storeId, String fieldName, Integer graphType, String graphParam, Long startTime, Long endTime) throws IOException {
        if (storeId == null || fieldName == null) {
            return null;
        }
        MilogLogStoreDO logStore = logstoreDao.queryById(storeId);
        EsService esService = esCluster.getEsService(logStore.getEsClusterId());
        String esIndex = commonExtensionService.getSearchIndex(logStore.getId(), logStore.getEsIndex());

        // Filter criteria
        LogQuery logQuery = new LogQuery();
        logQuery.setStoreId(logStore.getId());
        logQuery.setStartTime(startTime);
        logQuery.setEndTime(endTime - 1000);
        logQuery.setLogstore(logStore.getLogstoreName());
        BoolQueryBuilder boolQueryBuilder = commonExtensionService.commonRangeQuery(logQuery);

        AggregationBuilder aggrs = aggrCalcu.getAggr(new CalcuAggrParam(graphType, graphParam, fieldName, startTime, endTime));
        if (aggrs == null) {
            return null;
        }

        // Construct the query object
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(0);
        searchSourceBuilder.aggregation(aggrs);
        searchSourceBuilder.timeout(new TimeValue(1, TimeUnit.MINUTES));
        SearchRequest request = new SearchRequest(esIndex);
        request.source(searchSourceBuilder);

        // query
        SearchResponse response = esService.search(request);

        return aggrCalcu.formatRes(graphType, response);
    }

}
