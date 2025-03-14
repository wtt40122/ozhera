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

package org.apache.ozhera.monitor.dao;

import org.apache.ozhera.monitor.bo.RulePromQLTemplateInfo;
import org.apache.ozhera.monitor.bo.RulePromQLTemplateParam;
import org.apache.ozhera.monitor.dao.model.RulePromQLTemplate;
import org.apache.ozhera.monitor.service.model.PageData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class RulePromQLTemplateDao {


    @Autowired
    private Dao dao;

    public RulePromQLTemplate getById(Integer id) {
        return dao.fetch(RulePromQLTemplate.class, id);
    }

    public boolean insert(RulePromQLTemplate template) {
        if (template.getStatus() == null) {
            template.setStatus(0);
        }
        if (template.getType() == null) {
            template.setType(1);
        }
        if (template.getUpdateTime() == null) {
            template.setUpdateTime(new Date());
        }
        if (template.getCreateTime() == null) {
            template.setCreateTime(new Date());
        }
        try {
            return dao.insert(template) != null;
        } catch (Exception e) {
            log.error("RulePromQLTemplate表插入异常； template={}", template, e);
            return false;
        }
    }

    public boolean updateById(RulePromQLTemplate template) {
        if (template.getUpdateTime() == null) {
            template.setUpdateTime(new Date());
        }
        try {
            return  dao.updateIgnoreNull(template) > 0;
        } catch (Exception e) {
            log.error("RulePromQLTemplate表更新异常； template={}", template, e);
            return false;
        }
    }

    public boolean deleteById(Integer id) {
        try {
            return  dao.delete(RulePromQLTemplate.class, id) > 0;
        } catch (Exception e) {
            log.error("RulePromQLTemplate表删除异常； id={}", id, e);
            return false;
        }
    }

    /**
     * 根据条件搜索
     * @param user
     * @param param
     * @return
     */
    public PageData<List<RulePromQLTemplateInfo>> searchByCond(String user, RulePromQLTemplateParam param) {
        PageData<List<RulePromQLTemplateInfo>> pageData = new PageData<>();
        pageData.setPage(param.getPage());
        pageData.setPageSize(param.getPageSize());
        pageData.setTotal(0L);
        Cnd cnd = Cnd.where("creater", "=", user);
        if (StringUtils.isNotBlank(param.getName())) {
            StringBuilder name = new StringBuilder();
            name.append("%").append(param.getName()).append("%");
            cnd = cnd.and("name", "LIKE", name.toString());
        }
        if (param.isPaging()) {
            int total = dao.count(RulePromQLTemplate.class, cnd);
            if (total <= 0) {
                return pageData;
            }
            pageData.setTotal((long)total);
        }
        List<RulePromQLTemplate> list =  dao.query(RulePromQLTemplate.class, cnd, new Pager(param.getPage(), param.getPageSize()));
        if (!CollectionUtils.isEmpty(list)) {
            pageData.setList(list.stream().map(temple -> {
                RulePromQLTemplateInfo info = new RulePromQLTemplateInfo();
                BeanUtils.copyProperties(temple, info);
                if (temple.getCreateTime() != null) {
                    info.setCreateTime(temple.getCreateTime().getTime());
                }
                if (temple.getUpdateTime() != null) {
                    info.setUpdateTime(temple.getUpdateTime().getTime());
                }
                return info;
            }).collect(Collectors.toList()));
        }
        return pageData;
    }

    /**
     * 根据名称查询
     * @param user
     * @param name
     * @return
     */
    public List<RulePromQLTemplate> getByName(String user, String name) {
        Cnd cnd = Cnd.where("creater", "=", user).and("name", "=", name);
        return  dao.query(RulePromQLTemplate.class, cnd);
    }


}
