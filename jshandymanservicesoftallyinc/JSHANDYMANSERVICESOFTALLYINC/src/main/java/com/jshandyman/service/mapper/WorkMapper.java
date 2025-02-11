

/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.mapper;



import com.jshandyman.service.entitys.Work;
import com.jshandyman.service.pojo.WorkPojo;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkMapper extends AbstractConverter<Work, WorkPojo> {

    @Autowired
    private ClientMapper clientmapper;

    @Autowired
    private PaymentMapper paymentmapper;

    @Autowired
    private BillMapper billmapper;



    public Work pojoToEntity(WorkPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Work entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, Work.class);
        }
        return entity;
    }

    public WorkPojo entityToPojo(Work entity) {
        ModelMapper modelMapper = new ModelMapper();
        WorkPojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, WorkPojo.class);
           // pojo.setDaysLate(entity.getDaysLate());
        }

        return pojo;
    }

    public List<WorkPojo> entityToPojoList(List<Work> works){
        List<WorkPojo> listaWork = new ArrayList<WorkPojo>();
        works.stream().forEach(work -> listaWork.add(entityToPojo(work)));
        return listaWork;
    }

    @Override
    protected WorkPojo convert(@NonNull final Work source) {
        return null;
    }
}
 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


