/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.validation;

import com.jshandyman.service.pojo.TaxesAndPricePojo;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class TaxesAndPriceValidation {

    public TaxesAndPricePojo valida(TaxesAndPricePojo taxesandprice) {
        TaxesAndPricePojo pojo = null;
        try {
            if (taxesandprice != null) {
                if (taxesandprice.getTaxes() != null && taxesandprice.getDiscountcashpayment() != null &&
                    taxesandprice.getDiscount() != null) {
                    pojo = taxesandprice;
                }
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }

    // remplace de name of variable for you proyecte
    public Long valida_id(String poder) {
        Long id_Delete = 0l;
        try {
            if (poder != null) {
                if (poder.length() > 0 && !Pattern.matches("[a-zA-Z]+", poder)) {
                    id_Delete = Long.parseLong(poder);
                }
            }
            return id_Delete;
        } catch (Exception e) {
            e.printStackTrace();
            return id_Delete;
        }
    }

    public <T> Object validation(T t) {
        T elemento = null;
        try {
            if (t != null) {
                elemento = t;
            }
            return elemento;
        } catch (Exception e) {
            e.printStackTrace();
            return elemento;
        }
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


