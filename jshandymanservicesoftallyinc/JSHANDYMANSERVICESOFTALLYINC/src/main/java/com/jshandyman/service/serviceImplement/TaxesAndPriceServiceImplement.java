
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

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.TaxesAndPrice;
import com.jshandyman.service.entitys.User;
import com.jshandyman.service.mapper.TaxesAndPriceMapper;
import com.jshandyman.service.pojo.TaxesAndPricePojo;
import com.jshandyman.service.repository.TaxesAndPriceRepository;
import com.jshandyman.service.service.TaxesAndPriceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TaxesAndPriceServiceImplement implements TaxesAndPriceService {

    protected static final Log logger = LogFactory.getLog(TaxesAndPriceServiceImplement.class);

    @Autowired
    private TaxesAndPriceRepository taxesandpricerepository;

    @Autowired
    private TaxesAndPriceMapper taxesAndPriceMapper;


    @Override
    public TaxesAndPrice findByTaxes(Double taxes) {

        logger.info("Starting getTaxesAndPrice");
        TaxesAndPrice taxesandpriceEntity = new TaxesAndPrice();
        Optional<TaxesAndPrice> fileOptional1 = taxesandpricerepository.findByTaxes(taxes);

        if (fileOptional1.isPresent()) {

            try {
                taxesandpriceEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taxesandpriceEntity;
    }

    @Override
    public TaxesAndPrice findByDiscountCashPayment(Double discountCashPayment) {

        logger.info("Starting getTaxesAndPrice");
        TaxesAndPrice taxesandpriceEntity = new TaxesAndPrice();
        Optional<TaxesAndPrice> fileOptional1 = taxesandpricerepository.findByDiscountCashPayment(discountCashPayment);

        if (fileOptional1.isPresent()) {

            try {
                taxesandpriceEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taxesandpriceEntity;
    }

    @Override
    public TaxesAndPrice findByDiscount(Double discount) {

        logger.info("Starting getTaxesAndPrice");
        TaxesAndPrice taxesandpriceEntity = new TaxesAndPrice();
        Optional<TaxesAndPrice> fileOptional1 = taxesandpricerepository.findByDiscount(discount);

        if (fileOptional1.isPresent()) {

            try {
                taxesandpriceEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return taxesandpriceEntity;
    }


    @Override
    public List<TaxesAndPrice> getAllTaxesAndPrice() {
        logger.info("Get allProyect");
        List<TaxesAndPrice> listaTaxesAndPrice = new ArrayList<TaxesAndPrice>();
        taxesandpricerepository.findAll().forEach(taxesandprice -> listaTaxesAndPrice.add(taxesandprice));
        return listaTaxesAndPrice;
    }


    @Override
    public boolean saveTaxesAndPrice(TaxesAndPrice taxesandprice) {
        logger.info("Save TaxesAndPrice() for a Proyect");
        TaxesAndPricePojo taxesOld = null;

        try {
            taxesOld =  findByDescription(Constant.TAXES, taxesandprice.getCompany());
            if (taxesOld != null){
                this.deleteTaxesAndPrice(taxesOld.getIdtaxesandprice());
            }
            taxesandprice.setDescription(Constant.TAXES);
            taxesandpricerepository.save(taxesandprice);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean deleteTaxesAndPrice(Long id) {
        logger.info("Delete Proyect");
        boolean clave = false;
        try {
            taxesandpricerepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }

    @Override
    public TaxesAndPricePojo findByDescription(String description, String company ) {
        TaxesAndPricePojo t = null;
        try{
            t = taxesAndPriceMapper.entityToPojo(taxesandpricerepository.findByDescriptionAndCompany(description, company ));
            return t;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public TaxesAndPrice findById(Long id) {
        return taxesandpricerepository.findById(id).get();
    }


    @Override
    public List<TaxesAndPrice> findByTaxesContaining(Double taxes) {
        logger.info("Get allProyect");
        List<TaxesAndPrice> listaTaxesAndPrice = new ArrayList<TaxesAndPrice>();
        listaTaxesAndPrice = taxesandpricerepository.findByTaxesContaining(taxes);
        return listaTaxesAndPrice;
    }


    @Override
    public List<TaxesAndPrice> findByDiscountCashPaymentContaining(Double discountcashpayment) {
        logger.info("Get allProyect");
        List<TaxesAndPrice> listaTaxesAndPrice = new ArrayList<TaxesAndPrice>();
        listaTaxesAndPrice = taxesandpricerepository.findByDiscountCashPaymentContaining(discountcashpayment);
        return listaTaxesAndPrice;
    }


    @Override
    public List<TaxesAndPrice> findByDiscountContaining(Double discount) {
        logger.info("Get allProyect");
        List<TaxesAndPrice> listaTaxesAndPrice = new ArrayList<TaxesAndPrice>();
        listaTaxesAndPrice = taxesandpricerepository.findByDiscountContaining(discount);
        return listaTaxesAndPrice;
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
