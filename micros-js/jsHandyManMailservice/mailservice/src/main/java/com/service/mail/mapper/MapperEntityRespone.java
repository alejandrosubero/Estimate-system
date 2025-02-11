/*
Create on Sun Sep 11 00:37:30 EDT 2022
*Copyright (C) 122.
@author alejandro
@author js
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: email service for send </p>
*/


package com.service.mail.mapper;

import com.service.mail.pojo.EntityRespone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperEntityRespone {

    public <T> EntityRespone setEntityT(List<T> t) {
        EntityRespone entityRespone = new EntityRespone();
        List<Object> list = (List<Object>) t;
        entityRespone.setEntidades(list);
        return entityRespone;
    }

    public <T> EntityRespone setEntityTobj(T t) {
        EntityRespone entityRespone = new EntityRespone();
        List<Object> ob = new ArrayList<>();
        Object objects = t;
        ob.add(objects);
        entityRespone.setEntidades(ob);
        return entityRespone;
    }

    public <T> EntityRespone setEntityResponT(T t, String mensaje, String error) {
        EntityRespone entityRespone = new EntityRespone();
        List<Object> ob = new ArrayList<>();
        Object objects = t;
        ob.add(objects);
        entityRespone.setError(error);
        entityRespone.setMensaje(mensaje);
        entityRespone.setEntidades(ob);
        return entityRespone;
    }

    public <T> EntityRespone setEntityResponseT(List<T> t, String mensaje, String error) {
        EntityRespone entityRespone = new EntityRespone();
        List<Object> objects = (List<Object>) t;
        entityRespone.setError(error);
        entityRespone.setMensaje(mensaje);
        entityRespone.setEntidades(objects);
        return entityRespone;
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


