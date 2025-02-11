
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


import java.util.Date;
import java.util.Objects;


public class PaymentPojo  {


    private Long idPayment;
    private Date payday;
    private String typePayment;
    private Double amountPaind;
    private String billNumberAsociate;
    private String descriptionOfPaind;
    private Long idWork;
    private Long idEstimate;
    private String attachments;

    public PaymentPojo() {
    }


    public PaymentPojo(Date payday, String typePayment, Double amountPaind, String billNumberAsociate, String descriptionOfPaind, Long idWork, Long idEstimate, String attachments) {

        if(payday !=null)
        this.payday = payday;

        if(typePayment !=null)
        this.typePayment = typePayment;

        if(amountPaind !=null)
        this.amountPaind = amountPaind;

        if(billNumberAsociate !=null)
        this.billNumberAsociate = billNumberAsociate;

        if(descriptionOfPaind !=null)
        this.descriptionOfPaind = descriptionOfPaind;

        if(idWork !=null)
        this.idWork = idWork;

        if(idEstimate !=null)
        this.idEstimate = idEstimate;

        if(attachments !=null) {
            this.attachments = attachments;
        }
    }


    public PaymentPojo(PaymentPojo oterPayment) {

        if(oterPayment != null){

            if(oterPayment.getPayday() !=null)
                this.payday = oterPayment.getPayday();

            if(oterPayment.getTypePayment() !=null)
                this.typePayment = oterPayment.getTypePayment();

            if(oterPayment.getAmountPaind() !=null)
                this.amountPaind = oterPayment.getAmountPaind();

            if(oterPayment.getBillNumberAsociate() !=null)
                this.billNumberAsociate = oterPayment.getBillNumberAsociate();

            if(oterPayment.getDescriptionOfPaind() !=null)
                this.descriptionOfPaind = oterPayment.getDescriptionOfPaind();

            if(oterPayment.getIdWork() !=null)
                this.idWork = oterPayment.getIdWork();

//            if(oterPayment.getIdEstimate() !=null)
//                this.idEstimate = oterPayment.getIdEstimate();

            if(oterPayment.getAttachments() !=null) {
                this.attachments = oterPayment.getAttachments();
            }
        }
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
        if (!(o instanceof PaymentPojo)) return false;
        PaymentPojo that = (PaymentPojo) o;
        return Objects.equals(idPayment, that.idPayment) && Objects.equals(payday, that.payday) && Objects.equals(typePayment, that.typePayment) && Objects.equals(amountPaind, that.amountPaind) && Objects.equals(billNumberAsociate, that.billNumberAsociate) && Objects.equals(descriptionOfPaind, that.descriptionOfPaind) && Objects.equals(idWork, that.idWork) && Objects.equals(idEstimate, that.idEstimate) && Objects.equals(attachments, that.attachments);
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

