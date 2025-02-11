
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/

package com.jshandyman.service.entitys;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "bill")
@Audited
public class Bill extends Auditable {

  

    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idBill", updatable = true, nullable = false, length = 25)
    private Long idBill;

    @Column(name = "billType", updatable = true, nullable = true, length = 200)
    private String billType;

    @Column(name = "billNumber", updatable = true, nullable = true, length = 200)
    private String billNumber;

    @Column(name = "billTotalWichoutTaxes", updatable = true, nullable = true, length = 200)
    private Double billTotalWichoutTaxes;

    @Column(name = "billTotal", updatable = true, nullable = true, length = 200)
    private Double billTotal;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "bill_id", referencedColumnName = "idBill")
    private List<Product> productsAndServices = new ArrayList<>();

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "work_id")
    private Long idWork;

    @Column(name = "SubContractor_id")
    private Long idSubContractor;

    @Column(name = "estimate_id")
    private Long idEstimate;

    @Column(name = "itemDeliteEdit", updatable = true, nullable = true, length = 10)
    private boolean itemDeliteEdit;

    @Column(name = "referenceEstimate")
    private Long referenceEstimate;

    public Bill() {
    }

    public Long getReferenceEstimate() {
        return referenceEstimate;
    }

    public void setReferenceEstimate(Long referenceEstimate) {
        this.referenceEstimate = referenceEstimate;
    }

    public boolean isItemDeliteEdit() {
        return itemDeliteEdit;
    }

    public void setItemDeliteEdit(boolean itemDeliteEdit) {
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

    public List<Product> getProductsAndServices() {
        return productsAndServices;
    }

    public void setProductsAndServices(List<Product> productsAndServices) {
        this.productsAndServices = productsAndServices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public Long getIdSubContractor() {
        return idSubContractor;
    }

    public void setIdSubContractor(Long idSubContractor) {
        this.idSubContractor = idSubContractor;
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
        if (!(o instanceof Bill)) return false;
        if (!super.equals(o)) return false;
        Bill bill = (Bill) o;
        return itemDeliteEdit == bill.itemDeliteEdit && Objects.equals(idBill, bill.idBill) && Objects.equals(billType, bill.billType) && Objects.equals(billNumber, bill.billNumber) && Objects.equals(billTotalWichoutTaxes, bill.billTotalWichoutTaxes) && Objects.equals(billTotal, bill.billTotal) && Objects.equals(productsAndServices, bill.productsAndServices) && Objects.equals(description, bill.description) && Objects.equals(idWork, bill.idWork) && Objects.equals(idSubContractor, bill.idSubContractor) && Objects.equals(idEstimate, bill.idEstimate) && Objects.equals(referenceEstimate, bill.referenceEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idBill, billType, billNumber, billTotalWichoutTaxes, billTotal, productsAndServices, description, idWork, idSubContractor, idEstimate, itemDeliteEdit, referenceEstimate);
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

