
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

import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.mapper.PhoneClientMapper;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.PhoneClientPojo;
import com.jshandyman.service.service.PhoneClientService;
import com.jshandyman.service.validation.PhoneClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/phoneclient")
public class PhoneClientController {

    @Autowired
    PhoneClientService phoneclientService;

    @Autowired
    PhoneClientValidation phoneclientValidationService;

    @Autowired
   PhoneClientMapper phoneclientMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getnumber/{number}")
        private  ResponseEntity<EntityRespone> findByNumber(@PathVariable("number") String  number) {
            String busca = (String) phoneclientValidationService.validation(number);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(phoneclientService.findByNumber(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getcodeclient/{codeclient}")
        private  ResponseEntity<EntityRespone> findByCodeClient(@PathVariable("codeclient") String  codeclient) {
        String busca = (String) phoneclientValidationService.validation(codeclient);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(phoneclientService.findByCodeClient(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getnumbercontain/{number}")
        private ResponseEntity<EntityRespone> findByNumberContain(@PathVariable("number") String  number) {
            String busca = (String) phoneclientValidationService.validation(number);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(phoneclientService.findByNumberContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getcodeclientcontain/{codeclient}")
        private ResponseEntity<EntityRespone> findByCodeClientContain(@PathVariable("codeclient") String  codeclient) {
              String busca = (String) phoneclientValidationService.validation(codeclient);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(phoneclientService.findByCodeClientContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetPhoneClient/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(phoneclientService.findById(phoneclientValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllPhoneClient")
        private  ResponseEntity<EntityRespone> getAllPhoneClient(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(phoneclientService.getAllPhoneClient());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  savePhoneClient(@RequestBody PhoneClientPojo  phoneclient){ 
            return phoneclientService.savePhoneClient(phoneclientMapper.pojoToEntity(phoneclientValidationService.valida(phoneclient)) ); }



        @DeleteMapping("/deletePhoneClient/{id}")
            private boolean deletePhoneClient(@PathVariable("id") String id) {
            return phoneclientService.deletePhoneClient(phoneclientValidationService.valida_id(id)); }

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


