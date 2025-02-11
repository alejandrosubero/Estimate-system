
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

import com.jshandyman.service.mapper.EmailDataConfigMapper;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.pojo.EmailDataConfigPojo;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.service.EmailDataConfigService;
import com.jshandyman.service.validation.EmailDataConfigValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("config/emaildataconfig")
public class EmailDataConfigController {

    @Autowired
    private EmailDataConfigService emaildataconfigService;

    @Autowired
    private EmailDataConfigValidation emaildataconfigValidationService;

    @Autowired
   private EmailDataConfigMapper emaildataconfigMapper;

    @Autowired
   private MapperEntityRespone mapperEntityRespone;



        @GetMapping("/host/{host}")
        private  ResponseEntity<EntityRespone> findByHost(@PathVariable("host") String  host) {
        String busca = (String) emaildataconfigValidationService.validation(host);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(emaildataconfigService.findByHost(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/port/{port}")
        private  ResponseEntity<EntityRespone> findByPort(@PathVariable("port") String  port) {
        String busca = (String) emaildataconfigValidationService.validation(port);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(emaildataconfigService.findByPort(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/mail/username/{mailusername}")
        private  ResponseEntity<EntityRespone> findByMailUsername(@PathVariable("mailusername") String  mailusername) {
        String busca = (String) emaildataconfigValidationService.validation(mailusername);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(emaildataconfigService.findByMailUsername(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/host/contain/{host}")
        private ResponseEntity<EntityRespone> findByHostContain(@PathVariable("host") String  host, @RequestHeader("Company")  String company) {
              String busca = (String) emaildataconfigValidationService.validation(host);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(
                      emaildataconfigService.findByHostContaining(busca, company)
              );
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/port/contain/{port}")
        private ResponseEntity<EntityRespone> findByPortContain(@PathVariable("port") String  port) {
              String busca = (String) emaildataconfigValidationService.validation(port);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(emaildataconfigService.findByPortContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/mail/username/contain/{mailusername}")
        private ResponseEntity<EntityRespone> findByMailUsernameContain(@PathVariable("mailusername") String  mailusername) {
              String busca = (String) emaildataconfigValidationService.validation(mailusername);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(emaildataconfigService.findByMailUsernameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/id/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(emaildataconfigService.findById(emaildataconfigValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/all")
        private  ResponseEntity<EntityRespone> getAllEmailDataConfig(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(emaildataconfigService.getAllEmailDataConfig());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }


        @PostMapping("/save")
        private Boolean  saveEmailDataConfig(@RequestBody EmailDataConfigPojo  emaildataconfig, @RequestHeader("Company")  String company){
            emaildataconfig.setCompany(company);
            return emaildataconfigService.saveEmailDataConfig(
                    emaildataconfigMapper.pojoToEntity(
                                    emaildataconfigValidationService.valida(emaildataconfig)
                    ));
        }


        @DeleteMapping("/delete/id/{id}")
            private boolean deleteEmailDataConfig(@PathVariable("id") String id) {
            return emaildataconfigService.deleteEmailDataConfig(emaildataconfigValidationService.valida_id(id)); }

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


