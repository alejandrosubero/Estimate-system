
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.entitys.User;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.KeyPojo;
import com.jshandyman.service.repository.UserRepository;
import com.jshandyman.service.security.EncryptAES;
import com.jshandyman.service.service.ParametersServices;
import com.jshandyman.service.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Service
public class UserServiceImplement implements UserService {

    protected static final Log logger = LogFactory.getLog(UserServiceImplement.class);

    @Autowired
    private UserRepository userrepository;

    @Value("${keyAdmin}")
    private String keyAd;

    @Value("${saltAESKey}")
    private String saltAES;

    @Value("${keyAdmin2}")
    private String pass;

//    @Value("${licenceserver}")
//    private String serverUrl;

    @Autowired
    private ParametersServices parametersServices;

    @Autowired
    private EncryptAES encryptAES;

    @Autowired
    private RestTemplateBuilder restTemplate;

    private final String  ADMIN = "ADMIN";
    private final String  USER = "USER";

    @Override
    public EntityRespone newUser(User userRecibe, String Key) {
        logger.info("Save a new User");

        try {
            String license = encryptAES.encript(Key, pass);
           User user = this.decryptUserUpdate(userRecibe);

            String serverUrl = parametersServices.findByClave("licenceserver").getValor();
            ResponseEntity<String> result = restTemplate.build().postForEntity(new URI(serverUrl), new KeyPojo(license), String.class);
            String lic = result.getBody();
            String[] arrOfStr = lic.split("@");

            if (Key != null && license.equals(arrOfStr[0])) {

                List<Object> response = new ArrayList<Object>();

                if (userNameExist(user.getUserName())) {
                    return new EntityRespone("newUserExist01", "The user Name exist use oter userName");
                }

                if (userMailExist(user.getMail())) {
                    return new EntityRespone("newUserExist02", "The user mail exist use oter Mail for the user");
                }

                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                user.setEnabled(true);
                user.setFullName(user.getUserFirsName() + " " + user.getUserLastName());

                String pass = encryptAES.encript(user.getPassword(), saltAES);
                user.setPassword(pass);

                user.setUserCode(createUserCode());
                user.setDateChange(new Date());

                String rol = arrOfStr[1].equals(ADMIN)? ADMIN : USER;
                user.setRol(rol);

                if(arrOfStr != null && arrOfStr.length > 2){
                   user.setCompany(arrOfStr[2]);
                }

                String respuesta = encryptAES.encript(user.getRespuesta(), saltAES);
                user.setRespuesta(respuesta);

                response.add(this.saveUser(user));
                return new EntityRespone("", "The user was save", response);
            }
            return new EntityRespone("bat license", "An error occurred while trying to verifacate the license");

        } catch (DataAccessException | URISyntaxException e) {
            logger.error(" ERROR : " + e);
            return new EntityRespone("newUserExist00", "An error occurred while trying to save a new user");
        }
    }


    private Boolean userMailExist(String mail) {
        logger.info("user Mail Exist?...");
        Optional<User> fileOptional1 = userrepository.findByMail(mail);
            try {
                if (fileOptional1.isPresent() && fileOptional1.get() != null ) {
                    return true;
                }
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return false;
            }
        return false;
    }


    private Boolean userNameExist(String name) {
        logger.info("user Name Exist?...");
        User userFile = this.findByUserName(name);
        if (userFile.getUserName() != null) {
            return true;
        }
        return false;
    }


    private String createUserCode() {
        return generateCode();
    }

    private String generateCode() {
        String code = UUID.randomUUID().toString();
        User userFile = findByUserCode(code);
        if (userFile.getUserCode() != null && userFile.getUserCode().equals(code)) {
            generateCode();
        }
        return code;
    }


    @Override
    public EntityRespone updateUser(User userUpdate) {
        boolean response = false;
        String mennsage = null;
        String error = null;
        List<Object> list = new ArrayList<>();

        User userOld = this.findByUserCode(userUpdate.getUserCode());
        User userPojo = this.decryptUserUpdate(userUpdate);

        if (userPojo.getUserName() != null && userPojo.getUserName() != "" ) {
            if (userNameExist(userPojo.getUserName())) {
                list.add(false);
                return new EntityRespone("newUserExist01", "The user Name existe use oter userName", list);
            }

            if (userMailExist(userPojo.getMail())) {
                return new EntityRespone("newUserExist01", "The user mail exist use oter Mail for the user");
            }
        }

        if (userOld != null && userOld.getIdUser() != null && userPojo != null) {

            if (userPojo.getUserFirsName() != null) {
                userOld.setUserFirsName(userPojo.getUserFirsName());
            }

            if (userPojo.getUserLastName() != null) {
                userOld.setUserLastName(userPojo.getUserLastName());
            }

            if (userPojo.getMail() != null && !userMailExist(userPojo.getMail())) {
                userOld.setMail(userPojo.getMail());
            }

            if (userPojo.getPassword() != null) {
                String pass = encryptAES.encript(userPojo.getPassword(), saltAES);
                userOld.setPassword(pass);
            }

            if (userPojo.getImagen() != null) {
                userOld.setImagen(userPojo.getImagen());
            }

            if (userPojo.getUserName() != null && !userNameExist(userPojo.getUserName())) {
                userOld.setUserName(userPojo.getUserName());
            }

            if (userPojo.getRespuesta() != null ) {
                String respuesta = encryptAES.encript(userPojo.getRespuesta(), saltAES);
                userOld.setRespuesta(respuesta);
            }

            if (userPojo.getPregunta() != null ) {
                userOld.setPregunta(userPojo.getPregunta());
            }

            userOld.setDateChange(new Date());
            response = saveUser(userOld);
            list.add(response);
            mennsage = response?"The user was save":"An error occurred while trying to save a user";
            error = response? "" : "newUserExist00";
            logger.info("Save user changes...");
        }
        return new EntityRespone(error, mennsage, list);
    }


    private User decryptUserUpdate(User userPojo) {
        User user = new User();
        try {
            if (userPojo != null) {
                if (userPojo.getUserFirsName() != null && userPojo.getUserFirsName() != "") {
                    user.setUserFirsName(encryptAES.decryptAES(userPojo.getUserFirsName()).trim());
                }

                if (userPojo.getUserLastName() != null && userPojo.getUserLastName() != "") {
                    user.setUserLastName(encryptAES.decryptAES(userPojo.getUserLastName()).trim());
                }

                if (userPojo.getMail() != null && userPojo.getMail() != "") {
                    user.setMail(encryptAES.decryptAES(userPojo.getMail()).trim());
                }

                if (userPojo.getPassword() != null && userPojo.getPassword() != "") {
                    user.setPassword(encryptAES.decryptAES(userPojo.getPassword()).trim());
                }

                if (userPojo.getPregunta() != null && userPojo.getPregunta() != "") {
                    user.setPregunta(encryptAES.decryptAES(userPojo.getPregunta()));
                }

                if (userPojo.getRespuesta() != null && userPojo.getRespuesta() != "") {
                    user.setRespuesta(encryptAES.decryptAES(userPojo.getRespuesta()));
                }

                if (userPojo.getImagen() != null && userPojo.getImagen() != "") {
                    user.setImagen(encryptAES.decryptAES(userPojo.getImagen()));
                }

                if (userPojo.getUserName() != null && userPojo.getUserName() != "" ) {
                    user.setUserName(encryptAES.decryptAES(userPojo.getUserName()).trim());
                }
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return user;
        }
    }



    @Override
    public boolean saveUser(User user) {
        logger.info("Save Proyect");
        try {
            userrepository.save(user);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public User findByUserCode(String userCode) {
        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByUserCode(userCode);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByUserFirsName(String userFirsName) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByUserFirsName(userFirsName);
        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByUserLastName(String userLastName) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByUserLastName(userLastName);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByFullName(String fullName) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByFullName(fullName);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByUserName(String userName) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByUserName(userName);
        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByMail(String mail) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByMail(mail);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
                String respuesta = encryptAES.decrypt(userEntity.getRespuesta(), saltAES);
                String pass = encryptAES.decrypt(userEntity.getPassword(), saltAES);
                userEntity.setRespuesta(respuesta);
                userEntity.setPassword(pass);

            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }




    @Override
    public User findByPassword(String password) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByPassword(password);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByAccountNonExpired(Boolean accountNonExpired) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByAccountNonExpired(accountNonExpired);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByAccountNonLocked(Boolean accountNonLocked) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByAccountNonLocked(accountNonLocked);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByCredentialsNonExpired(Boolean credentialsNonExpired) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByCredentialsNonExpired(credentialsNonExpired);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByEnabled(Boolean enabled) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByEnabled(enabled);

        if (fileOptional1.isPresent()) {
            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }

    @Override
    public User findByRol(String rol) {

        logger.info("Starting getUser");
        User userEntity = new User();
        Optional<User> fileOptional1 = userrepository.findByRol(rol);

        if (fileOptional1.isPresent()) {

            try {
                userEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return userEntity;
    }


    @Override
    public List<User> getAllUser(String company) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        userrepository.findByCompany(company).forEach(user -> listaUser.add(user));
        return listaUser;
    }


    @Override
    public boolean deleteUser(Long id) {
        logger.info("Delete Proyect");
        boolean clave = false;


        try {
            userrepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public User findById(Long id) {
        return userrepository.findById(id).get();
    }


    @Override
    public List<User> findByUserCodeContaining(String usercode) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByUserCodeContaining(usercode);
        return listaUser;
    }

    @Override
    public List<User> findByUserFirsNameContaining(String userfirsname) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByUserFirsNameContaining(userfirsname);
        return listaUser;
    }

    @Override
    public List<User> findByUserLastNameContaining(String userlastname) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByUserLastNameContaining(userlastname);
        return listaUser;
    }

    @Override
    public List<User> findByFullNameContaining(String fullname) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByFullNameContaining(fullname);
        return listaUser;
    }

    @Override
    public List<User> findByUserNameContaining(String username) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByUserNameContaining(username);
        return listaUser;
    }

    @Override
    public List<User> findByMailContaining(String mail) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByMailContaining(mail);
        return listaUser;
    }

    @Override
    public List<User> findByPasswordContaining(String password) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByPasswordContaining(password);
        return listaUser;
    }

    @Override
    public List<User> findByAccountNonExpiredContaining(Boolean accountnonexpired) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByAccountNonExpiredContaining(accountnonexpired);
        return listaUser;
    }

    @Override
    public List<User> findByAccountNonLockedContaining(Boolean accountnonlocked) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByAccountNonLockedContaining(accountnonlocked);
        return listaUser;
    }

    @Override
    public List<User> findByCredentialsNonExpiredContaining(Boolean credentialsnonexpired) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByCredentialsNonExpiredContaining(credentialsnonexpired);
        return listaUser;
    }

    @Override
    public List<User> findByEnabledContaining(Boolean enabled) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByEnabledContaining(enabled);
        return listaUser;
    }

    @Override
    public List<User> findByRolContaining(String rol) {
        logger.info("Get allProyect");
        List<User> listaUser = new ArrayList<User>();
        listaUser = userrepository.findByRolContaining(rol);
        return listaUser;
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


}
