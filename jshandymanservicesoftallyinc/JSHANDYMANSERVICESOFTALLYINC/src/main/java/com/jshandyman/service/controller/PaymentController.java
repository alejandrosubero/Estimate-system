
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
import com.jshandyman.service.entitys.Payment;
import com.jshandyman.service.validation.PaymentValidation;
import com.jshandyman.service.mapper.PaymentMapper;
import com.jshandyman.service.service.PaymentService;
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
import com.jshandyman.service.pojo.PaymentPojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentValidation paymentValidationService;

    @Autowired
   PaymentMapper paymentMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Getpayday/{payday}")
        private  ResponseEntity<EntityRespone> findByPayday(@PathVariable("payday") Date  payday) {
        Date busca = (Date) paymentValidationService.validation(payday);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(paymentService.findByPayday(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Gettypepayment/{typepayment}")
        private  ResponseEntity<EntityRespone> findByTypePayment(@PathVariable("typepayment") String  typepayment) {
        String busca = (String) paymentValidationService.validation(typepayment);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(paymentService.findByTypePayment(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getamountpaind/{amountpaind}")
        private  ResponseEntity<EntityRespone> findByAmountPaind(@PathVariable("amountpaind") Double  amountpaind) {
        Double busca = (Double) paymentValidationService.validation(amountpaind);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(paymentService.findByAmountPaind(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getbillnumberasociate/{billnumberasociate}")
        private  ResponseEntity<EntityRespone> findByBillNumberAsociate(@PathVariable("billnumberasociate") String  billnumberasociate) {
        String busca = (String) paymentValidationService.validation(billnumberasociate);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(paymentService.findByBillNumberAsociate(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Getpaydaycontain/{payday}")
        private ResponseEntity<EntityRespone> findByPaydayContain(@PathVariable("payday") Date  payday) {
              Date busca = (Date) paymentValidationService.validation(payday);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(paymentService.findByPaydayContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Gettypepaymentcontain/{typepayment}")
        private ResponseEntity<EntityRespone> findByTypePaymentContain(@PathVariable("typepayment") String  typepayment) {
              String busca = (String) paymentValidationService.validation(typepayment);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(paymentService.findByTypePaymentContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getamountpaindcontain/{amountpaind}")
        private ResponseEntity<EntityRespone> findByAmountPaindContain(@PathVariable("amountpaind") Double  amountpaind) {
              Double busca = (Double) paymentValidationService.validation(amountpaind);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(paymentService.findByAmountPaindContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getbillnumberasociatecontain/{billnumberasociate}")
        private ResponseEntity<EntityRespone> findByBillNumberAsociateContain(@PathVariable("billnumberasociate") String  billnumberasociate) {
              String busca = (String) paymentValidationService.validation(billnumberasociate);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(paymentService.findByBillNumberAsociateContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetPayment/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(paymentService.findById(paymentValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllPayment")
        private  ResponseEntity<EntityRespone> getAllPayment(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(paymentService.getAllPayment());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  savePayment(@RequestBody PaymentPojo  payment){ 
            return paymentService.savePayment(paymentMapper.pojoToEntity(paymentValidationService.valida(payment)) ); }





        @DeleteMapping("/deletePayment/{id}")
            private boolean deletePayment(@PathVariable("id") String id) {
            return paymentService.deletePayment(paymentValidationService.valida_id(id)); }

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


