package com.jshandyman.service.entitys;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dataJshandyManServicesConfig")
public class DataJshandyManServicesConfig extends Auditable  {


    @Id
    @GeneratedValue(generator = "sequence_DataConfig_id_generator")
    @SequenceGenerator(name="sequence_DataConfig_id_generator", initialValue= 1, allocationSize=1000)
    @Column(name = "idDataConfig", updatable = true, nullable = false, length = 30)
    private Long idDataConfig;

    @Column(name = "direction", updatable = true, nullable = true, length = 500)
    private String direction;

    @Column(name = "mail", updatable = true, nullable = true, length = 500)
    private String mail;

    @Column(name = "web", updatable = true, nullable = true, length = 500)
    private String web;

    @Column(name = "phoneNumber", updatable = true, nullable = true, length = 500)
    private String phoneNumber;

    @Column(name = "TaxRegNumber", updatable = true, nullable = true, length = 500)
    private String taxRegNumber;

    @Column(name = "coments1", updatable = true, nullable = true, length = 3000)
    private String coments1;

    @Column(name = "coments2", updatable = true, nullable = true, length = 3000)
    private String coments2;

    @Column(name = "coments3", updatable = true, nullable = true, length = 3000)
    private String coments3;

    @Column(name = "coments4", updatable = true, nullable = true, length = 3000)
    private String coments4;

    @Column(name = "portMail", updatable = true, nullable = true, length = 500)
    private String portMail;

    @Column(name = "company", updatable = true, nullable = true, length = 500)
    private String company;

    @Column(name = "deadLineAlert", updatable = true, nullable = true, length = 500)
    private Integer deadLineAlert;

    @Column(name = "userCode", updatable = true, nullable = true, length = 200)
    private String userCode;

    @Column(name = "active", updatable = true, nullable = true, length = 200)
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EmailDataConfig emailDataConfig;


    @Column(name = "companyName", updatable = true, nullable = true, length = 500)
    private String companyName;

    public DataJshandyManServicesConfig() {
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

    public String getPortMail() {
        return portMail;
    }

    public void setPortMail(String portMail) {
        this.portMail = portMail;
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


    public com.jshandyman.service.entitys.EmailDataConfig getEmailDataConfig() {
        return emailDataConfig;
    }

    public void setEmailDataConfig(com.jshandyman.service.entitys.EmailDataConfig emailDataConfig) {
        emailDataConfig = emailDataConfig;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DataJshandyManServicesConfig that = (DataJshandyManServicesConfig) o;
        return Objects.equals(idDataConfig, that.idDataConfig) && Objects.equals(direction, that.direction) && Objects.equals(mail, that.mail) && Objects.equals(web, that.web) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(taxRegNumber, that.taxRegNumber) && Objects.equals(coments1, that.coments1) && Objects.equals(coments2, that.coments2) && Objects.equals(coments3, that.coments3) && Objects.equals(coments4, that.coments4) && Objects.equals(portMail, that.portMail) && Objects.equals(company, that.company) && Objects.equals(deadLineAlert, that.deadLineAlert) && Objects.equals(userCode, that.userCode) && Objects.equals(active, that.active) && Objects.equals(emailDataConfig, that.emailDataConfig) && Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idDataConfig, direction, mail, web, phoneNumber, taxRegNumber, coments1, coments2, coments3, coments4, portMail, company, deadLineAlert, userCode, active, emailDataConfig, companyName);
    }
}
