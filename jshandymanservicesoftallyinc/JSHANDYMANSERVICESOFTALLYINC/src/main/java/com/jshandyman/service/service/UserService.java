
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.service;

import java.util.Optional;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import com.jshandyman.service.entitys.User;
import com.jshandyman.service.pojo.EntityRespone;


public interface UserService {

    public EntityRespone updateUser(User userPojo);

    public EntityRespone newUser(User user, String Key);

    public User findByUserCode(String userCode);

    public User findByUserFirsName(String userFirsName);

    public User findByUserLastName(String userLastName);

    public User findByFullName(String fullName);

    public User findByUserName(String userName);

    public User findByMail(String mail);

    public List<User> findByMailContaining(String mail);

    public User findByPassword(String password);

    public User findByAccountNonExpired(Boolean accountNonExpired);

    public User findByAccountNonLocked(Boolean accountNonLocked);

    public User findByCredentialsNonExpired(Boolean credentialsNonExpired);

    public User findByEnabled(Boolean enabled);

    public User findByRol(String rol);

    public List<User> findByUserCodeContaining(String userCode);

    public List<User> findByUserFirsNameContaining(String userFirsName);

    public List<User> findByUserLastNameContaining(String userLastName);

    public List<User> findByFullNameContaining(String fullName);

    public List<User> findByUserNameContaining(String userName);



    public List<User> findByPasswordContaining(String password);

    public List<User> findByAccountNonExpiredContaining(Boolean accountNonExpired);

    public List<User> findByAccountNonLockedContaining(Boolean accountNonLocked);

    public List<User> findByCredentialsNonExpiredContaining(Boolean credentialsNonExpired);

    public List<User> findByEnabledContaining(Boolean enabled);

    public List<User> findByRolContaining(String rol);

    public User findById(Long id);

    public boolean saveUser(User user);

    public List<User> getAllUser(String company);

    public boolean deleteUser(Long id);

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


