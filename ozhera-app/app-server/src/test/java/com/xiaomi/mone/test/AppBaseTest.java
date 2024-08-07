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
//package com.xiaomi.mone.test;
//
//import com.google.gson.Gson;
//import com.xiaomi.mone.app.api.model.HeraAppBaseInfoModel;
//import com.xiaomi.mone.app.api.model.HeraAppBaseInfoParticipant;
//import com.xiaomi.mone.app.api.model.HeraAppBaseQuery;
//import com.xiaomi.mone.app.AppBootstrap;
//import com.xiaomi.mone.app.service.impl.HeraAppBaseInfoService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
///**
// * @author gaoxihui
// * @date 2022/11/2 2:59 下午
// */
//@Slf4j
//@SpringBootTest(classes = AppBootstrap.class)
//public class AppBaseTest {
//
//    @Autowired
//    HeraAppBaseInfoService baseInfoService;
//
//    @Test
//    public void testBaseInfoDb(){
//        HeraAppBaseInfoModel baseInfo = new HeraAppBaseInfoModel();
//        Long count = baseInfoService.count(baseInfo);
//        System.out.println(count);
//    }
//
//    @Test
//    public void test2(){
////        Long aLong = baseInfoService.countByParticipant(new HeraAppBaseQuery());
//        List<HeraAppBaseInfoParticipant> heraAppBaseInfoParticipants = baseInfoService.queryByParticipant(new HeraAppBaseQuery());
//        System.out.println(new Gson().toJson(heraAppBaseInfoParticipants));
//    }
//
//    @Test
//    public void test3(){
//        HeraAppBaseQuery heraAppBaseQuery = new HeraAppBaseQuery();
//        heraAppBaseQuery.setParticipant("");
//        heraAppBaseQuery.setAppName("");
//        Long aLong = baseInfoService.countByParticipant(heraAppBaseQuery);
//        System.out.println("hera app count:" + aLong);
//    }
//}
