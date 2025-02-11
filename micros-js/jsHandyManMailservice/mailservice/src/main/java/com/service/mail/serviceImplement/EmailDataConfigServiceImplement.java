
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



package com.service.mail.serviceImplement ;


import com.service.mail.entitys.EmailDataConfig;
import com.service.mail.mapper.EmailDataConfigMapper;
import com.service.mail.pojo.EmailDataConfigPojo;
import com.service.mail.repository.EmailDataConfigRepository;
import com.service.mail.security.EncryptAES;
import com.service.mail.service.EmailDataConfigService;
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
public class EmailDataConfigServiceImplement implements EmailDataConfigService {

	protected static final Log logger = LogFactory.getLog(EmailDataConfigServiceImplement.class);
	@Autowired
	private EmailDataConfigRepository emaildataconfigrepository;

	@Autowired
	private EmailDataConfigMapper emailDataConfigMapper;

	@Value("${keyAdmin2}")
	private String saltAES;

	@Autowired
	private EncryptAES encryptAES;


	@Override
	public EmailDataConfig encript(EmailDataConfig emailDataConfig) {

		EmailDataConfig pojo = new EmailDataConfig();

		if (emailDataConfig.getIdemailconfig() != null){
			pojo.setIdemailconfig(emailDataConfig.getIdemailconfig());
		}
		pojo.setMailpassword(encryptAES.encript(emailDataConfig.getMailpassword(), saltAES));
		pojo.setMailusername(encryptAES.encript(emailDataConfig.getMailusername(), saltAES));
		pojo.setHost(emailDataConfig.getHost());
		pojo.setPort(emailDataConfig.getPort());

		return pojo;
	}

	@Override
	public EmailDataConfigPojo decrypt(EmailDataConfig emailDataConfig) {
		EmailDataConfigPojo pojo = null;

		try {
			if (emailDataConfig != null){
				pojo = new EmailDataConfigPojo();

				if (emailDataConfig.getIdemailconfig() != null){
					pojo.setIdemailconfig(emailDataConfig.getIdemailconfig());
				}
				pojo.setMailpassword(encryptAES.decrypt(emailDataConfig.getMailpassword(), saltAES));
				pojo.setMailusername(encryptAES.decrypt(emailDataConfig.getMailusername(), saltAES));
				pojo.setHost(emailDataConfig.getHost());
				pojo.setPort(emailDataConfig.getPort());
			}
		}catch (Exception e){
			e.printStackTrace();
			return pojo;
		}
		return pojo;
	}


	@Override
	public EmailDataConfigPojo findByHost(String host) {

		logger.info("Starting getEmailDataConfig");
		EmailDataConfig emaildataconfigEntity = new EmailDataConfig();
		Optional<EmailDataConfig> fileOptional1 = emaildataconfigrepository.findByHost(host);
		if (fileOptional1.isPresent()) {
			try {
				emaildataconfigEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return this.decrypt(emaildataconfigEntity);
	}

	@Override
	public EmailDataConfigPojo findByPort(String port) {
		logger.info("Starting getEmailDataConfig");
		EmailDataConfig emaildataconfigEntity = new EmailDataConfig();
		Optional<EmailDataConfig> fileOptional1 = emaildataconfigrepository.findByPort(port);
		if (fileOptional1.isPresent()) {
			try {
				emaildataconfigEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return this.decrypt(emaildataconfigEntity);
	}

	@Override
	public EmailDataConfigPojo findByPortAndCompanys(String port, String company) {
		logger.info("Starting getEmailDataConfig");
		EmailDataConfig emaildataconfigEntity = new EmailDataConfig();
		Optional<EmailDataConfig> fileOptional1 = emaildataconfigrepository.findByPortAndCompany(port, company);
		if (fileOptional1.isPresent()) {
			try {
				emaildataconfigEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return this.decrypt(emaildataconfigEntity);
	}


	@Override
	public EmailDataConfigPojo findByMailUsername(String mailUsername) {

		logger.info("Starting getEmailDataConfig");
		EmailDataConfig emaildataconfigEntity = new EmailDataConfig();
		Optional<EmailDataConfig> fileOptional1 = emaildataconfigrepository.findByMailUsername(mailUsername);

		if (fileOptional1.isPresent()) {
			try {
				emaildataconfigEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return this.decrypt(emaildataconfigEntity);
	}

	@Override
	public EmailDataConfigPojo findByMailPassword(String mailPassword) {

		logger.info("Starting getEmailDataConfig");
		EmailDataConfig emaildataconfigEntity = new EmailDataConfig();
		Optional<EmailDataConfig> fileOptional1 = emaildataconfigrepository.findByMailPassword(mailPassword);

		if (fileOptional1.isPresent()) {
			try {
				emaildataconfigEntity = fileOptional1.get();
			} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
			}
		}
		return this.decrypt(emaildataconfigEntity);
	}


	private EmailDataConfig decryptUserUpdate(EmailDataConfig emaildataconfig) {
		EmailDataConfig emailData = new EmailDataConfig();

		try {
			if (emaildataconfig != null) {
				if (emaildataconfig.getHost() != null && emaildataconfig.getHost() != "") {
					emailData.setHost(encryptAES.decryptAES(emaildataconfig.getHost()));
				}

				if (emaildataconfig.getPort() != null && emaildataconfig.getPort() != "") {
					emailData.setPort(encryptAES.decryptAES(emaildataconfig.getPort()));
				}

				if (emaildataconfig.getMailpassword() != null && emaildataconfig.getMailpassword() != "") {
					emailData.setMailpassword(encryptAES.decryptAES(emaildataconfig.getMailpassword()));
				}

				if (emaildataconfig.getMailusername() != null && emaildataconfig.getMailusername() != "") {
					emailData.setMailusername(encryptAES.decryptAES(emaildataconfig.getMailusername()));
				}
			}
			return emailData;
		} catch (Exception e) {
			e.printStackTrace();
			return emailData;
		}
	}



	@Override
	public boolean saveEmailDataConfig(EmailDataConfig emaildataconfig) {
		logger.info("Save saveEmailDataConfig");
		try {

			if (emaildataconfig != null && emaildataconfig.getPort() != null && emaildataconfig.getHost() != null){

				EmailDataConfig dataconfig = decryptUserUpdate(emaildataconfig);
				List<EmailDataConfigPojo> config = findByHostContaining(dataconfig.getHost());

				if(config.size() > 0 ){
					config.stream().forEach(emailDataConfigPojo -> deleteEmailDataConfig(emailDataConfigPojo.getIdemailconfig()));
					emaildataconfigrepository.save(this.encript(dataconfig));
				}else{
					emaildataconfigrepository.save(this.encript(dataconfig));
				}
				return true;
			}

			if(emaildataconfig.getHost() == null){
				emaildataconfig.setHost("smtp.gmail.com");
			}
			if(emaildataconfig.getPort() == null){
				emaildataconfig.setPort("25");
			}

			return false;

		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			return false;
		}
	}


	@Override
	public boolean deleteEmailDataConfig(Long id) {
		logger.info("Delete Proyect");
		boolean clave = false;
		try {
			emaildataconfigrepository.deleteById(id);
			clave = true;
		} catch (DataAccessException e) {
			logger.error(" ERROR : " + e);
			clave = false;
		}
		return clave;
	}


	@Override
	public EmailDataConfigPojo findById(Long id) {
		return this.decrypt(emaildataconfigrepository.findById(id).get()) ;
	}


	@Override
	public List<EmailDataConfigPojo> getAllEmailDataConfig() {
		logger.info("Get allProyect");
		List<EmailDataConfigPojo> listaEmailDataConfig = new ArrayList<EmailDataConfigPojo>();
		emaildataconfigrepository.findAll()
				.forEach(emaildataconfig -> listaEmailDataConfig.add(this.decrypt(emaildataconfig)));
		return listaEmailDataConfig;
	}

	@Override
	public List<EmailDataConfigPojo> findByHostContaining(String host) {
		logger.info("Get allProyect");
		List<EmailDataConfigPojo> listaEmailDataConfig = new ArrayList<EmailDataConfigPojo>();
		List<EmailDataConfig> listEmailDataConfig = emaildataconfigrepository.findByHostContaining(host);

		listEmailDataConfig.stream().forEach(emailDataConfig -> listaEmailDataConfig.add(this.decrypt(emailDataConfig)));
		return listaEmailDataConfig;
	}

	@Override
	public List<EmailDataConfigPojo> findByPortContaining(String port) {
		logger.info("Get allProyect");
		List<EmailDataConfigPojo> listaEmailDataConfig = new ArrayList<EmailDataConfigPojo>();
		emaildataconfigrepository.findByPortContaining(port)
				.stream().forEach(emailDataConfig -> listaEmailDataConfig.add(this.decrypt(emailDataConfig)));
		return listaEmailDataConfig;
	}

	@Override
	public List<EmailDataConfigPojo> findByMailUsernameContaining(String mailusername) {
		logger.info("Get allProyect");
		List<EmailDataConfigPojo> listaEmailDataConfig = new ArrayList<EmailDataConfigPojo>();
		emaildataconfigrepository.findByMailUsernameContaining(mailusername)
				.stream().forEach(emailDataConfig -> listaEmailDataConfig.add(this.decrypt(emailDataConfig)));
		return listaEmailDataConfig;
	}

	@Override
	public List<EmailDataConfigPojo> findByMailPasswordContaining(String mailpassword) {
		logger.info("Get allProyect");
		List<EmailDataConfigPojo> listaEmailDataConfig = new ArrayList<EmailDataConfigPojo>();
		emaildataconfigrepository.findByMailPasswordContaining(mailpassword)
				.stream().forEach(emailDataConfig -> listaEmailDataConfig.add(this.decrypt(emailDataConfig)));
		return listaEmailDataConfig;
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
