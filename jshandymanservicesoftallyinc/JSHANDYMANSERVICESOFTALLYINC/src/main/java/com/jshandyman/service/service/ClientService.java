
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

import java.util.*;

import com.jshandyman.service.entitys.Client;
import com.jshandyman.service.entitys.PhoneClient;
import com.jshandyman.service.entitys.MailCliente;
import com.jshandyman.service.pojo.ClientPojo;
import com.jshandyman.service.pojo.LogingResponse;


public interface ClientService {

    public void remuveClientToMapForId(Long id, String objeto);
    public List<ClientPojo> listActiveClient(String company);
    public HashMap<String, HashMap<String, ClientPojo>> mapClients(String company);
    public void updateActiveForEstimateId(Long estimateId);
    public void updateActiveForWorkId(Long workId);

    public void updateActive(Long idClient);
    public void replaceClientToMap(ClientPojo cliente);
    public void remuveClientToMap(ClientPojo cliente);

    public boolean checkClienteInMap(Long idClient);
    public LogingResponse startFillClienteMap();
    public Boolean checkIsEmptyTheMap(String company);
    public ClientPojo returnClienteForMap(Long idClient, String company);

    public void fillClienteMap(String company);
    public void addNewClientToMap(ClientPojo cliente);
    public void resetClienteMap(String company);

    public List<ClientPojo> returnClienteListForMap();


    public ClientPojo decrypt(Client cliente);
    public Client encript(Client cliente);
    public ClientPojo findByCodeClient(String codeClient);

    public ClientPojo findByName(String name);

    public ClientPojo findByLastName(String lastName);

    public ClientPojo findByAddress(String address);

    public List<ClientPojo> findByCodeClientContaining(String codeClient);

    public List<ClientPojo> findByNameContaining(String name);

    public List<ClientPojo> findByLastNameContaining(String lastName);

    public List<ClientPojo> findByAddressContaining(String address);

    public ClientPojo findById(Long id);

    public boolean saveClient(Client client);

    public List<ClientPojo> getAllClient();

    public boolean deleteClient(Long id);

    public List<ClientPojo> findByMailClienteContaining(MailCliente emails);

    public List<ClientPojo> findByPhoneClientContaining(PhoneClient phoneNumbers);
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


