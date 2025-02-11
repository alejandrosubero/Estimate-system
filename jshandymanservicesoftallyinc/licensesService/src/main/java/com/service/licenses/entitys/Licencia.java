
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

package com.service.licenses.entitys;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "licencia")
public class Licencia implements Serializable {

private static final long serialVersionUID = -4546023805585221548L;

		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "id", updatable = true, nullable = false, length = 25)
		private Long id;

		@Column(name = "company", updatable = true, nullable = true, length = 200)
		private String company;

//TODO: change "lisenceCompanyId" for "rolLisenceCompany" for hambleht the user or admin.
		@Column(name = "lisenceCompanyId", updatable = true, nullable = true, length = 200)
		private String lisenceCompanyId;


		@Column(name = "lisence", updatable = true, nullable = true, length = 200)
		private String lisence;


		@Column(name = "active", updatable = true, nullable = true, length = 200)
		private Boolean active;


		@Column(name = "description", updatable = true, nullable = true, length = 200)
		private String description;

	public Licencia() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLisenceCompanyId() {
		return lisenceCompanyId;
	}

	public void setLisenceCompanyId(String lisenceCompanyId) {
		this.lisenceCompanyId = lisenceCompanyId;
	}

	public String getLisence() {
		return lisence;
	}

	public void setLisence(String lisence) {
		this.lisence = lisence;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

