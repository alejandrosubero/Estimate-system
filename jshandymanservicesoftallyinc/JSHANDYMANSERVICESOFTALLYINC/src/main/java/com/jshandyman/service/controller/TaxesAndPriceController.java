
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
import com.jshandyman.service.entitys.TaxesAndPrice;
import com.jshandyman.service.pojo.UserPojo;
import com.jshandyman.service.validation.TaxesAndPriceValidation;
import com.jshandyman.service.mapper.TaxesAndPriceMapper;
import com.jshandyman.service.service.TaxesAndPriceService;
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
import com.jshandyman.service.pojo.TaxesAndPricePojo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("config/taxes/and/price")
public class TaxesAndPriceController {

    @Autowired
    TaxesAndPriceService taxesandpriceService;

    @Autowired
    TaxesAndPriceValidation taxesandpriceValidationService;

    @Autowired
   TaxesAndPriceMapper taxesandpriceMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/Gettaxes/{taxes}")
        private  ResponseEntity<EntityRespone> findByTaxes(@PathVariable("taxes") Double  taxes) {
        Double busca = (Double) taxesandpriceValidationService.validation(taxes);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taxesandpriceService.findByTaxes(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdiscountcashpayment/{discountcashpayment}")
        private  ResponseEntity<EntityRespone> findByDiscountCashPayment(@PathVariable("discountcashpayment") Double  discountcashpayment) {
        Double busca = (Double) taxesandpriceValidationService.validation(discountcashpayment);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taxesandpriceService.findByDiscountCashPayment(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/Getdiscount/{discount}")
        private  ResponseEntity<EntityRespone> findByDiscount(@PathVariable("discount") Double  discount) {
        Double busca = (Double) taxesandpriceValidationService.validation(discount);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taxesandpriceService.findByDiscount(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/Gettaxescontain/{taxes}")
        private ResponseEntity<EntityRespone> findByTaxesContain(@PathVariable("taxes") Double  taxes) {
              Double busca = (Double) taxesandpriceValidationService.validation(taxes);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(taxesandpriceService.findByTaxesContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdiscountcashpaymentcontain/{discountcashpayment}")
        private ResponseEntity<EntityRespone> findByDiscountCashPaymentContain(@PathVariable("discountcashpayment") Double  discountcashpayment) {
              Double busca = (Double) taxesandpriceValidationService.validation(discountcashpayment);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(taxesandpriceService.findByDiscountCashPaymentContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/Getdiscountcontain/{discount}")
        private ResponseEntity<EntityRespone> findByDiscountContain(@PathVariable("discount") Double  discount) {
              Double busca = (Double) taxesandpriceValidationService.validation(discount);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(taxesandpriceService.findByDiscountContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/GetTaxesAndPrice/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(taxesandpriceService.findById(taxesandpriceValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/GetAllTaxesAndPrice")
        private  ResponseEntity<EntityRespone> getAllTaxesAndPrice(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(taxesandpriceService.getAllTaxesAndPrice());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }


//    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes="application/json")
//    private Boolean  saveTaxesAndPrice(@RequestBody TaxesAndPricePojo  taxesandprice, @RequestHeader("company")  String company){
//        taxesandprice.setCompany(company);
//        return taxesandpriceService.saveTaxesAndPrice(taxesandpriceMapper.pojoToEntity(taxesandpriceValidationService.valida(taxesandprice)) );
//    }

    @PostMapping("/save")
    private Boolean  saveTaxesAndPrice(@RequestBody TaxesAndPricePojo  taxesandprice, @RequestHeader("Company")  String company){
        taxesandprice.setCompany(company);
        return taxesandpriceService.saveTaxesAndPrice(taxesandpriceMapper.pojoToEntity(taxesandpriceValidationService.valida(taxesandprice)) );
    }
//
//        @PostMapping("/save")
//        private Boolean  saveTaxesAndPrice(@RequestBody TaxesAndPricePojo  taxesandprice){
//            return taxesandpriceService.saveTaxesAndPrice(taxesandpriceMapper.pojoToEntity(taxesandpriceValidationService.valida(taxesandprice)) ); }



        @DeleteMapping("/deleteTaxesAndPrice/{id}")
            private boolean deleteTaxesAndPrice(@PathVariable("id") String id) {
            return taxesandpriceService.deleteTaxesAndPrice(taxesandpriceValidationService.valida_id(id)); }

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


