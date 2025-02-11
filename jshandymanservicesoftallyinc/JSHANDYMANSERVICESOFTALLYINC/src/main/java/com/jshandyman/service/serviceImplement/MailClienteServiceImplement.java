
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

import com.jshandyman.service.entitys.MailCliente;
import com.jshandyman.service.repository.MailClienteRepository;
import com.jshandyman.service.service.MailClienteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MailClienteServiceImplement implements MailClienteService {

    protected static final Log logger = LogFactory.getLog(MailClienteServiceImplement.class);
    @Autowired
    private MailClienteRepository mailclienterepository;

    @Override
    public MailCliente findByEmail(String email) {

        logger.info("Starting getMailCliente");
        MailCliente mailclienteEntity = new MailCliente();
        Optional<MailCliente> fileOptional1 = mailclienterepository.findByEmail(email);

        if (fileOptional1.isPresent()) {
            try {
                mailclienteEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mailclienteEntity;
    }

    @Override
    public MailCliente findByCodeClient(String codeClient) {

        logger.info("Starting getMailCliente");
        MailCliente mailclienteEntity = new MailCliente();
        Optional<MailCliente> fileOptional1 = mailclienterepository.findByCodeClient(codeClient);

        if (fileOptional1.isPresent()) {
            try {
                mailclienteEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mailclienteEntity;
    }


    @Override
    public List<MailCliente> getAllMailCliente() {
        logger.info("Get allProyect");
        List<MailCliente> listaMailCliente = new ArrayList<MailCliente>();
        mailclienterepository.findAll().forEach(mailcliente -> listaMailCliente.add(mailcliente));
        return listaMailCliente;
    }


    @Override
    public boolean saveMailCliente(MailCliente mailcliente) {
        logger.info("Save Proyect");
        try {
            mailclienterepository.save(mailcliente);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean deleteMailCliente(Long id) {
        logger.info("Delete Proyect");
        boolean clave = false;
        try {
            mailclienterepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public MailCliente findById(Long id) {
        return mailclienterepository.findById(id).get();
    }


    @Override
    public List<MailCliente> findByEmailContaining(String email) {
        logger.info("Get allProyect");
        List<MailCliente> listaMailCliente = new ArrayList<MailCliente>();
        listaMailCliente = mailclienterepository.findByEmailContaining(email);
        return listaMailCliente;
    }

    @Override
    public List<MailCliente> findByCodeClientContaining(String codeclient) {
        logger.info("Get allProyect");
        List<MailCliente> listaMailCliente = new ArrayList<MailCliente>();
        listaMailCliente = mailclienterepository.findByCodeClientContaining(codeclient);
        return listaMailCliente;
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
