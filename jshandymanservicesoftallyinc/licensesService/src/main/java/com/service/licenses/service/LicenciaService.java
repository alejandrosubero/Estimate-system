
/*
Create on Sun Apr 24 19:56:28 EDT 2022
*Copyright (C) 122.
@author alejandro subero
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: service for licenses </p>
*/


package com.service.licenses.service;

import com.service.licenses.entitys.Licencia;

import java.util.List;


public interface LicenciaService {

    public String findByLic(String lisence);
    public boolean deleteAll(Long id);
    public boolean delete(Long id);
    public Licencia findByCompany(String company);

    public Licencia findByLisenceCompanyId(String lisenceCompanyId);

    public Licencia findByLisence(String lisence);

    public Licencia findById(Long id);

    public boolean saveLicencia(Licencia licencia);

    public List<Licencia> getAllLicencia();

    public boolean updateLicencia(Licencia licencia);

    public boolean saveOrUpdateLicencia(Licencia licencia);

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


