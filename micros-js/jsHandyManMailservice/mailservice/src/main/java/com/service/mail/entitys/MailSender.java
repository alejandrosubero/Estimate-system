
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

package com.service.mail.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.service.mail.entitys.EmailDataConfig;


@Entity
@Table(name = "mailsender")
public class MailSender implements Serializable {

    private static final long serialVersionUID = 8286941585339050054L;

    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idMailSender", updatable = true, nullable = false, length = 25)
    private Long idMailSender;

    @Column(name = "createday", updatable = true, nullable = true)
    private Date createDay;

    @Column(name = "port", updatable = true, nullable = true, length = 200)
    private String port;

    @Lob
    @Column(name = "template", updatable = true, nullable = true)
    private String template;

    @Column(name = "active", updatable = true, nullable = true)
    private Boolean active;

    @Column(name = "wasSend", updatable = true, nullable = true, length = 20)
    private String wasSend;

    @Lob
    @Column(name = "email", updatable = true, nullable = true)
    private String email;

    @Column(name = "attachments", updatable = true, nullable = true, length = 1000)
    private String attachments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "mailSender_id", referencedColumnName = "idMailSender")
    private List<Attachment> ListAttachments = new ArrayList<Attachment>();


    @Column(name = "company", updatable = true, nullable = true, length = 500)
    private String company;


    public MailSender() {
    }

    public MailSender(Long idMailSender, Date createDay, String port, String template, Boolean active, String wasSend, String email, String attachments) {
        this.idMailSender = idMailSender;
        this.createDay = createDay;
        this.port = port;
        this.template = template;
        this.active = active;
        this.wasSend = wasSend;
        this.email = email;
        this.attachments = attachments;
    }

    public MailSender(Date createDay, String port, String template, Boolean active, String wasSend, String email, List<Attachment> listAttachments) {
        this.createDay = createDay;
        this.port = port;
        this.template = template;
        this.active = active;
        this.wasSend = wasSend;
        this.email = email;
        ListAttachments = listAttachments;
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


    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
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


    public List<Attachment> getListAttachments() {
        return ListAttachments;
    }

    public void setListAttachments(List<Attachment> listAttachments) {
        ListAttachments = listAttachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailSender that = (MailSender) o;
        return Objects.equals(idMailSender, that.idMailSender) && Objects.equals(createDay, that.createDay) && Objects.equals(port, that.port) && Objects.equals(template, that.template) && Objects.equals(active, that.active) && Objects.equals(wasSend, that.wasSend) && Objects.equals(email, that.email) && Objects.equals(attachments, that.attachments) && Objects.equals(ListAttachments, that.ListAttachments) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMailSender, createDay, port, template, active, wasSend, email, attachments, ListAttachments, company);
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

