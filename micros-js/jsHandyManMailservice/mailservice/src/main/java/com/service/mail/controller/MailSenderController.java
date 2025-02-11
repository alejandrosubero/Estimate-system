
/*
Create on Sun Sep 11 00:37:30 EDT 2022
*Copyright (C) 122.
@author alejandro
@author js
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: email service for send </p>
*/


package com.service.mail.controller;

import com.service.mail.mapper.EmailDataConfigMapper;
import com.service.mail.mapper.MailSenderMapper;
import com.service.mail.mapper.MapperEntityRespone;
import com.service.mail.pojo.EntityRespone;
import com.service.mail.pojo.MailSenderPojo;
import com.service.mail.service.MailSenderService;
import com.service.mail.validation.EmailDataConfigValidation;
import com.service.mail.validation.MailSenderValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mailsender")
public class MailSenderController {

    @Autowired
    MailSenderService mailsenderService;

    @Autowired
    MailSenderValidation mailsenderValidationService;

    @Autowired
    MailSenderMapper mailsenderMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

    @Autowired
    EmailDataConfigValidation emaildataconfigValidationService;

    @Autowired
    EmailDataConfigMapper emaildataconfigMapper;


    @GetMapping("/send")
    private ResponseEntity<EntityRespone> mailSender() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailsenderService.sendMail());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/save")
    private Boolean saveMailSender(@RequestBody MailSenderPojo mailsender) {
        return mailsenderService.saveMailSender(mailsenderMapper.pojoToEntity(mailsenderValidationService.valida(mailsender)));
    }


    @GetMapping("/all")
    private ResponseEntity<EntityRespone> getAllMailSender() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailsenderService.getAllMailSender());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/update")
    private Long UpdateMailSender(@RequestBody MailSenderPojo mailsender) {
        mailsenderService.updateMailSender(mailsenderMapper.pojoToEntity(mailsenderValidationService.valida(mailsender)));
        return mailsender.getIdMailSender();
    }


    @PostMapping("/saveOrUpdate")
    private boolean saveOrUpdateMailSender(@RequestBody MailSenderPojo mailsender) {
        return mailsenderService.saveOrUpdateMailSender(mailsenderMapper.pojoToEntity(mailsenderValidationService.valida(mailsender)));
    }

    @GetMapping("/get/{wassend}")
    private ResponseEntity<EntityRespone> findByWasSendContain(@PathVariable("wassend") String wassend) {
        String busca = (String) mailsenderValidationService.validation(wassend);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailsenderService.findByWasSendContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/mailSender/id/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailsenderService.findById(mailsenderValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


//    @GetMapping("/Getport/{port}")
//    private ResponseEntity<EntityRespone> findByPort(@PathVariable("port") String port) {
//        String busca = (String) mailsenderValidationService.validation(port);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailsenderService.findByPort(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }


//    @GetMapping("/Gettemplate/{template}")
//    private ResponseEntity<EntityRespone> findByTemplate(@PathVariable("template") String template) {
//        String busca = (String) mailsenderValidationService.validation(template);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailsenderService.findByTemplate(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/Getactive/{active}")
//    private ResponseEntity<EntityRespone> findByActive(@PathVariable("active") Boolean active) {
//        Boolean busca = (Boolean) mailsenderValidationService.validation(active);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailsenderService.findByActive(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/Getwassend/{wassend}")
//    private ResponseEntity<EntityRespone> findByWasSend(@PathVariable("wassend") String wassend) {
//        String busca = (String) mailsenderValidationService.validation(wassend);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailsenderService.findByWasSend(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/Getemail/{email}")
//    private ResponseEntity<EntityRespone> findByEmail(@PathVariable("email") String email) {
//        String busca = (String) mailsenderValidationService.validation(email);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(mailsenderService.findByEmail(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/Getportcontain/{port}")
//    private ResponseEntity<EntityRespone> findByPortContain(@PathVariable("port") String port) {
//        String busca = (String) mailsenderValidationService.validation(port);
//        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailsenderService.findByPortContaining(busca));
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }


//    @GetMapping("/Gettemplatecontain/{template}")
//    private ResponseEntity<EntityRespone> findByTemplateContain(@PathVariable("template") String template) {
//        String busca = (String) mailsenderValidationService.validation(template);
//        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailsenderService.findByTemplateContaining(busca));
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }

//    @GetMapping("/Getactivecontain/{active}")
//    private ResponseEntity<EntityRespone> findByActiveContain(@PathVariable("active") Boolean active) {
//        Boolean busca = (Boolean) mailsenderValidationService.validation(active);
//        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailsenderService.findByActiveContaining(busca));
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }


//    @GetMapping("/Getemailcontain/{email}")
//    private ResponseEntity<EntityRespone> findByEmailContain(@PathVariable("email") String email) {
//        String busca = (String) mailsenderValidationService.validation(email);
//        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailsenderService.findByEmailContaining(busca));
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }


//
//    @PostMapping("/findEmailconfiguration")
//    private ResponseEntity<EntityRespone> findRelacionEmailDataConfig(@RequestBody EmailDataConfigPojo emaildataconfig) {
//        EntityRespone entityRespone = mapperEntityRespone.setEntityT(mailsenderService.findByRelacionEmailDataConfig(emaildataconfigMapper.pojoToEntity(emaildataconfigValidationService.valida(emaildataconfig))));
//        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//    }
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


