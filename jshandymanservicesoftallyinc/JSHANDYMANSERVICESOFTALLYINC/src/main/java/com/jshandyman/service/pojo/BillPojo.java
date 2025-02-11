
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


import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BillPojo  {



    private Long idBill;
    private String billType;
    private String billNumber;
    private Double billTotalWichoutTaxes;
    private Double billTotal;
    private Long idSubContractor;
    private String description;
    private List<ProductPojo> productsAndServices = new ArrayList<>();
    private Long idWork;
    private Long idEstimate;
    private Boolean itemDeliteEdit;
    private Long referenceEstimate;

    public BillPojo() {
    }

    public BillPojo(String billType, String billNumber, Double billTotalWichoutTaxes, Double billTotal,
                    Long idSubContractor, String description, List<ProductPojo> productsAndServices,
                    Long idWork, Long idEstimate, Boolean itemDeliteEdit) {
        if(billType !=null)
        this.billType = billType;

        if(billNumber !=null)
        this.billNumber = billNumber;

        if(billTotalWichoutTaxes !=null)
        this.billTotalWichoutTaxes = billTotalWichoutTaxes;

        if(billTotal !=null)
        this.billTotal = billTotal;

        if(idSubContractor !=null)
        this.idSubContractor = idSubContractor;

        if(description !=null)
        this.description = description;

        if(productsAndServices !=null)
        this.productsAndServices = productsAndServices;

        if( idWork!=null)
        this.idWork = idWork;

        if(idEstimate !=null)
        this.idEstimate = idEstimate;

        if(itemDeliteEdit !=null){
            this.itemDeliteEdit = itemDeliteEdit;
        }
    }


    public BillPojo(BillPojo oterBillPojo) {

        if(oterBillPojo != null){
            if(oterBillPojo.getBillType() !=null)
                this.billType = oterBillPojo.getBillType();

            if(oterBillPojo.getBillNumber()  !=null)
                this.billNumber = oterBillPojo.getBillNumber();

            if(oterBillPojo.getBillTotalWichoutTaxes()  !=null)
                this.billTotalWichoutTaxes = oterBillPojo.getBillTotalWichoutTaxes();

            if(oterBillPojo.getBillTotal()  !=null)
                this.billTotal = oterBillPojo.getBillTotal();

            if(oterBillPojo.getIdSubContractor()  !=null)
                this.idSubContractor = oterBillPojo.getIdSubContractor();

            if(oterBillPojo.getDescription()  !=null)
                this.description = oterBillPojo.getDescription();

            if(oterBillPojo.getProductsAndServices()  !=null){
                if(oterBillPojo.getProductsAndServices().size() > 0){
                    this.productsAndServices = this.cloneListProductPojo(oterBillPojo.getProductsAndServices());
                }else {
                    this.productsAndServices = new ArrayList<ProductPojo>();
                }
            }

            if( oterBillPojo.getIdWork() !=null)
                this.idWork = oterBillPojo.getIdWork();


            if(oterBillPojo.getReferenceEstimate() == null){
                this.referenceEstimate = oterBillPojo.getIdEstimate();
            }

//            if(oterBillPojo.getIdEstimate()  !=null){
//                this.idEstimate = oterBillPojo.getIdEstimate();
//            }

            if(oterBillPojo.getItemDeliteEdit() !=null){
                this.itemDeliteEdit = oterBillPojo.getItemDeliteEdit();
            }
        }
    }

    private List<ProductPojo>  cloneListProductPojo(List<ProductPojo> list){
        List<ProductPojo> products = new ArrayList<>();
        list.stream().forEach(productPojo -> products.add(new ProductPojo(productPojo)));
        return products;
    }

    public Long getReferenceEstimate() {
        return referenceEstimate;
    }

    public void setReferenceEstimate(Long referenceEstimate) {
        this.referenceEstimate = referenceEstimate;
    }

    public Boolean getItemDeliteEdit() {
        return itemDeliteEdit;
    }

    public void setItemDeliteEdit(Boolean itemDeliteEdit) {
        this.itemDeliteEdit = itemDeliteEdit;
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Double getBillTotalWichoutTaxes() {
        return billTotalWichoutTaxes;
    }

    public void setBillTotalWichoutTaxes(Double billTotalWichoutTaxes) {
        this.billTotalWichoutTaxes = billTotalWichoutTaxes;
    }

    public Double getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(Double billTotal) {
        this.billTotal = billTotal;
    }

    public Long getIdSubContractor() {
        return idSubContractor;
    }

    public void setIdSubContractor(Long idSubContractor) {
        this.idSubContractor = idSubContractor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductPojo> getProductsAndServices() {
        return productsAndServices;
    }

    public void setProductsAndServices(List<ProductPojo> productsAndServices) {
        this.productsAndServices = productsAndServices;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public Long getIdEstimate() {
        return idEstimate;
    }

    public void setIdEstimate(Long idEstimate) {
        this.idEstimate = idEstimate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillPojo)) return false;
        BillPojo billPojo = (BillPojo) o;
        return itemDeliteEdit == billPojo.itemDeliteEdit && Objects.equals(idBill, billPojo.idBill) && Objects.equals(billType, billPojo.billType) && Objects.equals(billNumber, billPojo.billNumber) && Objects.equals(billTotalWichoutTaxes, billPojo.billTotalWichoutTaxes) && Objects.equals(billTotal, billPojo.billTotal) && Objects.equals(idSubContractor, billPojo.idSubContractor) && Objects.equals(description, billPojo.description) && Objects.equals(productsAndServices, billPojo.productsAndServices) && Objects.equals(idWork, billPojo.idWork) && Objects.equals(idEstimate, billPojo.idEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBill, billType, billNumber, billTotalWichoutTaxes, billTotal, idSubContractor, description, productsAndServices, idWork, idEstimate, itemDeliteEdit);
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

