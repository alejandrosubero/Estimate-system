
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

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "payment")
@Audited
public class Payment extends Auditable  {


    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idPayment", updatable = true, nullable = false, length = 25)
    private Long idPayment;


    @Column(name = "payday", updatable = true, nullable = true, length = 200)
    private Date payday;


    @Column(name = "typePayment", updatable = true, nullable = true, length = 200)
    private String typePayment;


    @Column(name = "amountPaind", updatable = true, nullable = true, length = 200)
    private Double amountPaind;


    @Column(name = "billNumberAsociate", updatable = true, nullable = true, length = 200)
    private String billNumberAsociate;

    @Column(name = "descriptionOfPaind", updatable = true, nullable = true, length =1000)
    private String descriptionOfPaind;

    @Column(name = "work_id")
    private Long idWork;

    @Column(name = "estimate_id")
    private Long idEstimate;

    @Lob
    @Column(name = "attachments", updatable = true, nullable = true)
    private String attachments;


    public Payment() {
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public Double getAmountPaind() {
        return amountPaind;
    }

    public void setAmountPaind(Double amountPaind) {
        this.amountPaind = amountPaind;
    }

    public String getBillNumberAsociate() {
        return billNumberAsociate;
    }

    public void setBillNumberAsociate(String billNumberAsociate) {
        this.billNumberAsociate = billNumberAsociate;
    }

    public String getDescriptionOfPaind() {
        return descriptionOfPaind;
    }

    public void setDescriptionOfPaind(String descriptionOfPaind) {
        this.descriptionOfPaind = descriptionOfPaind;
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

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(idPayment, payment.idPayment) && Objects.equals(payday, payment.payday) && Objects.equals(typePayment, payment.typePayment) && Objects.equals(amountPaind, payment.amountPaind) && Objects.equals(billNumberAsociate, payment.billNumberAsociate) && Objects.equals(descriptionOfPaind, payment.descriptionOfPaind) && Objects.equals(idWork, payment.idWork) && Objects.equals(idEstimate, payment.idEstimate) && Objects.equals(attachments, payment.attachments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPayment, payday, typePayment, amountPaind, billNumberAsociate, descriptionOfPaind, idWork, idEstimate, attachments);
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

