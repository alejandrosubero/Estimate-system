
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

package com.service.mail.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "emaildataconfig")
public class EmailDataConfig   {


	@Id
	@GeneratedValue(generator = "sequence_mat_id_generator")
	@SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
	@Column(name = "idEmailConfig", updatable = true, nullable = false, length = 25)
	private Long idEmailConfig;

	@Column(name = "host", updatable = true, nullable = true, length = 200)
	private String host;

	@Column(name = "port", updatable = true, nullable = true, length = 200)
	private String port;

	@Column(name = "mailUsername", updatable = true, nullable = true, length = 200)
	private String mailUsername;

	@Column(name = "mailPassword", updatable = true, nullable = true, length = 200)
	private String mailPassword;

	@Column(name = "company", updatable = true, nullable = true, length = 500)
	private String company;


	public EmailDataConfig() {
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getIdemailconfig() {
		return idEmailConfig;
	}

	public void setIdemailconfig(Long idEmailConfig) {
		this.idEmailConfig = idEmailConfig;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMailusername() {
		return mailUsername;
	}

	public void setMailusername(String mailUsername) {
		this.mailUsername = mailUsername;
	}

	public String getMailpassword() {
		return mailPassword;
	}

	public void setMailpassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmailDataConfig that = (EmailDataConfig) o;
		return Objects.equals(idEmailConfig, that.idEmailConfig) && Objects.equals(host, that.host) && Objects.equals(port, that.port) && Objects.equals(mailUsername, that.mailUsername) && Objects.equals(mailPassword, that.mailPassword) && Objects.equals(company, that.company);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEmailConfig, host, port, mailUsername, mailPassword, company);
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

