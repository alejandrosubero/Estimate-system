

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

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.pojo.BillPojo;
import com.jshandyman.service.entitys.Product;
import com.jshandyman.service.pojo.ProductPojo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;

@Component
public class BillMapper {

    @Autowired
    private ProductMapper productmapper;

    public Bill pojoToEntity(BillPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Bill entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, Bill.class);
        }
        return entity;
    }

    public BillPojo entityToPojo(Bill entity) {
        ModelMapper modelMapper = new ModelMapper();
        BillPojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, BillPojo.class);
        }
        return pojo;
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


