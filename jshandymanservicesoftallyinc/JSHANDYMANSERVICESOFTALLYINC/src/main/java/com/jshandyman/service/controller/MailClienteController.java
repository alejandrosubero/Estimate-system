
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
import com.jshandyman.service.entitys.MailCliente;
import com.jshandyman.service.validation.MailClienteValidation;
import com.jshandyman.service.mapper.MailClienteMapper;
import com.jshandyman.service.service.MailClienteService;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.pojo.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.jshandyman.service.pojo.MailClientePojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/mailcliente")
public class MailClienteController {

    @Autowired
    MailClienteService mailclienteService;

    @Autowired
    MailClienteValidation mailclienteValidationService;

    @Autowired
   MailClienteMapper mailclienteMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getemail/{email}")
        private  ResponseEntity<EntityRespone> findByEmail(@PathVariable("email") String  email) {
        String busca = (String) mailclienteValidationService.validation(email);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailclienteService.findByEmail(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getcodeclient/{codeclient}")
        private  ResponseEntity<EntityRespone> findByCodeClient(@PathVariable("codeclient") String  codeclient) {
        String busca = (String) mailclienteValidationService.validation(codeclient);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailclienteService.findByCodeClient(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getemailcontain/{email}")
        private ResponseEntity<EntityRespone> findByEmailContain(@PathVariable("email") String  email) {
              String busca = (String) mailclienteValidationService.validation(email);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailclienteService.findByEmailContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getcodeclientcontain/{codeclient}")
        private ResponseEntity<EntityRespone> findByCodeClientContain(@PathVariable("codeclient") String  codeclient) {
              String busca = (String) mailclienteValidationService.validation(codeclient);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailclienteService.findByCodeClientContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetMailCliente/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailclienteService.findById(mailclienteValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllMailCliente")
        private  ResponseEntity<EntityRespone> getAllMailCliente(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailclienteService.getAllMailCliente());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveMailCliente(@RequestBody MailClientePojo  mailcliente){ 
            return mailclienteService.saveMailCliente(mailclienteMapper.pojoToEntity(mailclienteValidationService.valida(mailcliente)) ); }





        @DeleteMapping("/deleteMailCliente/{id}")
            private boolean deleteMailCliente(@PathVariable("id") String id) {
            return mailclienteService.deleteMailCliente(mailclienteValidationService.valida_id(id)); }

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


