
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;

import com.jshandyman.service.entitys.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.jshandyman.service.entitys.User;
import org.springframework.stereotype.Repository;

@Repository("user")
public interface UserRepository extends CrudRepository< User, Long> {

		public List<User> findByCompany(String company);
		public Optional<User> findByUserCode(String userCode);
		public List<User> findByUserCodeContaining(String userCode);
		public Optional<User> findByUserFirsName(String userFirsName);
		public List<User> findByUserFirsNameContaining(String userFirsName);
		public Optional<User> findByUserLastName(String userLastName);
		public List<User> findByUserLastNameContaining(String userLastName);
		public Optional<User> findByFullName(String fullName);
		public List<User> findByFullNameContaining(String fullName);
		public Optional<User> findByUserName(String userName);
		public List<User> findByUserNameContaining(String userName);
		public Optional<User> findByMail(String mail);
		public List<User> findByMailContaining(String mail);
		public Optional<User> findByPassword(String password);
		public List<User> findByPasswordContaining(String password);
		public Optional<User> findByAccountNonExpired(Boolean accountNonExpired);
		public List<User> findByAccountNonExpiredContaining(Boolean accountNonExpired);
		public Optional<User> findByAccountNonLocked(Boolean accountNonLocked);
		public List<User> findByAccountNonLockedContaining(Boolean accountNonLocked);
		public Optional<User> findByCredentialsNonExpired(Boolean credentialsNonExpired);
		public List<User> findByCredentialsNonExpiredContaining(Boolean credentialsNonExpired);
		public Optional<User> findByEnabled(Boolean enabled);
		public List<User> findByEnabledContaining(Boolean enabled);
		public Optional<User> findByRol(String rol);
		public List<User> findByRolContaining(String rol);

//	@Query(value = "SELECT p FROM Client p WHERE CONCAT( p.codeClient, ' ', p.name, ' ', p.address) LIKE %?1%")
//	public List<Client> finBySearch(String keyword);

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


