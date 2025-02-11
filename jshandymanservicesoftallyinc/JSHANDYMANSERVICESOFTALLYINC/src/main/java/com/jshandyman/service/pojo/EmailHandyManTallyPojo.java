package com.jshandyman.service.pojo;

import java.util.Objects;


public class EmailHandyManTallyPojo {

    private String subject;
    private String template;
    private Boolean printProduct;
    private Boolean avancePayments;
    private WorkPojo workPojo;
    private EstimatePojo estimatePojo;
    private EmailsPojo email;
    private EmailDataConfigPojo Emailconfiguration;
    private DataJshandyManServicesConfigPojo dataJshandyManServices;
    private String company;

    public EmailHandyManTallyPojo() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public DataJshandyManServicesConfigPojo getDataJshandyManServices() {
        return dataJshandyManServices;
    }

    public void setDataJshandyManServices(DataJshandyManServicesConfigPojo dataJshandyManServices) {
        this.dataJshandyManServices = dataJshandyManServices;
    }

    public EmailsPojo getEmail() {
        return email;
    }

    public void setEmail(EmailsPojo email) {
        this.email = email;
    }

    public Boolean getPrintProduct() {
        return printProduct;
    }

    public void setPrintProduct(Boolean printProduct) {
        this.printProduct = printProduct;
    }

    public EstimatePojo getEstimatePojo() {
        return estimatePojo;
    }

    public void setEstimatePojo(EstimatePojo estimatePojo) {
        this.estimatePojo = estimatePojo;
    }


    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public WorkPojo getWorkPojo() {
        return workPojo;
    }

    public void setWorkPojo(WorkPojo workPojo) {
        this.workPojo = workPojo;
    }


    public EmailDataConfigPojo getEmailconfiguration() {
        return Emailconfiguration;
    }

    public void setEmailconfiguration(EmailDataConfigPojo emailconfiguration) {
        Emailconfiguration = emailconfiguration;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getAvancePayments() {
        return avancePayments;
    }

    public void setAvancePayments(Boolean avancePayments) {
        this.avancePayments = avancePayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailHandyManTallyPojo that = (EmailHandyManTallyPojo) o;
        return Objects.equals(subject, that.subject) && Objects.equals(template, that.template) && Objects.equals(printProduct, that.printProduct) && Objects.equals(avancePayments, that.avancePayments) && Objects.equals(workPojo, that.workPojo) && Objects.equals(estimatePojo, that.estimatePojo) && Objects.equals(email, that.email) && Objects.equals(Emailconfiguration, that.Emailconfiguration) && Objects.equals(dataJshandyManServices, that.dataJshandyManServices) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, template, printProduct, avancePayments, workPojo, estimatePojo, email, Emailconfiguration, dataJshandyManServices, company);
    }
}
