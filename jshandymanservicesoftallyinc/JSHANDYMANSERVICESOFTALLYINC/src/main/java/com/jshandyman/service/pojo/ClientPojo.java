
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
import java.util.List;
import java.util.Objects;


public class ClientPojo {

    private Long idClient;
    private String codeClient;
    private String name;
    private String lastName;
    private String address;
    private String state;
    private String zipCode;
    private String fullAdress;
    private List<PhoneClientPojo> phoneNumbers = new ArrayList<>();
    private List<MailClientePojo> emails = new ArrayList<>();
    private String codeWork;
    private String city;
    private Boolean active;
    private String fullName;
    private Long estimateId;
    private Long workId;

    private String company;

    public ClientPojo() {
    }


    public ClientPojo(String codeClient, String name, String lastName, String address, String state, String zipCode,
                      String fullAdress, List<PhoneClientPojo> phoneNumbers, List<MailClientePojo> emails, String codeWork, String city) {

        if (codeClient != null) {
            this.codeClient = codeClient;
        }
        if (name != null) {
            this.name = name;
        }
        if (lastName != null) {
            this.lastName = lastName;
        }
        if (address != null) {
            this.address = address;
        }
        if (state != null) {
            this.state = state;
        }
        if (zipCode != null) {
            this.zipCode = zipCode;
        }
        if (fullAdress != null) {
            this.fullAdress = fullAdress;
        }
        if (phoneNumbers != null) {
            this.phoneNumbers = phoneNumbers;
        }
        if (emails != null) {
            this.emails = emails;
        }
        if (codeWork != null) {
            this.codeWork = codeWork;
        }
        if (city != null) {
            this.city = city;
        }
//        this.active = true;
    }

    public ClientPojo(String codeClient, String name, String lastName, String address, String state, String zipCode,
                      String fullAdress, List<PhoneClientPojo> phoneNumbers, List<MailClientePojo> emails, String city) {

        if (codeClient != null) {
            this.codeClient = codeClient;
        }
        if (name != null) {
            this.name = name;
        }
        if (lastName != null) {
            this.lastName = lastName;
        }
        if (address != null) {
            this.address = address;
        }
        if (state != null) {
            this.state = state;
        }
        if (zipCode != null) {
            this.zipCode = zipCode;
        }
        if (fullAdress != null) {
            this.fullAdress = fullAdress;
        }
        if (phoneNumbers != null) {
            this.phoneNumbers = phoneNumbers;
        }
        if (emails != null) {
            this.emails = emails;
        }
        if (city != null) {
            this.city = city;
        }
//        this.active = true;
    }


    public ClientPojo(ClientPojo oterClientPojo) {

        if (oterClientPojo != null) {
            if (oterClientPojo.getCodeClient() != null) {
                this.codeClient = oterClientPojo.getCodeClient();
            }
            if (oterClientPojo.getName() != null) {
                this.name = oterClientPojo.getName();
            }
            if (oterClientPojo.getLastName() != null) {
                this.lastName = oterClientPojo.getLastName();
            }
            if (oterClientPojo.getAddress() != null) {
                this.address = oterClientPojo.getAddress();
            }
            if (oterClientPojo.getState() != null) {
                this.state = oterClientPojo.getState();
            }
            if (oterClientPojo.getZipCode() != null) {
                this.zipCode = oterClientPojo.getZipCode();
            }
            if (oterClientPojo.getFullAdress() != null) {
                this.fullAdress = oterClientPojo.getFullAdress();
            }
            if (oterClientPojo.getPhoneNumbers() != null) {
                if (oterClientPojo.getPhoneNumbers().size() > 0) {
                    this.phoneNumbers = this.clonePhoneClientPojo(oterClientPojo.getPhoneNumbers());
                } else {
                    this.phoneNumbers = new ArrayList<PhoneClientPojo>();
                }
            }

            if (oterClientPojo.getEmails() != null) {
                if (oterClientPojo.getEmails().size() > 0) {
                    this.emails = this.cloneMailClientePojo(oterClientPojo.getEmails());
                } else {
                    this.emails = new ArrayList<MailClientePojo>();
                }
            }

            if (oterClientPojo.getCity() != null) {
                this.city = oterClientPojo.getCity();
            }

            if (oterClientPojo.getCodeWork() != null) {
                this.codeWork = oterClientPojo.getCodeWork();
            }
            if (oterClientPojo.getFullName() != null) {
                this.fullName = oterClientPojo.getFullName();
            }

//            if (oterClientPojo.getEstimateId() != null) {
//                this.estimateId = oterClientPojo.getEstimateId();
//            }

            if (oterClientPojo.getActive() != null) {
                this.active = true;
            }
        }
    }

    private List<PhoneClientPojo> clonePhoneClientPojo(List<PhoneClientPojo> phoneNumbers) {
        List<PhoneClientPojo> phoneNumbersList = new ArrayList<>();
        phoneNumbers.stream().forEach(phoneClientPojo -> phoneNumbersList.add(new PhoneClientPojo(phoneClientPojo)));
        return phoneNumbersList;
    }

    private List<MailClientePojo> cloneMailClientePojo(List<MailClientePojo> emailsList) {
        List<MailClientePojo> emails = new ArrayList<>();
        emailsList.stream().forEach(mailClientePojo -> emails.add(new MailClientePojo(mailClientePojo)));
        return emails;
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

    public List<PhoneClientPojo> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneClientPojo> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<MailClientePojo> getEmails() {
        return emails;
    }

    public void setEmails(List<MailClientePojo> emails) {
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientPojo that = (ClientPojo) o;
        return Objects.equals(idClient, that.idClient) && Objects.equals(codeClient, that.codeClient) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(state, that.state) && Objects.equals(zipCode, that.zipCode) && Objects.equals(fullAdress, that.fullAdress) && Objects.equals(phoneNumbers, that.phoneNumbers) && Objects.equals(emails, that.emails) && Objects.equals(codeWork, that.codeWork) && Objects.equals(city, that.city) && Objects.equals(active, that.active) && Objects.equals(fullName, that.fullName) && Objects.equals(estimateId, that.estimateId) && Objects.equals(workId, that.workId) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, codeClient, name, lastName, address, state, zipCode, fullAdress, phoneNumbers, emails, codeWork, city, active, fullName, estimateId, workId, company);
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

