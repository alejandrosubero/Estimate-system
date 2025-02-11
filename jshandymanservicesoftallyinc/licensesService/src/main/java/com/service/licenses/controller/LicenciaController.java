
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


package com.service.licenses.controller;

import com.service.licenses.mapper.LicenciaMapper;
import com.service.licenses.mapper.MapperEntityRespone;
import com.service.licenses.pojo.EntityRespone;
import com.service.licenses.pojo.KeyPojo;
import com.service.licenses.pojo.LicenciaPojo;
import com.service.licenses.service.LicenciaService;
import com.service.licenses.validation.LicenciaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/licencia")
public class LicenciaController {

    @Autowired
    LicenciaService licenciaService;

    @Autowired
    LicenciaValidation licenciaValidationService;

    @Autowired
    LicenciaMapper licenciaMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;


    @GetMapping("/data/api/{lisence}")
    private ResponseEntity<EntityRespone> findByLisence(@PathVariable("lisence") String lisence) {
        String busca = (String) licenciaValidationService.validation(lisence);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(licenciaService.findByLisence(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(licenciaService.findById(licenciaValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/all")
    private ResponseEntity<EntityRespone> getAllLicencia() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(licenciaService.getAllLicencia());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @PostMapping("/save")
    private Boolean saveLicencia(@RequestBody LicenciaPojo licencia) {
        return licenciaService.saveLicencia(licenciaMapper.pojoToEntity(licenciaValidationService.valida(licencia)));
    }


    @PostMapping("/Update")
    private Long UpdateLicencia(@RequestBody LicenciaPojo licencia) {
        licenciaService.updateLicencia(licenciaMapper.pojoToEntity(licenciaValidationService.valida(licencia)));
        return licencia.getId();
    }


    @PostMapping("/api/licenses")
    private String findBylice(@RequestBody KeyPojo lisence) {
        String busca = (String) licenciaValidationService.validation(lisence.getKey());
        try {
            return licenciaService.findByLic(busca);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @PostMapping("/saveOrUpdate")
    private boolean saveOrUpdateLicencia(@RequestBody LicenciaPojo licencia) {
        return licenciaService.saveOrUpdateLicencia(licenciaMapper.pojoToEntity(licenciaValidationService.valida(licencia)));
    }


    @GetMapping("/delete/{id}")
    private boolean delete(@PathVariable("id") String id) {
        return licenciaService.delete(licenciaValidationService.valida_id(id));
    }


    @GetMapping("/company/id/{lisencecompanyid}")
    private ResponseEntity<EntityRespone> findByLisenceCompanyId(@PathVariable("lisencecompanyid") String lisencecompanyid) {
        String busca = (String) licenciaValidationService.validation(lisencecompanyid);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(licenciaService.findByLisenceCompanyId(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/company/{company}")
    private ResponseEntity<EntityRespone> findByCompany(@PathVariable("company") String company) {
        String busca = (String) licenciaValidationService.validation(company);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(licenciaService.findByCompany(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
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


