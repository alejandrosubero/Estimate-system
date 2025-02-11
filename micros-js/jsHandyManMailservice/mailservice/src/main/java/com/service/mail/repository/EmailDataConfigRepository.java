
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
import org.springframework.data.repository.CrudRepository;
import com.service.mail.entitys.EmailDataConfig;

public interface EmailDataConfigRepository extends CrudRepository< EmailDataConfig, Long> {

	public Optional<EmailDataConfig> findByHost(String host);

	public List<EmailDataConfig> findByHostContaining(String host);

	public Optional<EmailDataConfig> findByPort(String port);

	public Optional<EmailDataConfig> findByPortAndCompany(String port, String conpany);

//	public List<EmailDataConfig> findByPortAndCompany(String port, String conpany);

	public List<EmailDataConfig> findByPortContaining(String port);

	public Optional<EmailDataConfig> findByMailUsername(String mailUsername);

	public List<EmailDataConfig> findByMailUsernameContaining(String mailUsername);

	public Optional<EmailDataConfig> findByMailPassword(String mailPassword);

	public List<EmailDataConfig> findByMailPasswordContaining(String mailPassword);


//    @Query(value = "SELECT p FROM Client p WHERE CONCAT( p.codeClient, ' ', p.name, ' ', p.address) LIKE %?1%")
//    public List<Client> finBySearch(String keyword);

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


