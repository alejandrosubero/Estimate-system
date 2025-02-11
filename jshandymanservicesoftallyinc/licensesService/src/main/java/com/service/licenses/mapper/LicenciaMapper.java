

/*
Create on Sun Apr 24 19:56:28 EDT 2022
*Copyright (C) 122.
@author alejandro subero
@author Companys
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: service for licenses </p>
*/


package com.service.licenses.mapper;
import com.service.licenses.entitys.Licencia;
import com.service.licenses.pojo.LicenciaPojo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;

    @Component
    public class LicenciaMapper {

    public Licencia pojoToEntity(LicenciaPojo pojo) {
		ModelMapper modelMapper = new ModelMapper();
        Licencia entity = null;

		if ( pojo != null) {
   		entity = modelMapper.map(pojo, Licencia.class);
		}
	return  entity;
}
    public Licencia entityToPojo(Licencia entity) {
 		ModelMapper modelMapper = new ModelMapper();
        Licencia pojo = null;

		if ( entity != null) {
   		pojo = modelMapper.map(entity, Licencia.class);
		}
	return  pojo;
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


