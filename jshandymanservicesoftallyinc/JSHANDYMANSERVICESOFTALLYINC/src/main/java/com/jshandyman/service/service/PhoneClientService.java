
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.service;

import java.util.Optional;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import com.jshandyman.service.entitys.PhoneClient;


public interface PhoneClientService {

    public PhoneClient findByNumber(String number);

    public PhoneClient findByCodeClient(String codeClient);

    public List<PhoneClient> findByNumberContaining(String number);

    public List<PhoneClient> findByCodeClientContaining(String codeClient);

    public PhoneClient findById(Long id);

    public boolean savePhoneClient(PhoneClient phoneclient);

    public List<PhoneClient> getAllPhoneClient();

    public boolean deletePhoneClient(Long id);

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


