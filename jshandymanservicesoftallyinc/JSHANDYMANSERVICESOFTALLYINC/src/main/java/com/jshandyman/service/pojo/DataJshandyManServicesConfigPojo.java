package com.jshandyman.service.pojo;


import java.util.Objects;

public class DataJshandyManServicesConfigPojo  {

  

    private Long idDataConfig;
    private String direction;
    private String mail;
    private String web;
    private String phoneNumber;
    private String taxRegNumber;
    private String coments1;
    private String coments2;
    private String coments3;
    private String coments4;

    private Integer deadLineAlert;
    private String userCode;
    private Boolean active;

    // Todo: implementar company para uso de multi empresas.
    private String company;

    //Todo: Cambiar a companyName en todos los ambientes y micros
    private String companyName;

    public DataJshandyManServicesConfigPojo() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

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
        return taxRegNumber;
    }

    public void setTaxRegNumber(String taxRegNumber) {
        this.taxRegNumber = taxRegNumber;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataJshandyManServicesConfigPojo that = (DataJshandyManServicesConfigPojo) o;
        return Objects.equals(idDataConfig, that.idDataConfig) && Objects.equals(direction, that.direction) && Objects.equals(mail, that.mail) && Objects.equals(web, that.web) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(taxRegNumber, that.taxRegNumber) && Objects.equals(coments1, that.coments1) && Objects.equals(coments2, that.coments2) && Objects.equals(coments3, that.coments3) && Objects.equals(coments4, that.coments4) && Objects.equals(company, that.company) && Objects.equals(deadLineAlert, that.deadLineAlert) && Objects.equals(userCode, that.userCode) && Objects.equals(active, that.active) && Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDataConfig, direction, mail, web, phoneNumber, taxRegNumber, coments1, coments2, coments3, coments4, company, deadLineAlert, userCode, active, companyName);
    }
}
