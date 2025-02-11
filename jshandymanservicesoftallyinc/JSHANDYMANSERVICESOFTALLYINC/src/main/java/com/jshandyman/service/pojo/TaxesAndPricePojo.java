
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/

package com.jshandyman.service.pojo;


import java.util.Objects;


public class TaxesAndPricePojo {


    private Long idTaxesAndPrice;

    private Double taxes;

    private Double discountCashPayment;

    private Double discount;

    private Double overHead;
    private String description;

    private String company;

    public TaxesAndPricePojo() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOverHead() {
        return overHead;
    }

    public void setOverHead(Double overHead) {
        this.overHead = overHead;
    }

    public Long getIdtaxesandprice() {
        return idTaxesAndPrice;
    }

    public void setIdtaxesandprice(Long idTaxesAndPrice) {
        this.idTaxesAndPrice = idTaxesAndPrice;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getDiscountcashpayment() {
        return discountCashPayment;
    }

    public void setDiscountcashpayment(Double discountCashPayment) {
        this.discountCashPayment = discountCashPayment;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxesAndPricePojo that = (TaxesAndPricePojo) o;
        return Objects.equals(idTaxesAndPrice, that.idTaxesAndPrice) && Objects.equals(taxes, that.taxes) && Objects.equals(discountCashPayment, that.discountCashPayment) && Objects.equals(discount, that.discount) && Objects.equals(overHead, that.overHead) && Objects.equals(description, that.description) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTaxesAndPrice, taxes, discountCashPayment, discount, overHead, description, company);
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

