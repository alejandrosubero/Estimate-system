

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
import com.service.mail.entitys.EmailDataConfig;
import com.service.mail.pojo.EmailDataConfigPojo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;

    @Component
    public class EmailDataConfigMapper {

    public EmailDataConfig pojoToEntity(EmailDataConfigPojo pojo) {
		ModelMapper modelMapper = new ModelMapper();
        EmailDataConfig entity = null;

		if ( pojo != null) {
   		entity = modelMapper.map(pojo, EmailDataConfig.class);
		}
	return  entity;
}
    public EmailDataConfig entityToPojo(EmailDataConfig entity) {
 		ModelMapper modelMapper = new ModelMapper();
        EmailDataConfig pojo = null;

		if ( entity != null) {
   		pojo = modelMapper.map(entity, EmailDataConfig.class);
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


