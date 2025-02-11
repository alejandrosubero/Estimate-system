
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


package com.service.licenses.serviceImplement;

import com.service.licenses.entitys.Licencia;
import com.service.licenses.repository.LicenciaRepository;
import com.service.licenses.security.EncryptAES;
import com.service.licenses.service.LicenciaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LicenciaServiceImplement implements LicenciaService {

    protected static final Log logger = LogFactory.getLog(LicenciaServiceImplement.class);

    @Value("${keyAdmin2}")
    private String pass;

    @Autowired
    private LicenciaRepository licenciarepository;

    @Autowired
    private EncryptAES encryptAES;


    private String encrypt(String data){
        try {
            return encryptAES.encriptMaster(data, this.pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String dencrypt(String data){
        try {
            return encryptAES.decryptMaster(data, this.pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Licencia findByLisence(String lisence) {
        logger.info("Starting find  By Lisence");
        Licencia licenciaEntity = new Licencia();
        Optional<Licencia> fileOptional1 = licenciarepository.findByLisence(lisence);
        if (fileOptional1.isPresent()) {
            try {
                licenciaEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return licenciaEntity;
    }


    @Override
    public String findByLic(String lisence) {
        logger.info("Starting to get lisence (find By Lic)");
        Licencia licenciaEntity = new Licencia();
        String response = "";
        Optional<Licencia> fileOptional1 = licenciarepository.findByLisence(lisence);
        if (fileOptional1.isPresent()) {
            try {
                licenciaEntity = fileOptional1.get();
                response = licenciaEntity.getLisence() + "@" +licenciaEntity.getLisenceCompanyId()+ "@" +licenciaEntity.getCompany();
//                response = licenciaEntity.getLisence() + "@" +licenciaEntity.getLisenceCompanyId();

            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return response;
    }


    @Override
    public boolean delete(Long id) {
        logger.info("Delete lisence by id");
        boolean clave = false;
        try {
            licenciarepository.deleteById(id);
            clave = true;
            logger.info("lisence is Delete");
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
            logger.info("i can't not Delete the licencia");
        }
        return clave;
    }


    @Override
    public boolean deleteAll(Long id) {
        logger.info("Delete all lic");
        boolean clave = false;
        List<Licencia> listaDelete = null;
        try {
            listaDelete = this.getAllLicencia();
            listaDelete.stream().forEach(delete ->licenciarepository.deleteById(delete.getId()));
            clave = true;
            logger.info("is Delete all lic");
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
            logger.info("is fail the Delete of all lic");
        }
        return clave;
    }


    @Override
    public List<Licencia> getAllLicencia() {
        logger.info("Get All Licencia");
        List<Licencia> listaLicencia = new ArrayList<Licencia>();
        licenciarepository.findAll().forEach(licencia ->listaLicencia.add(licencia));
        listaLicencia.stream().forEach(lic ->  lic.setLisence(this.dencrypt(lic.getLisence())));
        return listaLicencia;
    }


    @Override
    public boolean saveLicencia(Licencia licencia) {
        logger.info("Save new licencia for Proyect");
        try {
            licencia.setLisence(this.encrypt(licencia.getLisence()));
            licenciarepository.save(licencia);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        } catch (Exception d) {
            d.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateLicencia(Licencia licencia) {
        logger.info("Update licencia");
        boolean clave = false;
        Licencia empre = findById(licencia.getId());
        empre = licencia;
        try {
            String lic = EncryptAES.encryptAES(licencia.getLisence());
            licencia.setLisence(lic);
            licenciarepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        } catch (Exception e) {
            clave = false;
            e.printStackTrace();
        }
        return clave;
    }


    @Override
    public Licencia findById(Long id) {
        logger.info("find By Id");
        return licenciarepository.findById(id).get();
    }


    @Override
    public boolean saveOrUpdateLicencia(Licencia licencia) {
        logger.info("Update licencia");
        boolean clave = false;
        try {
        Optional<Licencia> fileOptional2 = licenciarepository.findById(licencia.getId());
        if (fileOptional2.isPresent()) {
            String lic = null;
                lic = EncryptAES.encryptAES(licencia.getLisence());
                licencia.setLisence(lic);
            clave = this.updateLicencia(licencia);
            logger.info(" is update");
        } else {
            clave = this.saveLicencia(licencia);
            logger.info(" is save");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clave;
    }

    @Override
    public Licencia findByCompany(String company) {
        logger.info("find By Company");
        Licencia licenciaEntity = new Licencia();
        Optional<Licencia> fileOptional1 = licenciarepository.findByCompany(company);
        if (fileOptional1.isPresent()) {
            try {
                licenciaEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return licenciaEntity;
            }
        }
        return licenciaEntity;
    }


    @Override
    public Licencia findByLisenceCompanyId(String lisenceCompanyId) {

        logger.info("find By Lisence by Company Id");
        Licencia licenciaEntity = new Licencia();
        Optional<Licencia> fileOptional1 = licenciarepository.findByLisenceCompanyId(lisenceCompanyId);
        if (fileOptional1.isPresent()) {
            try {
                licenciaEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
                return licenciaEntity;
            }
        }
        return licenciaEntity;
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