package com.jshandyman.service.pojo;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MailSender {

    private Long idMailSender;

    private Date createDay;

    private String port;

    private String template;

    private Boolean active;

    private String wasSend;

    private String email;

    private String attachments;

    private List<AttachmentPojo> ListAttachments = new ArrayList<AttachmentPojo>();

    private String company;

    public MailSender() {
    }

    public MailSender(String port, String template, String email, String attachments) {
        this.port = port;
        this.template = template;
        this.active = true;
        this.wasSend = "N";
        this.email = email;
        this.attachments = attachments;
        this.createDay = new Date();
    }

    public MailSender(String port, String template, String email, List<AttachmentPojo> listAttachments, String company) {
        this.port = port;
        this.template = template;
        this.createDay = new Date();
        this.active = true;
        this.wasSend = "N";
        this.email = email;
        ListAttachments = listAttachments;
        this.company = company;
    }

    public MailSender(String port, String template, String email, String attachments, List<AttachmentPojo> listAttachments) {
        this.port = port;
        this.template = template;
        this.createDay = new Date();
        this.active = true;
        this.wasSend = "N";
        this.email = email;
        this.attachments = attachments;
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

    public List<AttachmentPojo> getListAttachments() {
        return ListAttachments;
    }

    public void setListAttachments(List<AttachmentPojo> listAttachments) {
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

    @Override
    public String toString() {
        return "MailSender{" +
                "idMailSender=" + idMailSender +
                ", createDay=" + createDay +
                ", port='" + port + '\'' +
                ", template='" + template + '\'' +
                ", active=" + active +
                ", wasSend='" + wasSend + '\'' +
                ", email='" + email + '\'' +
                ", attachments='" + attachments + '\'' +
                ", ListAttachments=" + ListAttachments +
                '}';
    }
}
