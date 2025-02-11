
/*
Create on Mon Aug 22 18:51:50 EDT 2022
*Copyright (C) 122.
@author alejandro subero
@author JS
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: service of template, parameter </p>
*/



package com.template.parameter.controller;
import com.template.parameter.entitys.User;
import com.template.parameter.validation.UserValidation;
import com.template.parameter.mapper.UserMapper;
import com.template.parameter.service.UserService;
import com.template.parameter.mapper.MapperEntityRespone;
import com.template.parameter.pojo.EntityRespone;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.template.parameter.pojo.UserPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidation userValidationService;

    @Autowired
   UserMapper userMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getname/{name}")
        private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
        String busca = (String) userValidationService.validation(name);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByName(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getnamecontain/{name}")
        private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
              String busca = (String) userValidationService.validation(name);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByNameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetUser/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findById(userValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllUser")
        private  ResponseEntity<EntityRespone> getAllUser(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.getAllUser());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveUser(@RequestBody UserPojo  user){ 
            return userService.saveUser(userMapper.pojoToEntity(userValidationService.valida(user)) ); }



        @PostMapping("/Update")
        private Long UpdateUser(@RequestBody UserPojo  user){ 
        userService.updateUser(userMapper.pojoToEntity(userValidationService.valida(user)));
            return user.getId(); }


        @PostMapping("/saveOrUpdate")
        private boolean saveOrUpdateUser(@RequestBody UserPojo  user){ 
            return userService.saveOrUpdateUser(userMapper.pojoToEntity(userValidationService.valida(user)) ); }


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


