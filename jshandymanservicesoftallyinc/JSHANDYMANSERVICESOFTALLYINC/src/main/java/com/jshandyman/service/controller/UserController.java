
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

import com.jshandyman.service.entitys.User;
import com.jshandyman.service.security.EncryptAES;
import com.jshandyman.service.validation.UserValidation;
import com.jshandyman.service.mapper.UserMapper;
import com.jshandyman.service.service.UserService;
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
import com.jshandyman.service.pojo.UserPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidation userValidationService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;


    @PostMapping("/save")
    private Boolean saveUser(@RequestBody UserPojo user) {
        return userService.saveUser(userMapper.pojoToEntity(userValidationService.valida(user)));
    }


    @PostMapping("/u")
    private EntityRespone updateUser(@RequestBody UserPojo user, @RequestHeader("Company")  String company) {
        return userService.updateUser(userMapper.pojoToEntity( userValidationService.validaUpdateUser(user)));
    }


    @DeleteMapping("/deleteUser/{id}")
    private boolean deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(userValidationService.valida_id(id));
    }

    @GetMapping("/Getusercode/{usercode}")
    private ResponseEntity<EntityRespone> findByUserCode(@PathVariable("usercode") String usercode) {
        String busca = (String) userValidationService.validation(usercode);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserCode(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getuserfirsname/{userfirsname}")
    private ResponseEntity<EntityRespone> findByUserFirsName(@PathVariable("userfirsname") String userfirsname) {
        String busca = (String) userValidationService.validation(userfirsname);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserFirsName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getuserlastname/{userlastname}")
    private ResponseEntity<EntityRespone> findByUserLastName(@PathVariable("userlastname") String userlastname) {
        String busca = (String) userValidationService.validation(userlastname);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserLastName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getfullname/{fullname}")
    private ResponseEntity<EntityRespone> findByFullName(@PathVariable("fullname") String fullname) {
        String busca = (String) userValidationService.validation(fullname);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByFullName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getusername/{username}")
    private ResponseEntity<EntityRespone> findByUserName(@PathVariable("username") String username) {
        String busca = (String) userValidationService.validation(username);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByUserName(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getmail/{mail}")
    private ResponseEntity<EntityRespone> findByMail(@PathVariable("mail") String mail) {
        String busca = (String) userValidationService.validation(mail);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByMail(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getpassword/{password}")
    private ResponseEntity<EntityRespone> findByPassword(@PathVariable("password") String password) {
        String busca = (String) userValidationService.validation(password);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByPassword(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getaccountnonexpired/{accountnonexpired}")
    private ResponseEntity<EntityRespone> findByAccountNonExpired(@PathVariable("accountnonexpired") Boolean accountnonexpired) {
        Boolean busca = (Boolean) userValidationService.validation(accountnonexpired);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByAccountNonExpired(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getaccountnonlocked/{accountnonlocked}")
    private ResponseEntity<EntityRespone> findByAccountNonLocked(@PathVariable("accountnonlocked") Boolean accountnonlocked) {
        Boolean busca = (Boolean) userValidationService.validation(accountnonlocked);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByAccountNonLocked(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getcredentialsnonexpired/{credentialsnonexpired}")
    private ResponseEntity<EntityRespone> findByCredentialsNonExpired(@PathVariable("credentialsnonexpired") Boolean credentialsnonexpired) {
        Boolean busca = (Boolean) userValidationService.validation(credentialsnonexpired);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByCredentialsNonExpired(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getenabled/{enabled}")
    private ResponseEntity<EntityRespone> findByEnabled(@PathVariable("enabled") Boolean enabled) {
        Boolean busca = (Boolean) userValidationService.validation(enabled);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByEnabled(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Getrol/{rol}")
    private ResponseEntity<EntityRespone> findByRol(@PathVariable("rol") String rol) {
        String busca = (String) userValidationService.validation(rol);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findByRol(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/Getusercodecontain/{usercode}")
    private ResponseEntity<EntityRespone> findByUserCodeContain(@PathVariable("usercode") String usercode) {
        String busca = (String) userValidationService.validation(usercode);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserCodeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getuserfirsnamecontain/{userfirsname}")
    private ResponseEntity<EntityRespone> findByUserFirsNameContain(@PathVariable("userfirsname") String userfirsname) {
        String busca = (String) userValidationService.validation(userfirsname);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserFirsNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getuserlastnamecontain/{userlastname}")
    private ResponseEntity<EntityRespone> findByUserLastNameContain(@PathVariable("userlastname") String userlastname) {
        String busca = (String) userValidationService.validation(userlastname);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserLastNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getfullnamecontain/{fullname}")
    private ResponseEntity<EntityRespone> findByFullNameContain(@PathVariable("fullname") String fullname) {
        String busca = (String) userValidationService.validation(fullname);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByFullNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getusernamecontain/{username}")
    private ResponseEntity<EntityRespone> findByUserNameContain(@PathVariable("username") String username) {
        String busca = (String) userValidationService.validation(username);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByUserNameContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getmailcontain/{mail}")
    private ResponseEntity<EntityRespone> findByMailContain(@PathVariable("mail") String mail) {
        String busca = (String) userValidationService.validation(mail);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByMailContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getpasswordcontain/{password}")
    private ResponseEntity<EntityRespone> findByPasswordContain(@PathVariable("password") String password) {
        String busca = (String) userValidationService.validation(password);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByPasswordContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getaccountnonexpiredcontain/{accountnonexpired}")
    private ResponseEntity<EntityRespone> findByAccountNonExpiredContain(@PathVariable("accountnonexpired") Boolean accountnonexpired) {
        Boolean busca = (Boolean) userValidationService.validation(accountnonexpired);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByAccountNonExpiredContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getaccountnonlockedcontain/{accountnonlocked}")
    private ResponseEntity<EntityRespone> findByAccountNonLockedContain(@PathVariable("accountnonlocked") Boolean accountnonlocked) {
        Boolean busca = (Boolean) userValidationService.validation(accountnonlocked);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByAccountNonLockedContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getcredentialsnonexpiredcontain/{credentialsnonexpired}")
    private ResponseEntity<EntityRespone> findByCredentialsNonExpiredContain(@PathVariable("credentialsnonexpired") Boolean credentialsnonexpired) {
        Boolean busca = (Boolean) userValidationService.validation(credentialsnonexpired);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByCredentialsNonExpiredContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getenabledcontain/{enabled}")
    private ResponseEntity<EntityRespone> findByEnabledContain(@PathVariable("enabled") Boolean enabled) {
        Boolean busca = (Boolean) userValidationService.validation(enabled);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByEnabledContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/Getrolcontain/{rol}")
    private ResponseEntity<EntityRespone> findByRolContain(@PathVariable("rol") String rol) {
        String busca = (String) userValidationService.validation(rol);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.findByRolContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetUser/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(userService.findById(userValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/GetAllUser")
    private ResponseEntity<EntityRespone> getAllUser( @RequestHeader("Company")  String company) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(userService.getAllUser(company));
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


