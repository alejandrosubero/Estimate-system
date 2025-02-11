
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/

package com.jshandyman.service.pojo;

import java.util.Date;
import java.util.Objects;


public class UserPojo  {


		private Long idUser;

		private String userCode;

		private String userFirsName;

		private String userLastName;

		private String fullName;

		private String userName;

		private String mail;

		private String password;

		private Boolean accountNonExpired;

		private Boolean accountNonLocked;

		private Boolean credentialsNonExpired;

		private Boolean enabled;

		private String rol;

		private String imagen;
		private String pregunta;
		private String respuesta;
		private Date dateChange;

		private String company;

	public UserPojo() {
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getDateChange() {
		return dateChange;
	}

	public void setDateChange(Date dateChange) {
		this.dateChange = dateChange;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserFirsName() {
		return userFirsName;
	}

	public void setUserFirsName(String userFirsName) {
		this.userFirsName = userFirsName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserPojo userPojo = (UserPojo) o;
		return Objects.equals(idUser, userPojo.idUser) && Objects.equals(userCode, userPojo.userCode) && Objects.equals(userFirsName, userPojo.userFirsName) && Objects.equals(userLastName, userPojo.userLastName) && Objects.equals(fullName, userPojo.fullName) && Objects.equals(userName, userPojo.userName) && Objects.equals(mail, userPojo.mail) && Objects.equals(password, userPojo.password) && Objects.equals(accountNonExpired, userPojo.accountNonExpired) && Objects.equals(accountNonLocked, userPojo.accountNonLocked) && Objects.equals(credentialsNonExpired, userPojo.credentialsNonExpired) && Objects.equals(enabled, userPojo.enabled) && Objects.equals(rol, userPojo.rol) && Objects.equals(imagen, userPojo.imagen) && Objects.equals(pregunta, userPojo.pregunta) && Objects.equals(respuesta, userPojo.respuesta) && Objects.equals(dateChange, userPojo.dateChange) && Objects.equals(company, userPojo.company);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser, userCode, userFirsName, userLastName, fullName, userName, mail, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, rol, imagen, pregunta, respuesta, dateChange, company);
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

