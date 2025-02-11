
/*
Create on Sun Sep 11 00:37:29 EDT 2022
*Copyright (C) 122.
@author alejandro
@author js
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: email service for send </p>
*/


package com.service.mail.service;

import com.service.mail.entitys.MailSender;
import com.service.mail.pojo.MailSenderPojo;

import java.util.List;


public interface MailSenderService {


    public String sendMail();

    public MailSender findById(Long id);

    public boolean saveMailSender(MailSender mailsender);

    public List<MailSender> getAllMailSender();

    public boolean updateMailSender(MailSender mailsender);

    public boolean saveOrUpdateMailSender(MailSender mailsender);

    public List<MailSender> findByWasSendContaining(String wasSend);

    public List<MailSenderPojo> findByWasSend(String wasSend);


    public MailSender findByEmail(String email);

    public List<MailSender> findByPortContaining(String port);

    public List<MailSender> findByTemplateContaining(String template);

    public List<MailSender> findByActiveContaining(Boolean active);

    public List<MailSender> findByEmailContaining(String email);

    public MailSender findByTemplate(String template);

    //    public MailSender findByPort(String port);
    //    public MailSender findByActive(Boolean active);



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


