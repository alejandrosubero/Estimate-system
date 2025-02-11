
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


public class MailClientePojo {

    
    private Long idMailCliente;
    private String email;
    private String codeClient;
    private Long idClient;

    public MailClientePojo() {
    }

    public MailClientePojo(String email, String codeClient, Long idClient) {
        if(email!=null)
        this.email = email;

        if(codeClient!=null)
        this.codeClient = codeClient;

        if(idClient!=null)
        this.idClient = idClient;
    }


    public MailClientePojo(MailClientePojo oterMailCliente) {

       if(oterMailCliente !=null){
           if(oterMailCliente.getEmail()!=null){
               this.email = oterMailCliente.getEmail();
           }

           if(oterMailCliente.getCodeClient()!=null){
               this.codeClient = oterMailCliente.getCodeClient();
           }

           if(oterMailCliente.getIdClient() !=null){
               this.idClient = oterMailCliente.getIdClient();
           }
       }
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
        if (!(o instanceof MailClientePojo)) return false;
        MailClientePojo that = (MailClientePojo) o;
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

