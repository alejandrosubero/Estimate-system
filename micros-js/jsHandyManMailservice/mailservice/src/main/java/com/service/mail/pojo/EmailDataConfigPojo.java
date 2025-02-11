
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

package com.service.mail.pojo;


import java.util.Objects;



public class EmailDataConfigPojo  {
private String host;
private Long idEmailConfig;
private String port;
private String mailUsername;
private String mailPassword;

public EmailDataConfigPojo() {
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

public boolean equalsEmailDataConfigPojo(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmailDataConfigPojo emaildataconfigpojo = (EmailDataConfigPojo) o;
		return Objects.equals(host, emaildataconfigpojo.host) ||
		Objects.equals(idEmailConfig, emaildataconfigpojo.idEmailConfig) ||
		Objects.equals(port, emaildataconfigpojo.port) ||
		Objects.equals(mailUsername, emaildataconfigpojo.mailUsername) ||
		Objects.equals(mailPassword, emaildataconfigpojo.mailPassword);

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

