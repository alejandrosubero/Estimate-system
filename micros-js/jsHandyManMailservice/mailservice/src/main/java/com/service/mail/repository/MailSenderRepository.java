
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


package com.service.mail.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.service.mail.entitys.MailSender;
import org.springframework.transaction.annotation.Transactional;

public interface MailSenderRepository extends CrudRepository< MailSender, Long> {
 
	public Optional<MailSender> findByTemplate(String template);
	public List<MailSender> findByWasSend(String wasSend);
	public List<MailSender> findByWasSendContaining(String wasSend);
	public List<MailSender> findByTemplateContaining(String template);
	public Optional<MailSender> findByEmail(String email);
	public List<MailSender> findByActiveContaining(Boolean active);
	public List<MailSender> findByEmailContaining(String email);
	public List<MailSender> findByPortContaining(String port);

	//		public Optional<MailSender> findByPort(String port);
//		public Optional<MailSender> findByActive(Boolean active);

	@Transactional
	@Modifying
	@Query("update MailSender u set u.wasSend = ?1 where u.idMailSender = ?2")
	void updateWasSend(String wasSend, Long idMailSender);
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


