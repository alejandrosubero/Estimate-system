
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
import com.jshandyman.service.entitys.ProcutService;
import com.jshandyman.service.validation.ProcutServiceValidation;
import com.jshandyman.service.mapper.ProcutServiceMapper;
import com.jshandyman.service.service.ProcutServiceService;
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
import com.jshandyman.service.pojo.ProcutServicePojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/procutservice")
public class ProcutServiceController {

    @Autowired
    ProcutServiceService procutserviceService;

    @Autowired
    ProcutServiceValidation procutserviceValidationService;

    @Autowired
   ProcutServiceMapper procutserviceMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Gettypeservice/{typeservice}")
        private  ResponseEntity<EntityRespone> findByTypeService(@PathVariable("typeservice") String  typeservice) {
        String busca = (String) procutserviceValidationService.validation(typeservice);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(procutserviceService.findByTypeService(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Gettypeservicecontain/{typeservice}")
        private ResponseEntity<EntityRespone> findByTypeServiceContain(@PathVariable("typeservice") String  typeservice) {
              String busca = (String) procutserviceValidationService.validation(typeservice);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(procutserviceService.findByTypeServiceContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetProcutService/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(procutserviceService.findById(procutserviceValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllProcutService")
        private  ResponseEntity<EntityRespone> getAllProcutService(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(procutserviceService.getAllProcutService());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveProcutService(@RequestBody ProcutServicePojo  procutservice){ 
            return procutserviceService.saveProcutService(procutserviceMapper.pojoToEntity(procutserviceValidationService.valida(procutservice)) ); }





        @DeleteMapping("/deleteProcutService/{id}")
            private boolean deleteProcutService(@PathVariable("id") String id) {
            return procutserviceService.deleteProcutService(procutserviceValidationService.valida_id(id)); }

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


