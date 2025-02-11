
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


package com.service.licenses.repository;

import java.util.List;
import java.util.Date;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.service.licenses.entitys.Licencia;

public interface LicenciaRepository extends CrudRepository<Licencia, Long> {

    public Optional<Licencia> findByCompany(String company);

	public Optional<Licencia> findByLisence(String lisence);

	public Optional<Licencia> findByLisenceCompanyId(String lisenceCompanyId);

    public List<Licencia> findByCompanyContaining(String company);

    public List<Licencia> findByLisenceCompanyIdContaining(String lisenceCompanyId);

    public List<Licencia> findByLisenceContaining(String lisence);

    public Optional<Licencia> findByActive(Boolean active);

    public List<Licencia> findByActiveContaining(Boolean active);

    public Optional<Licencia> findByDescription(String description);

    public List<Licencia> findByDescriptionContaining(String description);

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


