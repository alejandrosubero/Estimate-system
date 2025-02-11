
/*
Create on Sun Sep 11 00:37:29 EDT 2022
*Copyright (C) 122.
@author alejandro
@author js
@author  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: email service for send </p>
*/

package com.service.mail.pojo;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MailSenderPojo {

    private Long idMailSender;

    private String port;

    private String template;

    private Boolean active;

    private String wasSend;

    private String email;

    private String attachments;

    private Date createDay;

    private EmailDataConfigPojo Emailconfiguration;

	private EmailsPojo emailsPojo;

    private List<String> adjuntos = new ArrayList<String>();

    private List<AttachmentPojo> ListAttachments = new ArrayList<AttachmentPojo>();

    private String company;


    public MailSenderPojo() {
    }

    public void addAdjuntos(String[] adjuntosLista) {
        if(adjuntosLista != null && adjuntosLista.length >0){
            Collections.addAll( this.adjuntos, adjuntosLista);
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Long getIdMailSender() {
        return idMailSender;
    }

    public void setIdMailSender(Long idMailSender) {
        this.idMailSender = idMailSender;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getWasSend() {
        return wasSend;
    }

    public void setWasSend(String wasSend) {
        this.wasSend = wasSend;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public EmailDataConfigPojo getEmailconfiguration() {
        return Emailconfiguration;
    }

    public void setEmailconfiguration(EmailDataConfigPojo emailconfiguration) {
        Emailconfiguration = emailconfiguration;
    }

    public EmailsPojo getEmailsPojo() {
        return emailsPojo;
    }

    public void setEmailsPojo(EmailsPojo emailsPojo) {
        this.emailsPojo = emailsPojo;
    }

    public List<String> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<String> adjuntos) {
        this.adjuntos = adjuntos;
    }


    public List<AttachmentPojo> getListAttachments() {
        return ListAttachments;
    }

    public void setListAttachments(List<AttachmentPojo> listAttachments) {
        ListAttachments = listAttachments;
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

