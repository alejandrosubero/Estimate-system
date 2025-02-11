
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

import com.jshandyman.service.mapper.PaymentMapper;
import com.jshandyman.service.pojo.PaymentPojo;
import com.jshandyman.service.service.PaymentService;
import com.jshandyman.service.repository.PaymentRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.jshandyman.service.entitys.Payment;




@Service
public class PaymentServiceImplement implements PaymentService {

protected static final Log logger = LogFactory.getLog(PaymentServiceImplement.class);

	@Autowired
	private PaymentRepository paymentrepository;

	@Autowired
	private PaymentMapper paymentMapper;


	@Override
	public List<PaymentPojo> findByIdWorkContaining(Long idWork) {
		List<PaymentPojo> lista = new ArrayList<PaymentPojo>();

		paymentrepository.findByIdWorkContaining(idWork)
				.stream().forEach(payment ->
						lista.add(paymentMapper.entityToPojo(payment))
				);
		return lista;
	}


	@Override
		public Payment findByPayday(Date payday){

		logger.info("Starting getPayment");
			Payment paymentEntity = new Payment();
		Optional<Payment> fileOptional1 = paymentrepository.findByPayday(payday);

		if (fileOptional1.isPresent()) { 

				try {
			paymentEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return paymentEntity;	}
		@Override
		public Payment findByTypePayment(String typePayment){

		logger.info("Starting getPayment");
			Payment paymentEntity = new Payment();
		Optional<Payment> fileOptional1 = paymentrepository.findByTypePayment(typePayment);

		if (fileOptional1.isPresent()) { 

				try {
			paymentEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return paymentEntity;	}
		@Override
		public Payment findByAmountPaind(Double amountPaind){

		logger.info("Starting getPayment");
			Payment paymentEntity = new Payment();
		Optional<Payment> fileOptional1 = paymentrepository.findByAmountPaind(amountPaind);

		if (fileOptional1.isPresent()) { 

				try {
			paymentEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return paymentEntity;	}
		@Override
		public Payment findByBillNumberAsociate(String billNumberAsociate){

		logger.info("Starting getPayment");
			Payment paymentEntity = new Payment();
		Optional<Payment> fileOptional1 = paymentrepository.findByBillNumberAsociate(billNumberAsociate);

		if (fileOptional1.isPresent()) { 

				try {
			paymentEntity = fileOptional1.get();
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);

				}
		}
		return paymentEntity;	}




		@Override
		public List<Payment> getAllPayment(){
		logger.info("Get allProyect");
			List<Payment> listaPayment = new ArrayList<Payment>();
				paymentrepository.findAll().forEach(payment -> listaPayment.add(payment));
			return listaPayment;
}


		@Override
		public boolean savePayment(Payment payment){
		logger.info("Save Proyect");


				try {
				paymentrepository.save(payment);
				return true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				return false;
				}
		}


		@Override
		public boolean deletePayment( Long id){
		logger.info("Delete Proyect");
		boolean clave = false;


				try {
				paymentrepository.deleteById(id);
				clave = true;
				} catch (DataAccessException e) {
				logger.error(" ERROR : " + e);
				clave = false;
				}
		return clave;
	}




		@Override
		public Payment findById( Long id){
				return  paymentrepository.findById(id).get();
		}



		@Override
		public List<Payment> findByPaydayContaining(Date payday){
			logger.info("Get allProyect");
 			List<Payment> listaPayment = new ArrayList<Payment>();
			listaPayment = paymentrepository.findByPaydayContaining(payday);
  			return listaPayment;
		}

		@Override
		public List<Payment> findByTypePaymentContaining(String typepayment){
			logger.info("Get allProyect");
 			List<Payment> listaPayment = new ArrayList<Payment>();
			listaPayment = paymentrepository.findByTypePaymentContaining(typepayment);
  			return listaPayment;
		}

		@Override
		public List<Payment> findByAmountPaindContaining(Double amountpaind){
			logger.info("Get allProyect");
 			List<Payment> listaPayment = new ArrayList<Payment>();
			listaPayment = paymentrepository.findByAmountPaindContaining(amountpaind);
  			return listaPayment;
		}

		@Override
		public List<Payment> findByBillNumberAsociateContaining(String billnumberasociate){
			logger.info("Get allProyect");
 			List<Payment> listaPayment = new ArrayList<Payment>();
			listaPayment = paymentrepository.findByBillNumberAsociateContaining(billnumberasociate);
  			return listaPayment;
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
