
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

import javax.persistence.*;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;


public class PhoneClientPojo {

    private Long idPhoneClient;
    private String number;
    private String codeClient;
    private Long idClient;


    public PhoneClientPojo() {
    }


    public PhoneClientPojo(PhoneClientPojo oterPhoneClientPojo) {

            if(oterPhoneClientPojo != null && oterPhoneClientPojo.getNumber() !=null){
                this.number = oterPhoneClientPojo.getNumber();
            }

            if(oterPhoneClientPojo != null && oterPhoneClientPojo.getCodeClient() !=null){
                this.codeClient = oterPhoneClientPojo.getCodeClient();
            }

            if(oterPhoneClientPojo != null && oterPhoneClientPojo.getIdClient() !=null) {
                this.idClient = oterPhoneClientPojo.getIdClient();
            }

    }


    public PhoneClientPojo(Long idPhoneClient, String number, String codeClient, Long idClient) {
        if(idPhoneClient !=null) {
            this.idPhoneClient = idPhoneClient;
        }
        if(number !=null) {
            this.number = number;
        }
        if(codeClient !=null) {
            this.codeClient = codeClient;
        }
        if(idClient !=null) {
            this.idClient = idClient;
        }
    }

    public PhoneClientPojo(String number, String codeClient, Long idClient) {

        if(number !=null){
            this.number = number;
        }
        if(codeClient !=null) {
            this.codeClient = codeClient;
        }
        if(idClient !=null) {
            this.idClient = idClient;
        }
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
        if (!(o instanceof PhoneClientPojo)) return false;
        PhoneClientPojo that = (PhoneClientPojo) o;
        return Objects.equals(idPhoneClient, that.idPhoneClient) && Objects.equals(number, that.number) && Objects.equals(codeClient, that.codeClient) && Objects.equals(idClient, that.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhoneClient, number, codeClient, idClient);
    }

    @Override
    public String toString() {
        return "PhoneClientPojo{" +
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

