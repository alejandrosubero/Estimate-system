
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

import com.jshandyman.service.mapper.*;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.service.WorkService;
import com.jshandyman.service.validation.BillValidation;
import com.jshandyman.service.validation.ClientValidation;
import com.jshandyman.service.validation.PaymentValidation;
import com.jshandyman.service.validation.WorkValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkValidation workValidationService;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private ClientValidation clientValidationService;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PaymentValidation paymentValidationService;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private BillValidation billValidationService;

    @Autowired
    private BillMapper billMapper;


    @PostMapping("/save")
    private Boolean saveWork(@RequestBody WorkPojo work, @RequestHeader("Company")  String company) {
        if(work.getCompany() != null || work.getCompany().equals("")){
            work.setCompany(company);
        }
        boolean res = workService.saveWork(workMapper.pojoToEntity(workValidationService.valida(work)));
        return res;
    }

    @PostMapping("/new/save/update")
    private WorkPojo saveUpdateWork(@RequestBody WorkPojo work, @RequestHeader("Company")  String company) {
        if(work.getCompany() != null || work.getCompany().equals("")){
            work.setCompany(company);
        }
        return workService.saveUpdateWork(workMapper.pojoToEntity(workValidationService.valida(work)));
    }

    @PostMapping("/new/save")
    private WorkPojo saveNewWork(@RequestBody WorkPojo work, @RequestHeader("Company")  String company) {
        if(work.getCompany() != null || work.getCompany().equals("")){
            work.setCompany(company);
        }
        return workService.saveNewWork(workMapper.pojoToEntity(workValidationService.valida(work)));
    }

    @GetMapping("/All/list")
    private ResponseEntity<EntityRespone> getAllList(@RequestHeader("Company")  String company) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.getWorkListTablet(company));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/work/id/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findById(workValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/estimate/{idEstimate}")
    private ResponseEntity<EntityRespone> findByIdEstimate(@PathVariable("idEstimate") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByIdEstimate(workValidationService.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/All")
    private ResponseEntity<EntityRespone> getAllWork() {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.getAllWork());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/delete/id/{id}")
    private boolean deleteWork(@PathVariable("id") String id) {
        return workService.deleteWork(workValidationService.valida_id(id));
    }

    @GetMapping("/delete/logical/{id}")
    private boolean deleteLogical(@PathVariable("id") String id) {
        return workService.deleteWorkLogic(workValidationService.valida_id(id));
    }

    @GetMapping("/pament/all/{idwork}")
    private ResponseEntity<EntityRespone> allPamentToWork(@PathVariable("idwork") Long idwork) {
        Long busca = (Long) workValidationService.validation(idwork);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.getAllPamentToWork(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/all/{codework}")
    private ResponseEntity<EntityRespone> AllProductInWork(@PathVariable("codework") String codework) {
        String busca = (String) workValidationService.validation(codework);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.AllProductInWork(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/codework/{codework}")
    private ResponseEntity<EntityRespone> findByCodeWork(@PathVariable("codework") String codework) {
        String busca = (String) workValidationService.validation(codework);
        try {
            EntityRespone entityRespone =  mapperEntityRespone.setEntityTobj(workService.findByCodeWork(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/description/{description}")
    private ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String description) {
        String busca = (String) workValidationService.validation(description);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByDescription(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/dedline/{dedline}")
    private ResponseEntity<EntityRespone> findByDedline(@PathVariable("dedline") Date dedline) {
        Date busca = (Date) workValidationService.validation(dedline);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByDedline(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stardate/{stardate}")
    private ResponseEntity<EntityRespone> findByStarDate(@PathVariable("stardate") Date stardate) {
        Date busca = (Date) workValidationService.validation(stardate);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByStarDate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/daystodeline/{daystodeline}")
    private ResponseEntity<EntityRespone> findByDaysToDeline(@PathVariable("daystodeline") Long daystodeline) {
        Long busca = (Long) workValidationService.validation(daystodeline);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByDaysToDeline(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/daystodeline/contain/{daystodeline}")
    private ResponseEntity<EntityRespone> findByDaysToDelineContain(@PathVariable("daystodeline") Long daystodeline) {
        Long busca = (Long) workValidationService.validation(daystodeline);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByDaysToDelineContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/dayslate/{dayslate}")
    private ResponseEntity<EntityRespone> findByDaysLate(@PathVariable("dayslate") Long dayslate) {
        Long busca = (Long) workValidationService.validation(dayslate);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByDaysLate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/totalcostwork/withouttaxes/{totalcostworkwithouttaxes}")
    private ResponseEntity<EntityRespone> findByTotalCostWorkWithoutTaxes(@PathVariable("totalcostworkwithouttaxes") Double totalcostworkwithouttaxes) {
        Double busca = (Double) workValidationService.validation(totalcostworkwithouttaxes);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByTotalCostWorkWithoutTaxes(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/totalcostwork/{totalcostwork}")
    private ResponseEntity<EntityRespone> findByTotalCostWork(@PathVariable("totalcostwork") Double totalcostwork) {
        Double busca = (Double) workValidationService.validation(totalcostwork);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByTotalCostWork(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/remainingpayable/{remainingpayable}")
    private ResponseEntity<EntityRespone> findByRemainingPayable(@PathVariable("remainingpayable") Double remainingpayable) {
        Double busca = (Double) workValidationService.validation(remainingpayable);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(workService.findByRemainingPayable(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/codework/contain/{codework}")
    private ResponseEntity<EntityRespone> findByCodeWorkContain(@PathVariable("codework") String codework) {
        String busca = (String) workValidationService.validation(codework);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByCodeWorkContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/description/contain/{description}")
    private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String description) {
        String busca = (String) workValidationService.validation(description);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByDescriptionContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/dedline/contain/{dedline}")
    private ResponseEntity<EntityRespone> findByDedlineContain(@PathVariable("dedline") Date dedline) {
        Date busca = (Date) workValidationService.validation(dedline);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByDedlineContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/stardate/contain/{stardate}")
    private ResponseEntity<EntityRespone> findByStarDateContain(@PathVariable("stardate") Date stardate) {
        Date busca = (Date) workValidationService.validation(stardate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByStarDateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/dayslate/contain/{dayslate}")
    private ResponseEntity<EntityRespone> findByDaysLateContain(@PathVariable("dayslate") Long dayslate) {
        Long busca = (Long) workValidationService.validation(dayslate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByDaysLateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/totalcostwork/withouttaxes/contain/{totalcostworkwithouttaxes}")
    private ResponseEntity<EntityRespone> findByTotalCostWorkWithoutTaxesContain(@PathVariable("totalcostworkwithouttaxes") Double totalcostworkwithouttaxes) {
        Double busca = (Double) workValidationService.validation(totalcostworkwithouttaxes);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByTotalCostWorkWithoutTaxesContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/totalcostwork/contain/{totalcostwork}")
    private ResponseEntity<EntityRespone> findByTotalCostWorkContain(@PathVariable("totalcostwork") Double totalcostwork) {
        Double busca = (Double) workValidationService.validation(totalcostwork);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByTotalCostWorkContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/remainingpayable/contain/{remainingpayable}")
    private ResponseEntity<EntityRespone> findByRemainingPayableContain(@PathVariable("remainingpayable") Double remainingpayable) {
        Double busca = (Double) workValidationService.validation(remainingpayable);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByRemainingPayableContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @PostMapping("/payments/contain/")
    private List<WorkPojo> findByPayment(@RequestBody PaymentPojo payment) {
        return workService.findByPaymentContaining(paymentMapper.pojoToEntity(paymentValidationService.valida(payment)));
    }


    @PostMapping("/bills/contain/")
    private List<WorkPojo> findByBill(@RequestBody BillPojo bill) {
        return workService.findByBillContaining(billMapper.pojoToEntity(billValidationService.valida(bill)));
    }


    @PostMapping("/find/client")
    private ResponseEntity<EntityRespone> findRelacionClient(@RequestBody ClientPojo client) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(workService.findByRelacionClient(clientMapper.pojoToEntity(clientValidationService.valida(client))));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
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


