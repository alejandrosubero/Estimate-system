
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


package com.template.parameter.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.template.parameter.entitys.User;


public interface UserService{
 
		public User  findByName(String name);

		public List<User>  findByNameContaining(String name);

		public User findById(Long id);
		public boolean saveUser(User user);
		public List<User> getAllUser();
		public boolean updateUser(User user);
 		public boolean saveOrUpdateUser(User user);

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


