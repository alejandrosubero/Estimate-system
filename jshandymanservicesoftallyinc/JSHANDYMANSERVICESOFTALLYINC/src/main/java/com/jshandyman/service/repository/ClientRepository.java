
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

import com.jshandyman.service.entitys.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository< Client, Long> {
 
		public Optional<Client> findByCodeClient(String codeClient);
		public List<Client> findByCodeClientContaining(String codeClient);
		public Optional<Client> findByName(String name);
		public List<Client> findByNameContaining(String name);
		public Optional<Client> findByLastName(String lastName);
		public List<Client> findByLastNameContaining(String lastName);
		public Optional<Client> findByAddress(String address);
		public List<Client> findByAddressContaining(String address);
		public  List<Client> findByActiveAndCompany(Boolean active, String company);

		public Optional<Client> findByEstimateId(Long id);
		public Optional<Client> findByWorkId(Long id);

	@Query(value = "SELECT p FROM Client p WHERE CONCAT( p.codeClient, ' ', p.name, ' ', p.address) LIKE %?1%")
	public List<Client> finBySearch(String keyword);

	@Transactional
	@Modifying
	@Query("update Client u set u.active = ?1 where u.idClient = ?2")
	void updateActive(Boolean active, Long idClient);

	@Transactional
	@Modifying
	@Query("update Client u set u.active = ?1 where u.estimateId = ?2")
	void updateActiveForEstimateId(Boolean active, Long estimateId);

	@Transactional
	@Modifying
	@Query("update Client u set u.active = ?1 where u.workId = ?2")
	void updateActiveForWorkId(Boolean active, Long workId);


//	@Modifying
//	@Query("update Client u set u.firstname = ?1 where u.lastname = ?2")
//	int setFixedFirstnameFor(String firstname, String lastname);


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


