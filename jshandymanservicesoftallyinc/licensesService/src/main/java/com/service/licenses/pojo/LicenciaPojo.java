
/*
Create on Sun Apr 24 19:56:27 EDT 2022
*Copyright (C) 122.
@author alejandro subero
@author Companys
@author ANACODE AND IVANCODE
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: service for licenses </p>
*/

package com.service.licenses.pojo;

import java.io.Serializable;
import java.util.Objects;



public class LicenciaPojo implements Serializable {

private static final long serialVersionUID = 1952757810089713287L;

		private Long id;

		private String company;

		private String lisenceCompanyId;

		private String lisence;

		private Boolean active;

		private String description;

	public LicenciaPojo() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LicenciaPojo that = (LicenciaPojo) o;
		return Objects.equals(id, that.id) && Objects.equals(company, that.company) && Objects.equals(lisenceCompanyId, that.lisenceCompanyId) && Objects.equals(lisence, that.lisence) && Objects.equals(active, that.active) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, company, lisenceCompanyId, lisence, active, description);
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

