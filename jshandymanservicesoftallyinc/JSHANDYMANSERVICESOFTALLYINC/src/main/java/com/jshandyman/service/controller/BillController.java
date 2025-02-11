
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
@user Jorge Sepulveda
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.controller;

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.mapper.BillMapper;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.mapper.ProductMapper;
import com.jshandyman.service.pojo.BillPojo;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.ProductPojo;
import com.jshandyman.service.service.BillService;
import com.jshandyman.service.validation.BillValidation;
import com.jshandyman.service.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/bill")
public class BillController {

    @Autowired
    BillService billService;

    @Autowired
    BillValidation billValidationService;

    @Autowired
    BillMapper billMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;

    @Autowired
    ProductValidation productValidationService;

    @Autowired
    ProductMapper productMapper;


    @GetMapping("/type/{billtype}")
    private ResponseEntity<EntityRespone> findByBillType(@PathVariable("billtype") String billtype) {
        String busca = (String) billValidationService.validation(billtype);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(billService.findByBillType(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/billnumber/{billnumber}")
    private ResponseEntity<EntityRespone> findByBillNumber(@PathVariable("billnumber") String billnumber) {
        String busca = (String) billValidationService.validation(billnumber);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(billService.findByBillNumber(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/totalwichouttaxes/{billtotalwichouttaxes}")
    private ResponseEntity<EntityRespone> findByBillTotalWichoutTaxes(@PathVariable("billtotalwichouttaxes") Double billtotalwichouttaxes) {
        Double busca = (Double) billValidationService.validation(billtotalwichouttaxes);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(billService.findByBillTotalWichoutTaxes(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/total/{billtotal}")
    private ResponseEntity<EntityRespone> findByBillTotal(@PathVariable("billtotal") Double billtotal) {
        Double busca = (Double) billValidationService.validation(billtotal);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(billService.findByBillTotal(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/type/contain/{billtype}")
    private ResponseEntity<EntityRespone> findByBillTypeContain(@PathVariable("billtype") String billtype) {
        String busca = (String) billValidationService.validation(billtype);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(billService.findByBillTypeContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/billnumber/contain/{billnumber}")
    private ResponseEntity<EntityRespone> findByBillNumberContain(@PathVariable("billnumber") String billnumber) {
        Double busca = (Double) billValidationService.validation(billnumber);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(billService.findByBillNumberContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/totalwichouptaxes/contain/{billtotalwichouttaxes}")
    private ResponseEntity<EntityRespone> findByBillTotalWichoutTaxesContain(@PathVariable("billtotalwichouttaxes") Double billtotalwichouttaxes) {
        Double busca = (Double) billValidationService.validation(billtotalwichouttaxes);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(billService.findByBillTotalWichoutTaxesContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/total/contain/{billtotal}")
    private ResponseEntity<EntityRespone> findByBillTotalContain(@PathVariable("billtotal") Double billtotal) {
        Double busca = (Double) billValidationService.validation(billtotal);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(billService.findByBillTotalContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(billService.findById(billValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/All")
    private ResponseEntity<EntityRespone> getAllBill() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(billService.getAllBill());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/save")
    private Boolean saveBill(@RequestBody BillPojo bill) {
        return billService.saveBill(billMapper.pojoToEntity(billValidationService.valida(bill)));
    }


    @DeleteMapping("/delete/id/{id}")
    private boolean deleteBill(@PathVariable("id") String id) {
        return billService.deleteBill(billValidationService.valida_id(id));
    }


    @PostMapping("/productsAndServices/contain/")
    private List<Bill> findByProduct(@RequestBody ProductPojo product) {
        return billService.findByProductContaining(productMapper.pojoToEntity(productValidationService.valida(product)));
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


