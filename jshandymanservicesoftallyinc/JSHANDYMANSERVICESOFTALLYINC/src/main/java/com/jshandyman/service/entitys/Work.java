
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
import java.util.Date;
import java.util.List;
import java.util.Objects;

// TODO: FALTA LA IMPLEMENTACION DEL METODO DE SEARCH PARA LA BUSQUEDA EN TODOS LOS PUNTOS DESEADOS

@Entity
@Table(name = "work")
@Audited
public class Work extends Auditable  {

   

    @Id
    @GeneratedValue(generator = "sequence_work_id_generator")
    @SequenceGenerator(name = "sequence_work_id_generator", initialValue = 1, allocationSize = 10000)
    @Column(name = "idWork", updatable = true, nullable = false, length = 25)
    private Long idWork;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @Column(name = "codeWork", updatable = true, nullable = true, length = 200)
    private String codeWork;

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "dedline", updatable = true, nullable = true, length = 200)
    private Date dedline;

    @Column(name = "createDay", updatable = true, nullable = true)
    private Date createDay;

    @Column(name = "owner", updatable = true, nullable = true, length = 200)
    private String owner;

    @Column(name = "StarDate", updatable = true, nullable = true, length = 200)
    private Date starDate;

    @Column(name = "daysToDeline", updatable = true, nullable = true, length = 200)
    private Long daysToDeline;

    @Column(name = "daysLate", updatable = true, nullable = true, length = 200)
    private Long daysLate;

    @Column(name = "totalCostWorkWithoutTaxes", updatable = true, nullable = true, length = 200)
    private Double totalCostWorkWithoutTaxes;

    @Column(name = "totalCostWork", updatable = true, nullable = true, length = 200)
    private Double totalCostWork;

    @Column(name = "remainingPayable", updatable = true, nullable = true, length = 200)
    private Double remainingPayable;

    @Column(name = "totalAmountPaind", updatable = true, nullable = true, length = 200)
    private Double totalAmountPaind;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "work_id", referencedColumnName = "idWork")
    private List<Payment> payments = new ArrayList<Payment>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "work_id", referencedColumnName = "idWork")
    private List<Bill> bills = new ArrayList<Bill>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "work_id", referencedColumnName = "idWork")
    private List<Subcontractor> subcontractors = new ArrayList<Subcontractor>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "work_id", referencedColumnName = "idWork")
    private List<ServiceHandyManTally> services = new ArrayList<ServiceHandyManTally>();

    @Column(name = "overhead", updatable = true, nullable = true, length = 200)
    private Double overhead;

    @Column(name = "id_estimate", updatable = true, nullable = true, length = 200)
    private Long idEstimate;

    @Column(name = "active", updatable = true, nullable = true, length = 200)
    private Boolean active;

    @Column(name = "status", updatable = true, nullable = true, length = 200)
    private String status;

    @Column(name = "finalDate", updatable = true, nullable = true, length = 200)
    private Date finalDate;

    @Column(name = "reopeningDate", updatable = true, nullable = true, length = 200)
    private Date reopeningDate;

    @Column(name = "reopeningNote", updatable = true, nullable = true, length = 2000)
    private String reopeningNote;

    @Column(name = "notes", updatable = true, nullable = true, length = 2000)
    private String notes;

    @Column(name = "company", updatable = true, nullable = true, length = 500)
    private String company;


    public Work() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Subcontractor> getSubcontractors() {
        return subcontractors;
    }

    public void setSubcontractors(List<Subcontractor> subcontractors) {
        this.subcontractors = subcontractors;
    }

    public List<ServiceHandyManTally> getServices() {
        return services;
    }

    public void setServices(List<ServiceHandyManTally> services) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Work)) return false;
        if (!super.equals(o)) return false;
        Work work = (Work) o;
        return Objects.equals(idWork, work.idWork) && Objects.equals(title, work.title) && Objects.equals(codeWork, work.codeWork) && Objects.equals(description, work.description) && Objects.equals(dedline, work.dedline) && Objects.equals(createDay, work.createDay) && Objects.equals(owner, work.owner) && Objects.equals(starDate, work.starDate) && Objects.equals(daysToDeline, work.daysToDeline) && Objects.equals(daysLate, work.daysLate) && Objects.equals(totalCostWorkWithoutTaxes, work.totalCostWorkWithoutTaxes) && Objects.equals(totalCostWork, work.totalCostWork) && Objects.equals(remainingPayable, work.remainingPayable) && Objects.equals(totalAmountPaind, work.totalAmountPaind) && Objects.equals(client, work.client) && Objects.equals(payments, work.payments) && Objects.equals(bills, work.bills) && Objects.equals(subcontractors, work.subcontractors) && Objects.equals(services, work.services) && Objects.equals(overhead, work.overhead) && Objects.equals(idEstimate, work.idEstimate) && Objects.equals(active, work.active) && Objects.equals(status, work.status) && Objects.equals(finalDate, work.finalDate) && Objects.equals(reopeningDate, work.reopeningDate) && Objects.equals(reopeningNote, work.reopeningNote) && Objects.equals(notes, work.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idWork, title, codeWork, description, dedline, createDay, owner, starDate, daysToDeline, daysLate, totalCostWorkWithoutTaxes, totalCostWork, remainingPayable, totalAmountPaind, client, payments, bills, subcontractors, services, overhead, idEstimate, active, status, finalDate, reopeningDate, reopeningNote, notes);
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

