
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
@Table(name = "mailcliente")
@Audited
public class MailCliente  {

   

    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idMailCliente", updatable = true, nullable = false, length = 25)
    private Long idMailCliente;

    @Column(name = "email", updatable = true, nullable = true, length = 200)
    private String email;

    @Column(name = "codeClient", updatable = true, nullable = true, length = 200)
    private String codeClient;

    @Column(name = "client_id")
    private Long idClient;

    public MailCliente() {
    }

    public Long getIdMailCliente() {
        return idMailCliente;
    }

    public void setIdMailCliente(Long idMailCliente) {
        this.idMailCliente = idMailCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof MailCliente)) return false;
        MailCliente that = (MailCliente) o;
        return Objects.equals(idMailCliente, that.idMailCliente) && Objects.equals(email, that.email) && Objects.equals(codeClient, that.codeClient) && Objects.equals(idClient, that.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMailCliente, email, codeClient, idClient);
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

