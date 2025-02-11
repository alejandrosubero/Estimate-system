
/*
Create on Mon Aug 22 18:51:50 EDT 2022
*Copyright (C) 122.
@author alejandro subero
@author JS
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: service of template, parameter </p>
*/

package com.template.parameter.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;



public class UserPojo implements Serializable {

private static final long serialVersionUID = 4163714511115013179L;

		private Long id;

		private String name;

		public Long getId() { 
			return id;
		}
		public void setId(Long  id) {
			this.id = id;
		}
		public String getName() { 
			return name;
		}
		public void setName(String  name) {
			this.name = name;
		}
			public boolean equalsUserPojo(Object o) {
				if (this == o) return true;
				if (o == null || getClass() != o.getClass()) return false;
					UserPojo userpojo = (UserPojo) o;
						return 			Objects.equals(id, userpojo.id) ||
			Objects.equals(name, userpojo.name);

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

