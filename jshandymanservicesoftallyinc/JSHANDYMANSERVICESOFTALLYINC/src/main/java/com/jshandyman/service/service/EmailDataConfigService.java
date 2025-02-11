
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.service;

import com.jshandyman.service.entitys.EmailDataConfig;
import com.jshandyman.service.pojo.EmailDataConfigPojo;

import java.util.List;


public interface EmailDataConfigService {

    public EmailDataConfig encript(EmailDataConfig emailDataConfig);
    public EmailDataConfigPojo decrypt(EmailDataConfig emailDataConfig);

    public EmailDataConfigPojo findByHost(String host);

    public EmailDataConfigPojo findByPort(String port);

    public EmailDataConfigPojo findByPortAndCompanys(String port, String company);

    public EmailDataConfigPojo findByMailUsername(String mailUsername);

    public EmailDataConfigPojo findByMailPassword(String mailPassword);

    public List<EmailDataConfigPojo> findByHostContaining(String host, String company);

    public List<EmailDataConfigPojo> findByPortContaining(String port);

    public List<EmailDataConfigPojo> findByMailUsernameContaining(String mailUsername);

    public List<EmailDataConfigPojo> findByMailPasswordContaining(String mailPassword);

    public EmailDataConfigPojo findById(Long id);

    public boolean saveEmailDataConfig(EmailDataConfig emaildataconfig);

    public List<EmailDataConfigPojo> getAllEmailDataConfig();

    public boolean deleteEmailDataConfig(Long id);

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


