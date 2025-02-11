
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
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "phoneclient")
@Audited
public class PhoneClient {


    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idPhoneClient", updatable = true, nullable = false, length = 25)
    private Long idPhoneClient;


    @Column(name = "number", updatable = true, nullable = true, length = 200)
    private String number;


    @Column(name = "codeClient", updatable = true, nullable = true, length = 200)
    private String codeClient;


    @Column(name = "client_id", updatable = true, nullable = true, length = 200)
    private Long idClient;

    public PhoneClient() {
    }

    public Long getIdPhoneClient() {
        return idPhoneClient;
    }

    public void setIdPhoneClient(Long idPhoneClient) {
        this.idPhoneClient = idPhoneClient;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneClient)) return false;
        PhoneClient that = (PhoneClient) o;
        return Objects.equals(idPhoneClient, that.idPhoneClient) && Objects.equals(number, that.number) && Objects.equals(codeClient, that.codeClient) && Objects.equals(idClient, that.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhoneClient, number, codeClient, idClient);
    }

    @Override
    public String toString() {
        return "PhoneClient{" +
                "idPhoneClient=" + idPhoneClient +
                ", number='" + number + '\'' +
                ", codeClient='" + codeClient + '\'' +
                ", idClient=" + idClient +
                '}';
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

