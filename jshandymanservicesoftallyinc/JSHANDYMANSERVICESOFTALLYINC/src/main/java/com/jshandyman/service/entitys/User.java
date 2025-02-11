
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/

package com.jshandyman.service.entitys;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "user")
@Audited
public class User  {


		@Id
		@GeneratedValue(generator = "sequence_mat_id_generator")
		@SequenceGenerator(name="sequence_mat_id_generator", initialValue= 25, allocationSize=1000)
		@Column(name = "idUser", updatable = true, nullable = false, length = 25)
		private Long idUser;


		@Column(name = "userCode", updatable = true, nullable = true, length = 200)
		private String userCode;


		@Column(name = "userFirsName", updatable = true, nullable = true, length = 200)
		private String userFirsName;


		@Column(name = "userLastName", updatable = true, nullable = true, length = 200)
		private String userLastName;


		@Column(name = "fullName", updatable = true, nullable = true, length = 200)
		private String fullName;


		@Column(name = "userName", updatable = true, nullable = true, length = 200)
		private String userName;


		@Column(name = "mail", updatable = true, nullable = true, length = 200)
		private String mail;


		@Column(name = "password", updatable = true, nullable = true, length = 200)
		private String password;


		@Column(name = "accountNonExpired", updatable = true, nullable = true, length = 200)
		private Boolean accountNonExpired;


		@Column(name = "accountNonLocked", updatable = true, nullable = true, length = 200)
		private Boolean accountNonLocked;


		@Column(name = "credentialsNonExpired", updatable = true, nullable = true, length = 200)
		private Boolean credentialsNonExpired;


		@Column(name = "enabled", updatable = true, nullable = true, length = 200)
		private Boolean enabled;


		@Column(name = "rol", updatable = true, nullable = true, length = 200)
		private String rol;

		@Lob
		@Column(name = "imagen", updatable = true, nullable = true)
		private String imagen;

		@Column(name = "pregunta", updatable = true, nullable = true, length = 200)
		private String pregunta;

		@Column(name = "respuesta", updatable = true, nullable = true, length = 200)
		private String respuesta;

		@Column(name = "dateChange", updatable = true, nullable = true)
		private Date dateChange;

		@Column(name = "company", updatable = true, nullable = true, length = 200)
		private String company;


	public User() {
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(idUser, user.idUser) && Objects.equals(userCode, user.userCode) && Objects.equals(userFirsName, user.userFirsName) && Objects.equals(userLastName, user.userLastName) && Objects.equals(fullName, user.fullName) && Objects.equals(userName, user.userName) && Objects.equals(mail, user.mail) && Objects.equals(password, user.password) && Objects.equals(accountNonExpired, user.accountNonExpired) && Objects.equals(accountNonLocked, user.accountNonLocked) && Objects.equals(credentialsNonExpired, user.credentialsNonExpired) && Objects.equals(enabled, user.enabled) && Objects.equals(rol, user.rol) && Objects.equals(imagen, user.imagen) && Objects.equals(pregunta, user.pregunta) && Objects.equals(respuesta, user.respuesta) && Objects.equals(dateChange, user.dateChange) && Objects.equals(company, user.company);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser, userCode, userFirsName, userLastName, fullName, userName, mail, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, rol, imagen, pregunta, respuesta, dateChange, company);
	}

	@Override
	public String toString() {
		return "User{" +
				"idUser=" + idUser +
				", userFirsName='" + userFirsName + '\'' +
				", userLastName='" + userLastName + '\'' +
				", fullName='" + fullName + '\'' +
				", userName='" + userName + '\'' +
				", mail='" + mail + '\'' +
				", rol='" + rol + '\'' +
				", dateChange=" + dateChange +
				", company='" + company + '\'' +
				'}';
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

