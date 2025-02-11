
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.repository;

import java.util.List;import java.util.Date;

import java.util.Optional;

import com.jshandyman.service.entitys.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.jshandyman.service.entitys.Payment;

public interface PaymentRepository extends CrudRepository< Payment, Long> {
 
		public Optional<Payment> findByPayday(Date payday);
		public List<Payment> findByPaydayContaining(Date payday);
		public Optional<Payment> findByTypePayment(String typePayment);
		public List<Payment> findByTypePaymentContaining(String typePayment);
		public Optional<Payment> findByAmountPaind(Double amountPaind);
		public List<Payment> findByAmountPaindContaining(Double amountPaind);
		public Optional<Payment> findByBillNumberAsociate(String billNumberAsociate);
		public List<Payment> findByBillNumberAsociateContaining(String billNumberAsociate);
		public List<Payment> findByIdWorkContaining(Long idWork);

//	@Query(value = "SELECT p FROM Client p WHERE CONCAT_WS( p.codeClient, ' ', p.name, ' ', p.address) LIKE %?1%")
//	public List<Client> finBySearch(String keyword);

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


