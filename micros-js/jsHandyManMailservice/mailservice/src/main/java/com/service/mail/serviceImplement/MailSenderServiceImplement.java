
/*
Create on Sun Sep 11 00:37:29 EDT 2022
*Copyright (C) 122.
@author alejandro
@author js
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: email service for send </p>
*/


package com.service.mail.serviceImplement;

import com.service.mail.entitys.MailSender;
import com.service.mail.mapper.MailSenderMapper;
import com.service.mail.pojo.MailSenderPojo;
import com.service.mail.repository.MailSenderRepository;
import com.service.mail.service.MailSenderService;
import com.service.mail.service.MailServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MailSenderServiceImplement implements MailSenderService {

    protected static final Log logger = LogFactory.getLog(MailSenderServiceImplement.class);

    @Autowired
    private MailSenderRepository mailsenderrepository;

    @Autowired
    private MailSenderMapper mailSenderMapper;

    @Autowired
    private MailServices mailServices;

    @Override
    public MailSender findById(Long id) {
        return mailsenderrepository.findById(id).get();
    }

    @Override
    public String sendMail(){
        var messages = "";
        try {
            List<MailSenderPojo> list = this.findByWasSend("N");
            if(list.size()>0){
                mailServices.eMail(list);
                messages =  "The mail was send...";
            }else {
                messages =  "No mail to send...";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "NO, DON'T SENT e= " + e;
        }
        return messages;
    }


    @Override
    public boolean saveMailSender(MailSender mailsender) {
        try {
            mailsenderrepository.save(mailsender);
            logger.info("Save mail...");
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean updateMailSender(MailSender mailsender) {
        logger.info("Update mail...");
        boolean clave = false;
        MailSender empre = findById(mailsender.getIdMailSender());
        empre = mailsender;
        try {
            mailsenderrepository.save(empre);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public boolean saveOrUpdateMailSender(MailSender mailsender) {
        logger.info("save Or Update mail...");
        boolean clave = false;
        Optional<MailSender> fileOptional2 = mailsenderrepository.findById(mailsender.getIdMailSender());
        if (fileOptional2.isPresent()) {
            clave = this.updateMailSender(mailsender);
            logger.info(" is update");
        } else {
            clave = this.saveMailSender(mailsender);
            logger.info(" is save");
        }
        return clave;
    }


    @Override
    public List<MailSenderPojo> findByWasSend(String wasSend) {
//        logger.info("Starting get List of Mail for Send P ...");
        List<MailSenderPojo> mailsenderPojo = new ArrayList<MailSenderPojo>();
        List<MailSender> mailsenderEntity = mailsenderrepository.findByWasSend(wasSend);
        mailsenderEntity.stream().forEach(mailSender -> mailsenderPojo.add(this.mailSenderMapper.entityToPojo(mailSender)));
        return mailsenderPojo;
    }

    @Override
    public List<MailSender> findByWasSendContaining(String wassend) {
        logger.info("Get find By Was Send Containing");
        List<MailSender> listaMailSender = new ArrayList<MailSender>();
        listaMailSender = mailsenderrepository.findByWasSendContaining(wassend);
        return listaMailSender;
    }


    @Override
    public MailSender findByTemplate(String template) {

        logger.info(" get By Template");
        MailSender mailsenderEntity = new MailSender();
        Optional<MailSender> fileOptional1 = mailsenderrepository.findByTemplate(template);

        if (fileOptional1.isPresent()) {
            try {
                mailsenderEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mailsenderEntity;
    }

    @Override
    public MailSender findByEmail(String email) {
        logger.info("find By Email...");
        MailSender mailsenderEntity = new MailSender();
        Optional<MailSender> fileOptional1 = mailsenderrepository.findByEmail(email);
        if (fileOptional1.isPresent()) {
            try {
                mailsenderEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mailsenderEntity;
    }


    @Override
    public List<MailSender> getAllMailSender() {
        logger.info("Get all");
        List<MailSender> listaMailSender = new ArrayList<MailSender>();
        try {
            mailsenderrepository.findAll().forEach(mailsender -> listaMailSender.add(mailsender));
        }catch (Exception e){
            e.printStackTrace();
            return listaMailSender;
        }
        return listaMailSender;
    }


    @Override
    public List<MailSender> findByPortContaining(String port) {
        logger.info("Get all Port Containing");
        List<MailSender> listaMailSender = new ArrayList<MailSender>();
        listaMailSender = mailsenderrepository.findByPortContaining(port);
        return listaMailSender;
    }

    @Override
    public List<MailSender> findByTemplateContaining(String template) {
        logger.info("Get all Template Containing");
        List<MailSender> listaMailSender = new ArrayList<MailSender>();
        listaMailSender = mailsenderrepository.findByTemplateContaining(template);
        return listaMailSender;
    }

    @Override
    public List<MailSender> findByActiveContaining(Boolean active) {
        logger.info("find By Active Containing");
        List<MailSender> listaMailSender = new ArrayList<MailSender>();
        listaMailSender = mailsenderrepository.findByActiveContaining(active);
        return listaMailSender;
    }


    @Override
    public List<MailSender> findByEmailContaining(String email) {
        logger.info("find By Email Containing");
        List<MailSender> listaMailSender = new ArrayList<MailSender>();
        listaMailSender = mailsenderrepository.findByEmailContaining(email);
        return listaMailSender;
    }


    //    @Override
//    public MailSender findByPort(String port) {
//        logger.info("Starting getMailSender");
//        MailSender mailsenderEntity = new MailSender();
//        Optional<MailSender> fileOptional1 = mailsenderrepository.findByPort(port);
//
//        if (fileOptional1.isPresent()) {
//
//            try {
//                mailsenderEntity = fileOptional1.get();
//            } catch (DataAccessException e) {
//                logger.error(" ERROR : " + e);
//
//            }
//        }
//        return mailsenderEntity;
//    }

    //    @Override
//    public MailSender findByActive(Boolean active) {
//
//        logger.info("Starting getMailSender");
//        MailSender mailsenderEntity = new MailSender();
//        Optional<MailSender> fileOptional1 = mailsenderrepository.findByActive(active);
//
//        if (fileOptional1.isPresent()) {
//
//            try {
//                mailsenderEntity = fileOptional1.get();
//            } catch (DataAccessException e) {
//                logger.error(" ERROR : " + e);
//
//            }
//        }
//        return mailsenderEntity;
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