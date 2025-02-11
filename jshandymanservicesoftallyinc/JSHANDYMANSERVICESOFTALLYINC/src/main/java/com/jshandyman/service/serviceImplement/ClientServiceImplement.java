
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.Client;
import com.jshandyman.service.entitys.MailCliente;
import com.jshandyman.service.entitys.PhoneClient;
import com.jshandyman.service.mapper.ClientMapper;
import com.jshandyman.service.pojo.ClientPojo;
import com.jshandyman.service.pojo.LogingResponse;
import com.jshandyman.service.pojo.MailClientePojo;
import com.jshandyman.service.pojo.PhoneClientPojo;
import com.jshandyman.service.repository.ClientRepository;
import com.jshandyman.service.security.EncryptAES;
import com.jshandyman.service.service.ClientService;
import com.jshandyman.service.service.PhoneClientService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ClientServiceImplement implements ClientService {

    protected static final Log logger = LogFactory.getLog(ClientServiceImplement.class);

    @Autowired
    private ClientRepository clientrepository;

    @Autowired
    private PhoneClientService phoneService;

    @Autowired
    private ClientMapper clientMapper;

    @Value("${keyAdmin2}")
    private String saltAES;

    @Autowired
    private EncryptAES encryptAES;

    @Autowired
    private RestTemplateBuilder restTemplate;

    private HashMap<Long, ClientPojo> mapIdClient = new HashMap<Long, ClientPojo>();


    @Override
    public ClientPojo returnClienteForMap(Long idClient, String company) {
        if (this.checkIsEmptyTheMap(company)) {
            return clienteForMap(idClient, company);
        } else {
            return clienteForMap(idClient, company);
        }
    }

    private ClientPojo clienteForMap(Long idClient, String company) {
        logger.info("started => clienteForMap ");

        if (mapIdClient.containsKey(idClient)) {
            return mapIdClient.get(idClient);
        }else {
            logger.info("cliente not fount in Map started reset Map ");
            this.resetClienteMap(company);
            if (mapIdClient.containsKey(idClient)) {
                return mapIdClient.get(idClient);
            }
        }
        return new ClientPojo();
    }

    @Override
    public boolean checkClienteInMap(Long idClient) {
        logger.info("started => clienteForMap ");

        if (mapIdClient.containsKey(idClient)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void resetClienteMap(String company) {
        logger.info("ResetClienteMap() start...");
        mapIdClient.clear();
        this.fillClienteMap(company);
    }


    @Override
    public void addNewClientToMap(ClientPojo cliente) {
        if (!mapIdClient.containsKey(cliente.getIdClient())) {
            mapIdClient.put(cliente.getIdClient(), cliente);
        }
    }


    @Override
    public void remuveClientToMap(ClientPojo cliente) {
        if (mapIdClient.containsKey(cliente.getIdClient())) {
            mapIdClient.remove(cliente.getIdClient());
        }
    }

    @Override
    public void remuveClientToMapForId(Long id, String objeto) {
        Client cliente =null;
        if(objeto.equals(Constant.ESTIMATE)){
            cliente = clientrepository.findByEstimateId(id).get();
        }

        if(objeto.equals(Constant.WORK)){
            cliente = clientrepository.findByWorkId(id).get();
        }

        if(cliente != null) {
            this.remuveClientToMap(clientMapper.entityToPojo(cliente));
        }
    }

    @Override
    public void replaceClientToMap(ClientPojo cliente) {
        logger.info("Replace the Client into Map... D");
        if (mapIdClient.containsKey(cliente.getIdClient())) {
            mapIdClient.replace(cliente.getIdClient(), cliente);
        }
    }

    @Override
    public List<ClientPojo> listActiveClient(String company){
        List<ClientPojo> listClient = null;
        try{
            Boolean hasContent = this.checkIsEmptyTheMap(company);
            if(!this.mapIdClient.isEmpty()){
                listClient = returnClienteListForMap();
            }
        }catch (Exception e){
            e.printStackTrace();
            return listClient;
        }
        return listClient;
    }

    @Override
    public HashMap<String, HashMap<String, ClientPojo>> mapClients(String company){
        HashMap<String, HashMap<String, ClientPojo>> mapFullnameClienteMap = null;
        List<ClientPojo> listClient = null;
       Boolean hasContent = this.checkIsEmptyTheMap(company);
        if(!this.mapIdClient.isEmpty()){
            listClient = returnClienteListForMap();
            if(listClient.size() > 0){
                mapFullnameClienteMap = this.clientsMap(listClient);
            }
        }
        return mapFullnameClienteMap;
    }

    private HashMap<String, HashMap<String, ClientPojo> > clientsMap(List<ClientPojo> listClient){
        HashMap<String, List<ClientPojo>> mapFullnameClienteObject = new HashMap<String, List<ClientPojo>>();
        HashMap<String, HashMap<String, ClientPojo> > mapFullnameClienteMap = new HashMap<String, HashMap<String, ClientPojo>>();

        listClient.stream().forEach(cliente ->{
            if (!mapFullnameClienteObject.containsKey(cliente.getFullName())) {
                List<ClientPojo> listTemp = new ArrayList<ClientPojo>();
                listTemp.add(cliente);
                mapFullnameClienteObject.put(cliente.getFullName(), listTemp);
            }else {
                mapFullnameClienteObject.get(cliente.getFullName()).add(cliente);
            }
        });
        mapFullnameClienteObject.forEach((k,v) ->{
            HashMap<String, ClientPojo> mapAdressClient = this.mapAdressClient(v);
            mapFullnameClienteMap.put(k ,mapAdressClient);
        });
        return mapFullnameClienteMap;
    }


    private HashMap<String, ClientPojo> mapAdressClient(  List<ClientPojo> listClient ){
        HashMap<String, ClientPojo> mapAdressClientObject = new HashMap<String, ClientPojo>();
                listClient.stream().forEach(cliente ->{
                    if (!mapAdressClientObject.containsKey(cliente.getFullAdress())) {
                        mapAdressClientObject.put(cliente.getFullAdress(), cliente);
                    }
                });
                return mapAdressClientObject;
    }


    @Override
    public Boolean checkIsEmptyTheMap(String company) {
        if (mapIdClient.isEmpty()) {
            fillClienteMap(company);
            return false;
        }
        return true;
    }

    @Override
    public void fillClienteMap(String company) {
        logger.info("start => FillClienteMap");
//        List<ClientPojo> clientes = this.getAllClient();
        List<ClientPojo> clientes = this.getActiveClient(company);
        if(clientes.size() > 0){
            for (ClientPojo cliente : clientes) {
                if (!mapIdClient.containsKey(cliente.getIdClient())) {
                    mapIdClient.put(cliente.getIdClient(), cliente);
                }
            }
        }
    }

    @Override
    public void updateActive(Long idClient){
        clientrepository.updateActive(false,idClient);
    }

    @Override
    public void updateActiveForWorkId(Long workId){
        clientrepository.updateActiveForWorkId(false,workId);
    }

    @Override
    public void updateActiveForEstimateId(Long estimateId){
        clientrepository.updateActiveForEstimateId(false,estimateId);
    }

    @Override
    public List<ClientPojo> returnClienteListForMap() {
        List<ClientPojo> list = new ArrayList<ClientPojo>();
        Set<ClientPojo> setList = new HashSet<ClientPojo>();
        mapIdClient.forEach((key, client) -> setList.add(client));
        setList.stream().forEach(clientPojo -> list.add(clientPojo));
        return list;
    }


    @Override
    public Client encript(Client cliente) {
        logger.info("... E");
        Client cli = new Client();

        if (cliente.getIdClient() != null) {
            cli.setIdClient(cliente.getIdClient());
        }

        if (cliente.getCodeWork() != null) {
            cli.setCodeWork(cliente.getCodeWork());
        }

        if (cliente.getCodeClient() != null) {
            cli.setCodeClient(encryptAES.encript(cliente.getCodeClient(), saltAES));
        }
        if (cliente.getName() != null) {
            cli.setName(encryptAES.encript(cliente.getName(), saltAES));
        }
        if (cliente.getAddress() != null) {
            cli.setAddress(encryptAES.encript(cliente.getAddress(), saltAES));
        }
        if (cliente.getLastName() != null) {
            cli.setLastName(encryptAES.encript(cliente.getLastName(), saltAES));
        }
        if (cliente.getFullAdress() != null) {
            cli.setFullAdress(encryptAES.encript(cliente.getFullAdress(), saltAES));
        }
        if (cliente.getState() != null) {
            cli.setState(encryptAES.encript(cliente.getState(), saltAES));
        }
        if (cliente.getZipCode() != null) {
            cli.setZipCode(encryptAES.encript(cliente.getZipCode(), saltAES));
        }
        if (cliente.getCity() != null) {
            cli.setCity(encryptAES.encript(cliente.getCity(), saltAES));
        }
        if(cliente.getFullName() != null){
            cli.setFullName(  encryptAES.encript(cliente.getFullName(), saltAES));
        }
        if(cliente.getActive() !=null){
            cli.setActive(cliente.getActive());
        }

        if(cliente.getEstimateId() != null){
            cli.setEstimateId(cliente.getEstimateId());
        }

        if(cliente.getWorkId() != null){
            cli.setWorkId(cliente.getWorkId());
        }

        if (cliente.getPhoneNumbers().size() > 0) {
            for (PhoneClient phone : cliente.getPhoneNumbers()) {
                PhoneClient ph = new PhoneClient();

                ph.setCodeClient(encryptAES.encript(phone.getCodeClient(), saltAES));
                ph.setNumber(encryptAES.encript(phone.getNumber(), saltAES));
                if (phone.getIdClient() != null) {
                    ph.setIdClient(phone.getIdClient());
                }
                if (phone.getIdPhoneClient() != null) {
                    ph.setIdPhoneClient(phone.getIdPhoneClient());
                }
                if (cliente.getIdClient() != null) {
                    phone.setIdClient(cliente.getIdClient());
                }
                cli.getPhoneNumbers().add(ph);
            }
        }

        if (cliente.getEmails().size() > 0) {
            for (MailCliente mail : cliente.getEmails()) {
                MailCliente email = new MailCliente();
                email.setCodeClient(encryptAES.encript(mail.getCodeClient(), saltAES));
                email.setEmail(encryptAES.encript(mail.getEmail(), saltAES));
                if (mail.getIdClient() != null) {
                    email.setIdClient(mail.getIdClient());
                }
                if (mail.getIdMailCliente() != null) {
                    email.setIdMailCliente(mail.getIdMailCliente());
                }
                if (cliente.getIdClient() != null) {
                    email.setIdClient(cliente.getIdClient());
                }
                cli.getEmails().add(email);
            }
        }
        return cli;
    }


    @Override
    public ClientPojo decrypt(Client cliente) {
        logger.info("... D");
        ClientPojo cli = new ClientPojo();

        if (cliente.getIdClient() != null) {
            cli.setIdClient(cliente.getIdClient());
        }

        if (cliente.getCodeWork() != null) {
            cli.setCodeWork(cliente.getCodeWork());
        }

        if (cliente.getCodeClient() != null) {
            cli.setCodeClient(encryptAES.decrypt(cliente.getCodeClient(), saltAES));
        }
        if (cliente.getName() != null) {
            cli.setName(encryptAES.decrypt(cliente.getName(), saltAES));
        }
        if (cliente.getAddress() != null) {
            cli.setAddress(encryptAES.decrypt(cliente.getAddress(), saltAES));
        }
        if (cliente.getLastName() != null) {
            cli.setLastName(encryptAES.decrypt(cliente.getLastName(), saltAES));
        }
        if (cliente.getFullAdress() != null) {
            cli.setFullAdress(encryptAES.decrypt(cliente.getFullAdress(), saltAES));
        }
        if (cliente.getState() != null) {
            cli.setState(encryptAES.decrypt(cliente.getState(), saltAES));
        }
        if (cliente.getZipCode() != null) {
            cli.setZipCode(encryptAES.decrypt(cliente.getZipCode(), saltAES));
        }
        if (cliente.getCity() != null) {
            cli.setCity(encryptAES.decrypt(cliente.getCity(), saltAES));
        }

        if(cliente.getFullName() != null){
            cli.setFullName(  encryptAES.decrypt(cliente.getFullName(), saltAES));
        }
        if(cliente.getActive() != null){
            cli.setActive(cliente.getActive());
        }

        if(cliente.getEstimateId() != null){
            cli.setEstimateId(cliente.getEstimateId());
        }

        if(cliente.getWorkId() != null){
            cli.setWorkId(cliente.getWorkId());
        }

        if (cliente.getPhoneNumbers() != null && cliente.getPhoneNumbers().size() > 0) {
            for (PhoneClient phone : cliente.getPhoneNumbers()) {

                PhoneClientPojo ph = new PhoneClientPojo(
                        phone.getIdPhoneClient(),
                        encryptAES.decrypt(phone.getNumber(), saltAES),
                        encryptAES.decrypt(phone.getCodeClient(), saltAES),
                        cliente.getIdClient());
                System.out.println(ph);
//                PhoneClientPojo ph = new PhoneClientPojo();
//                ph.setCodeClient(encryptAES.decrypt(phone.getCodeClient(), saltAES));
//                ph.setNumber(encryptAES.decrypt(phone.getNumber(), saltAES));
//                if (phone.getIdPhoneClient() != null) {
//                    ph.setIdPhoneClient(phone.getIdPhoneClient());
//                }
//                if (cliente.getIdClient() != null) {
//                    phone.setIdClient(cliente.getIdClient());
//                }

                cli.getPhoneNumbers().add(ph);
            }
        }

        if (cliente.getEmails().size() > 0) {
            for (MailCliente mail : cliente.getEmails()) {
                MailClientePojo email = new MailClientePojo();
                email.setCodeClient(encryptAES.decrypt(mail.getCodeClient(), saltAES));
                email.setEmail(encryptAES.decrypt(mail.getEmail(), saltAES));

                if (mail.getIdMailCliente() != null) {
                    email.setIdMailCliente(mail.getIdMailCliente());
                }
                if (cliente.getIdClient() != null) {
                    email.setIdClient(cliente.getIdClient());
                }
                cli.getEmails().add(email);
            }
        }
        return cli;
    }


    @Override
    public ClientPojo findByCodeClient(String codeClient) {
        logger.info("Starting findByCodeClient");
        Client clientEntity = new Client();
        Optional<Client> fileOptional1 = clientrepository.findByCodeClient(codeClient);
        if (fileOptional1.isPresent()) {
            try {
                clientEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return this.decrypt(clientEntity);
    }


    @Override
    public ClientPojo findByName(String name) {

        logger.info("Starting findByName");
        Client clientEntity = new Client();
        Optional<Client> fileOptional1 = clientrepository.findByName(name);
        if (fileOptional1.isPresent()) {
            try {
                clientEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return this.decrypt(clientEntity);
    }

    @Override
    public ClientPojo findByLastName(String lastName) {
        logger.info("Starting findByLastName");
        Client clientEntity = new Client();
        Optional<Client> fileOptional1 = clientrepository.findByLastName(lastName);
        if (fileOptional1.isPresent()) {
            try {
                clientEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return this.decrypt(clientEntity);
    }

    @Override
    public ClientPojo findByAddress(String address) {
        logger.info("findByAddress");
        Client clientEntity = new Client();
        Optional<Client> fileOptional1 = clientrepository.findByAddress(address);
        if (fileOptional1.isPresent()) {
            try {
                clientEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return this.decrypt(clientEntity);
    }


    @Override
    public List<ClientPojo> getAllClient() {
        logger.info("Get getAllClients...");
        List<Client> clients = new ArrayList<Client>();
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        clientrepository.findAll().forEach(client -> clients.add(client));
        clients.stream().forEach(client -> listaClient.add(this.decrypt(client)));
        //clientrepository.findAll().forEach(client -> listaClient.add(this.decrypt(client)));
        return listaClient;
    }

    public List<ClientPojo> getActiveClient(String company) {
        logger.info("Get ActiveClient...");
        List<Client> clients = new ArrayList<Client>();
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        clientrepository.findByActiveAndCompany(true,company).forEach(client -> clients.add(client));
        clients.stream().forEach(client -> listaClient.add(this.decrypt(client)));
        return listaClient;
    }

    @Override
    public boolean saveClient(Client client) {
        logger.info("Save Client" + client.toString());
        try {
            if (client != null) {
                clientrepository.save(this.encript(client));
            }
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteClient(Long id) {
        logger.info("Delete Client");
        boolean clave = false;
        try {
            clientrepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public ClientPojo findById(Long id) {
        return this.decrypt(clientrepository.findById(id).get());
    }


    @Override
    public List<ClientPojo> findByCodeClientContaining(String codeclient) {
        logger.info("Get findByCodeClientContaining");
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        clientrepository.findByCodeClientContaining(codeclient)
                .stream().forEach(client -> listaClient.add(this.decrypt(client)));
        return listaClient;
    }


    @Override
    public List<ClientPojo> findByNameContaining(String name) {
        logger.info("Get findByNameContaining");
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        clientrepository.findByNameContaining(name)
                .stream().forEach(client -> listaClient.add(this.decrypt(client)));
        return listaClient;
    }


    @Override
    public List<ClientPojo> findByLastNameContaining(String lastname) {
        logger.info("Get findByLastNameContaining");
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        clientrepository.findByLastNameContaining(lastname)
                .stream().forEach(client -> listaClient.add(this.decrypt(client)));
        return listaClient;
    }

    @Override
    public List<ClientPojo> findByAddressContaining(String address) {
        logger.info("Get findByAddressContaining");
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        clientrepository.findByAddressContaining(address)
                .stream().forEach(client -> listaClient.add(this.decrypt(client)));
        return listaClient;
    }


    @Override
    public List<ClientPojo> findByMailClienteContaining(MailCliente emails) {
        logger.info("metodo: metodContainingRelacion NEW ");
        logger.info("Get allProyect");
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        for (ClientPojo client : this.getAllClient()) {
            for (MailClientePojo emailsx : client.getEmails()) {
                if (emailsx.getEmail().equals(emails.getEmail())
                        || emailsx.getIdClient().equals(emails.getIdClient())) {
                    listaClient.add(client);
                }
            }
        }
        return listaClient;
    }


    public List<ClientPojo> findByPhoneClientContaining(PhoneClient phoneNumbers) {
        logger.info("metodo: metodContainingRelacion NEW ");
        logger.info("findByPhoneClientContaining");
        List<ClientPojo> listaClient = new ArrayList<ClientPojo>();
        List<ClientPojo> lista = this.getAllClient();
        for (ClientPojo client : lista) {
            for (PhoneClientPojo phoneNumbersx : client.getPhoneNumbers()) {
                if (phoneNumbersx.getNumber().equals(phoneNumbers.getNumber())
                        || phoneNumbersx.getIdClient().equals(phoneNumbers.getIdClient())
                        || phoneNumbersx.getCodeClient().equals(phoneNumbers.getCodeClient())) {
                    listaClient.add(client);
                }
            }
        }
        return listaClient;
    }


    public LogingResponse startFillClienteMap() {
        ResponseEntity responseEntity = restTemplate.build().getForEntity("http://localhost:8987/jshandyman/new/user/start", String.class, 2);

        //        responseEntity.getHeaders().entrySet().forEach((k) -> {
////            System.out.println("Key is:"+ k.getKey());
////            System.out.println("Values are:"+k.getValue().stream().collect(Collectors.joining()));
//        });
        LogingResponse login = new LogingResponse();
        return login;
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