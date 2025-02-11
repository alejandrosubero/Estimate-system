
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

import javax.persistence.*;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

import com.jshandyman.service.entitys.PhoneClient;
import com.jshandyman.service.entitys.MailCliente;
import org.hibernate.envers.Audited;


@Entity
@Table(name = "client")
@Audited
public class Client extends Auditable  {


    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idClient", updatable = true, nullable = false, length = 25)
    private Long idClient;

    @Column(name = "codeClient", updatable = true, nullable = true, length = 200)
    private String codeClient;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "lastName", updatable = true, nullable = true, length = 200)
    private String lastName;

    @Column(name = "address", updatable = true, nullable = true, length = 200)
    private String address;

    @Column(name = "state", updatable = true, nullable = true, length = 200)
    private String state;

    @Column(name = "zipCode", updatable = true, nullable = true, length = 200)
    private String zipCode;

    @Column(name = "fullAdress", updatable = true, nullable = true, length = 200)
    private String fullAdress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "client_id", referencedColumnName = "idClient")
    private List<PhoneClient> phoneNumbers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "client_id", referencedColumnName = "idClient")
    private List<MailCliente> emails = new ArrayList<>();

    @Column(name = "codeWork")
    private String codeWork;

    @Column(name = "city")
    private String city;

    @Column(name = "active", updatable = true, nullable = true, length = 200)
    private Boolean active;

    @Column(name = "fullName", updatable = true, nullable = true, length = 200)
    private String fullName;

    @Column(name = "estimateId", updatable = true, nullable = true, length = 200)
    private Long estimateId;

    @Column(name = "workId", updatable = true, nullable = true, length = 200)
    private Long workId;

    @Column(name = "company", updatable = true, nullable = true, length = 500)
    private String company;

    public Client() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(Long estimateId) {
        this.estimateId = estimateId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCodeWork() {
        return codeWork;
    }

    public void setCodeWork(String codeWork) {
        this.codeWork = codeWork;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFullAdress() {
        return fullAdress;
    }

    public void setFullAdress(String fullAdress) {
        this.fullAdress = fullAdress;
    }

    public List<PhoneClient> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneClient> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<MailCliente> getEmails() {
        return emails;
    }

    public void setEmails(List<MailCliente> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(idClient, client.idClient) && Objects.equals(codeClient, client.codeClient) && Objects.equals(name, client.name) && Objects.equals(lastName, client.lastName) && Objects.equals(address, client.address) && Objects.equals(state, client.state) && Objects.equals(zipCode, client.zipCode) && Objects.equals(fullAdress, client.fullAdress) && Objects.equals(phoneNumbers, client.phoneNumbers) && Objects.equals(emails, client.emails) && Objects.equals(codeWork, client.codeWork) && Objects.equals(city, client.city) && Objects.equals(active, client.active) && Objects.equals(fullName, client.fullName) && Objects.equals(estimateId, client.estimateId) && Objects.equals(workId, client.workId) && Objects.equals(company, client.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idClient, codeClient, name, lastName, address, state, zipCode, fullAdress, phoneNumbers, emails, codeWork, city, active, fullName, estimateId, workId, company);
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

