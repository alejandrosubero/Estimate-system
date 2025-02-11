
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

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.entitys.Product;
import com.jshandyman.service.repository.BillRepository;
import com.jshandyman.service.service.BillService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BillServiceImplement implements BillService {

    protected static final Log logger = LogFactory.getLog(BillServiceImplement.class);
    @Autowired
    private BillRepository billrepository;

    @Override
    public Bill findByBillType(String billType) {
        logger.info("Starting getBill");
        Bill billEntity = new Bill();
        Optional<Bill> fileOptional1 = billrepository.findByBillType(billType);
        if (fileOptional1.isPresent()) {
            try {
                billEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return billEntity;
    }

    @Override
    public Bill findByBillNumber(String billNumber) {

        logger.info("Starting getBill");
        Bill billEntity = new Bill();
        Optional<Bill> fileOptional1 = billrepository.findByBillNumber(billNumber);

        if (fileOptional1.isPresent()) {
            try {
                billEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return billEntity;
    }

    @Override
    public Bill findByBillTotalWichoutTaxes(Double billTotalWichoutTaxes) {
        logger.info("Starting getBill");
        Bill billEntity = new Bill();
        Optional<Bill> fileOptional1 = billrepository.findByBillTotalWichoutTaxes(billTotalWichoutTaxes);

        if (fileOptional1.isPresent()) {
            try {
                billEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return billEntity;
    }


    @Override
    public Bill findByBillTotal(Double billTotal) {

        logger.info("Starting getBill");
        Bill billEntity = new Bill();
        Optional<Bill> fileOptional1 = billrepository.findByBillTotal(billTotal);
        if (fileOptional1.isPresent()) {
            try {
                billEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return billEntity;
    }


    @Override
    public List<Bill> getAllBill() {
        logger.info("Get allProyect");
        List<Bill> listaBill = new ArrayList<Bill>();
        billrepository.findAll().forEach(bill -> listaBill.add(bill));
        return listaBill;
    }


    @Override
    public boolean saveBill(Bill bill) {
        logger.info("Save Proyect");
        try {
            billrepository.save(bill);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean deleteBill(Long id) {
        logger.info("Delete Proyect");
        boolean clave = false;
        try {
            billrepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public Bill findById(Long id) {
        return billrepository.findById(id).get();
    }


    @Override
    public List<Bill> findByBillTypeContaining(String billtype) {
        logger.info("Get allProyect");
        List<Bill> listaBill = new ArrayList<Bill>();
        listaBill = billrepository.findByBillTypeContaining(billtype);
        return listaBill;
    }

    @Override
    public List<Bill> findByBillNumberContaining(Double billnumber) {
        logger.info("Get allProyect");
        List<Bill> listaBill = new ArrayList<Bill>();
        listaBill = billrepository.findByBillNumberContaining(billnumber);
        return listaBill;
    }

    @Override
    public List<Bill> findByBillTotalWichoutTaxesContaining(Double billtotalwichouttaxes) {
        logger.info("Get allProyect");
        List<Bill> listaBill = new ArrayList<Bill>();
        listaBill = billrepository.findByBillTotalWichoutTaxesContaining(billtotalwichouttaxes);
        return listaBill;
    }

    @Override
    public List<Bill> findByBillTotalContaining(Double billtotal) {
        logger.info("Get allProyect");
        List<Bill> listaBill = new ArrayList<Bill>();
        listaBill = billrepository.findByBillTotalContaining(billtotal);
        return listaBill;
    }


    @Override
    public List<Bill> findByProductContaining(Product productsAndServices) {
        logger.info("metodo: metodContainingRelacion NEW ");
        logger.info("Get allProyect");
        List<Bill> listaBill = new ArrayList<Bill>();
        for (Bill bill : this.getAllBill()) {
            for (Product productsAndServicesx : bill.getProductsAndServices()) {
                if (productsAndServicesx.equals(productsAndServices)) {
                    listaBill.add(bill);
                }
            }
        }
        return listaBill;
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
