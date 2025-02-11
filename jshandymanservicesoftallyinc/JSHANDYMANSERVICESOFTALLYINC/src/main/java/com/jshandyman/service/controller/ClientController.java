
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.ClientMapper;
import com.jshandyman.service.mapper.MailClienteMapper;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.mapper.PhoneClientMapper;
import com.jshandyman.service.pojo.ClientPojo;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.MailClientePojo;
import com.jshandyman.service.pojo.PhoneClientPojo;
import com.jshandyman.service.service.ClientService;
import com.jshandyman.service.validation.ClientValidation;
import com.jshandyman.service.validation.MailClienteValidation;
import com.jshandyman.service.validation.PhoneClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientValidation clientValidationService;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

    @Autowired
    PhoneClientValidation phoneclientValidationService;

    @Autowired
    PhoneClientMapper phoneclientMapper;

    @Autowired
    MailClienteValidation mailclienteValidationService;

    @Autowired
    MailClienteMapper mailclienteMapper;


    @GetMapping("/map/all")
    private ResponseEntity<HashMap<String, HashMap<String, ClientPojo>>> getMapAllClient( @RequestHeader("Company")  String company) {

        return new ResponseEntity<HashMap<String, HashMap<String, ClientPojo>>>(clientService.mapClients(company), HttpStatus.OK);
    }

    @GetMapping("/active/all")
    private ResponseEntity<EntityRespone> listActiveClient(@RequestHeader("Company")  String company) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(clientService.listActiveClient(company));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/reset/Clients")
    private ResponseEntity<EntityRespone> resetClienteMapAllClient(@RequestHeader("Company")  String company) {
        clientService.resetClienteMap(company);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(clientService.listActiveClient(company));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @PostMapping("/save")
    private Boolean saveClient(@RequestBody ClientPojo client, @RequestHeader("Company")  String company) {

        return clientService.saveClient(clientMapper.pojoToEntity(clientValidationService.valida(client)));
    }

    @GetMapping("/codeclient/{codeclient}")
    private ResponseEntity<EntityRespone> findByCodeClient(@PathVariable("codeclient") String codeclient) {
        String busca = (String) clientValidationService.validation(codeclient);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(clientService.findByCodeClient(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/name/{name}")
    private ResponseEntity<EntityRespone> findByName(@PathVariable("name") String name) {
        String busca = (String) clientValidationService.validation(name);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(clientService.findByName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lastname/{lastname}")
    private ResponseEntity<EntityRespone> findByLastName(@PathVariable("lastname") String lastname) {
        String busca = (String) clientValidationService.validation(lastname);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(clientService.findByLastName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/address/{address}")
    private ResponseEntity<EntityRespone> findByAddress(@PathVariable("address") String address) {
        String busca = (String) clientValidationService.validation(address);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(clientService.findByAddress(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/codeclient/contain/{codeclient}")
    private ResponseEntity<EntityRespone> findByCodeClientContain(@PathVariable("codeclient") String codeclient) {
        String busca = (String) clientValidationService.validation(codeclient);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(clientService.findByCodeClientContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/name/contain/{name}")
    private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String name) {
        String busca = (String) clientValidationService.validation(name);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(clientService.findByNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/lastname/contain/{lastname}")
    private ResponseEntity<EntityRespone> findByLastNameContain(@PathVariable("lastname") String lastname) {
        String busca = (String) clientValidationService.validation(lastname);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(clientService.findByLastNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/address/contain/{address}")
    private ResponseEntity<EntityRespone> findByAddressContain(@PathVariable("address") String address) {
        String busca = (String) clientValidationService.validation(address);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(clientService.findByAddressContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/client/id/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(clientService.findById(clientValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/All")
    private ResponseEntity<EntityRespone> getAllClient() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(clientService.getAllClient());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }



//    @DeleteMapping("/delete/id/{id}")
//    private boolean deleteClient(@PathVariable("id") String id) {
//        return clientService.deleteClient(clientValidationService.valida_id(id));
//    }


    @PostMapping("/phoneNumbers/contain")
    private ResponseEntity<EntityRespone> findByPhoneClient(@RequestBody PhoneClientPojo phoneclient) {
        EntityRespone entityRespone =
                mapperEntityRespone.setEntityT(
                        clientService.findByPhoneClientContaining(
                                phoneclientMapper.pojoToEntity(
                                        phoneclientValidationService.valida(phoneclient))));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @PostMapping("/emails/contain")
    private ResponseEntity<EntityRespone> findByMailCliente(@RequestBody MailClientePojo mailcliente) {
        EntityRespone entityRespone =
                mapperEntityRespone.setEntityT(
                        clientService.findByMailClienteContaining(
                                mailclienteMapper.pojoToEntity(
                                        mailclienteValidationService.valida(mailcliente)
                                )));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
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


