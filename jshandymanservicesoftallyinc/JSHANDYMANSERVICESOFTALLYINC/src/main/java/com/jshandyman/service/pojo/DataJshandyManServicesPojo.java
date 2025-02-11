package com.jshandyman.service.pojo;

import com.jshandyman.service.entitys.TaxesAndPrice;
import com.jshandyman.service.entitys.Template;


public class DataJshandyManServicesPojo {


    private Long idDataConfig;
    private String direction;
    private String mail;
    private String web;
    private String phoneNumber;
    private String TaxRegNumber;
    private String coments1;
    private String coments2;
    private String coments3;
    private String coments4;
    private String portMail;
    private String taxesDescriptionSearch;
    private String templateCode;
    private String company;
    private Integer deadLineAlert;
    private String userCode;

    private TaxesAndPrice taxesAndPrice;
    private Template template;
    private com.jshandyman.service.entitys.EmailDataConfig EmailDataConfig;

    public Long getIdDataConfig() {
        return idDataConfig;
    }

    public void setIdDataConfig(Long idDataConfig) {
        this.idDataConfig = idDataConfig;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTaxRegNumber() {
        return TaxRegNumber;
    }

    public void setTaxRegNumber(String taxRegNumber) {
        TaxRegNumber = taxRegNumber;
    }

    public String getComents1() {
        return coments1;
    }

    public void setComents1(String coments1) {
        this.coments1 = coments1;
    }

    public String getComents2() {
        return coments2;
    }

    public void setComents2(String coments2) {
        this.coments2 = coments2;
    }

    public String getComents3() {
        return coments3;
    }

    public void setComents3(String coments3) {
        this.coments3 = coments3;
    }

    public String getComents4() {
        return coments4;
    }

    public void setComents4(String coments4) {
        this.coments4 = coments4;
    }

    public String getPortMail() {
        return portMail;
    }

    public void setPortMail(String portMail) {
        this.portMail = portMail;
    }

    public String getTaxesDescriptionSearch() {
        return taxesDescriptionSearch;
    }

    public void setTaxesDescriptionSearch(String taxesDescriptionSearch) {
        this.taxesDescriptionSearch = taxesDescriptionSearch;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getDeadLineAlert() {
        return deadLineAlert;
    }

    public void setDeadLineAlert(Integer deadLineAlert) {
        this.deadLineAlert = deadLineAlert;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public TaxesAndPrice getTaxesAndPrice() {
        return taxesAndPrice;
    }

    public void setTaxesAndPrice(TaxesAndPrice taxesAndPrice) {
        this.taxesAndPrice = taxesAndPrice;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public com.jshandyman.service.entitys.EmailDataConfig getEmailDataConfig() {
        return EmailDataConfig;
    }

    public void setEmailDataConfig(com.jshandyman.service.entitys.EmailDataConfig emailDataConfig) {
        EmailDataConfig = emailDataConfig;
    }
}
