
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/



package com.jshandyman.service.serviceImplement ;

import com.jshandyman.service.service.PhoneClientService;
import com.jshandyman.service.repository.PhoneClientRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.jshandyman.service.entitys.PhoneClient;




@Service
public class PhoneClientServiceImplement implements PhoneClientService {

protected static final Log logger = LogFactory.getLog(PhoneClientServiceImplement.class);
@Autowired
private PhoneClientRepository phoneclientrepository;

		@Override
		public PhoneClient findByNumber(String number){

		logger.info("Starting getPhoneClient");
			PhoneClient phoneclientEntity = new PhoneClient();
		Optional<PhoneClient> fileOptional1 = phoneclientrepository.findByNumber(number);

		if (fileOptional1.isPresent()) { 

				try {
			phoneclientEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return phoneclientEntity;	}
		@Override
		public PhoneClient findByCodeClient(String codeClient){

		logger.info("Starting getPhoneClient");
			PhoneClient phoneclientEntity = new PhoneClient();
		Optional<PhoneClient> fileOptional1 = phoneclientrepository.findByCodeClient(codeClient);

		if (fileOptional1.isPresent()) { 

				try {
			phoneclientEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return phoneclientEntity;	}




		@Override
		public List<PhoneClient> getAllPhoneClient(){
		logger.info("Get allProyect");
			List<PhoneClient> listaPhoneClient = new ArrayList<PhoneClient>();
				phoneclientrepository.findAll().forEach(phoneclient -> listaPhoneClient.add(phoneclient));
			return listaPhoneClient;
}


		@Override
		public boolean savePhoneClient(PhoneClient phoneclient){
		logger.info("Save Proyect");


				try {
				phoneclientrepository.save(phoneclient);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deletePhoneClient( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				phoneclientrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}




		@Override
		public PhoneClient findById( Long id){
				return  phoneclientrepository.findById(id).get();
		}



		@Override
		public List<PhoneClient> findByNumberContaining(String number){
			logger.info("Get allProyect");
 			List<PhoneClient> listaPhoneClient = new ArrayList<PhoneClient>();
			listaPhoneClient = phoneclientrepository.findByNumberContaining(number);
  			return listaPhoneClient;
		}

		@Override
		public List<PhoneClient> findByCodeClientContaining(String codeclient){
			logger.info("Get allProyect");
 			List<PhoneClient> listaPhoneClient = new ArrayList<PhoneClient>();
			listaPhoneClient = phoneclientrepository.findByCodeClientContaining(codeclient);
  			return listaPhoneClient;
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
