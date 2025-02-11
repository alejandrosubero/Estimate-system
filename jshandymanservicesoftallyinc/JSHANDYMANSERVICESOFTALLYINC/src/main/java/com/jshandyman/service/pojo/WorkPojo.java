
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



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class WorkPojo {

    private Long idWork;
    private String codeWork;
    private String description;
    private Date dedline;
    private Date starDate;
    private Long daysToDeline;
    private Long daysLate;
    private Double totalCostWorkWithoutTaxes;
    private Double totalCostWork;
    private Double remainingPayable;
    private Double totalAmountPaind;
    private ClientPojo client;
    private List<PaymentPojo> payments = new ArrayList<PaymentPojo>();
    private List<BillPojo> bills = new ArrayList<BillPojo>();
    private List<SubcontractorPojo> subcontractors = new ArrayList<SubcontractorPojo>();
    private List<ServiceHandyManTallyPojo> services = new ArrayList<ServiceHandyManTallyPojo>();
    private Double overhead;
    private Long idEstimate;
    private String title;
    private Date createDay;
    private String owner;
    private boolean active;
    private String status;
    private Date finalDate;
    private Date reopeningDate;
    private String reopeningNote;
    private String notes;

    private String company;
    public WorkPojo() {

    }

    public WorkPojo(String codeWork, String description, Date dedline, Date starDate, Long daysToDeline, Long daysLate,
                    Double totalCostWorkWithoutTaxes, Double totalCostWork, Double remainingPayable, Double totalAmountPaind,
                    ClientPojo client,
                    List<PaymentPojo> payments,
                    List<BillPojo> bills,
                    List<SubcontractorPojo> subcontractors,
                    List<ServiceHandyManTallyPojo> services,
                    Double overhead,
                    Long idEstimate, String title, String owner) {


        if(title != null){
            this.title = title;
        }

        if(codeWork !=null)
        this.codeWork = codeWork;

        if(description !=null)
        this.description = description;

        if(dedline !=null)
        this.dedline = dedline;

        if(starDate !=null)
        this.starDate = starDate;

        if(daysToDeline !=null)
        this.daysToDeline = daysToDeline;

        if(daysLate !=null)
        this.daysLate = daysLate;

        if(totalCostWorkWithoutTaxes !=null)
        this.totalCostWorkWithoutTaxes = totalCostWorkWithoutTaxes;

        if(totalCostWork !=null)
        this.totalCostWork = totalCostWork;

        if(remainingPayable !=null)
        this.remainingPayable = remainingPayable;

        if(totalAmountPaind !=null)
        this.totalAmountPaind = totalAmountPaind;

        if(client !=null)
        this.client = client;

        if(payments !=null)
        this.payments = payments;

        if(bills !=null)
        this.bills = bills;

        if(subcontractors !=null)
        this.subcontractors = subcontractors;

        if(services !=null){
            this.services = services;
        }

        if(overhead !=null)
        this.overhead = overhead;

        if(idEstimate !=null)
        this.idEstimate = idEstimate;

        if(owner != null)
         this.title = owner;

        this.active = true;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public String getCodeWork() {
        return codeWork;
    }

    public void setCodeWork(String codeWork) {
        this.codeWork = codeWork;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDedline() {
        return dedline;
    }

    public void setDedline(Date dedline) {
        this.dedline = dedline;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Long getDaysToDeline() {
        return daysToDeline;
    }

    public void setDaysToDeline(Long daysToDeline) {
        this.daysToDeline = daysToDeline;
    }

    public Long getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(Long daysLate) {
        this.daysLate = daysLate;
    }

    public Double getTotalCostWorkWithoutTaxes() {
        return totalCostWorkWithoutTaxes;
    }

    public void setTotalCostWorkWithoutTaxes(Double totalCostWorkWithoutTaxes) {
        this.totalCostWorkWithoutTaxes = totalCostWorkWithoutTaxes;
    }

    public Double getTotalCostWork() {
        return totalCostWork;
    }

    public void setTotalCostWork(Double totalCostWork) {
        this.totalCostWork = totalCostWork;
    }

    public Double getRemainingPayable() {
        return remainingPayable;
    }

    public void setRemainingPayable(Double remainingPayable) {
        this.remainingPayable = remainingPayable;
    }

    public Double getTotalAmountPaind() {
        return totalAmountPaind;
    }

    public void setTotalAmountPaind(Double totalAmountPaind) {
        this.totalAmountPaind = totalAmountPaind;
    }

    public ClientPojo getClient() {
        return client;
    }

    public void setClient(ClientPojo client) {
        this.client = client;
    }

    public List<PaymentPojo> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentPojo> payments) {
        this.payments = payments;
    }

    public List<BillPojo> getBills() {
        return bills;
    }

    public void setBills(List<BillPojo> bills) {
        this.bills = bills;
    }

    public List<SubcontractorPojo> getSubcontractors() {
        return subcontractors;
    }

    public void setSubcontractors(List<SubcontractorPojo> subcontractors) {
        this.subcontractors = subcontractors;
    }

    public List<ServiceHandyManTallyPojo> getServices() {
        return services;
    }

    public void setServices(List<ServiceHandyManTallyPojo> services) {
        this.services = services;
    }

    public Double getOverhead() {
        return overhead;
    }

    public void setOverhead(Double overhead) {
        this.overhead = overhead;
    }

    public Long getIdEstimate() {
        return idEstimate;
    }

    public void setIdEstimate(Long idEstimate) {
        this.idEstimate = idEstimate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Date getReopeningDate() {
        return reopeningDate;
    }

    public void setReopeningDate(Date reopeningDate) {
        this.reopeningDate = reopeningDate;
    }

    public String getReopeningNote() {
        return reopeningNote;
    }

    public void setReopeningNote(String reopeningNote) {
        this.reopeningNote = reopeningNote;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

