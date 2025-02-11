
/*
Create on Sun Nov 07 14:17:04 ART 2021
*Copyright (C) 121.
@author Alejandro Subero
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: This project tracking software </p>
*/

package com.jshandyman.service.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;



public class RolPojo  {

		private Long idRol;

		private String rol;

		private String description;

		public Long getIdrol() { 
			return idRol;
		}
		public void setIdrol(Long  idRol) {
			this.idRol = idRol;
		}
		public String getRol() { 
			return rol;
		}
		public void setRol(String  rol) {
			this.rol = rol;
		}
		public String getDescription() { 
			return description;
		}
		public void setDescription(String  description) {
			this.description = description;
		}
			public boolean equalsRolPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					RolPojo rolpojo = (RolPojo) o;
						return 			Objects.equals(idRol, rolpojo.idRol) ||
			Objects.equals(rol, rolpojo.rol) ||
			Objects.equals(description, rolpojo.description);

}}
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

