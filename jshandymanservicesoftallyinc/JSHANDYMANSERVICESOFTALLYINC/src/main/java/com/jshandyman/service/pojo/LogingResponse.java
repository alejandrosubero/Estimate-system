package com.jshandyman.service.pojo;

import java.util.Objects;

public class LogingResponse {

    private String authorization;
    private String token;
    private String username;
    private String userCode;
    private Double taxes;
    private DataJshandyManServicesConfigPojo dataJshandyManServices;
    private Object status;
    private String userImagen;
    private Boolean configurationRedy;

    private String company;


    public LogingResponse() {
        this.authorization = "Authorization";
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getConfigurationRedy() {
        return configurationRedy;
    }

    public void setConfigurationRedy(Boolean configurationRedy) {
        this.configurationRedy = configurationRedy;
    }

    public DataJshandyManServicesConfigPojo getData() {
        return dataJshandyManServices;
    }

    public void setData(DataJshandyManServicesConfigPojo data) {
        this.dataJshandyManServices = data;
    }

    public String getAuthorization() {
        return authorization;
    }


    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public Object getStatus() {
        return status;
    }


    public void setStatus(Object status) {
        this.status = status;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getUserCode() {
        return userCode;
    }


    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public String getUserImagen() {
        return userImagen;
    }

    public void setUserImagen(String userImagen) {
        this.userImagen = userImagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogingResponse that = (LogingResponse) o;
        return Objects.equals(authorization, that.authorization) && Objects.equals(token, that.token) && Objects.equals(username, that.username) && Objects.equals(userCode, that.userCode) && Objects.equals(taxes, that.taxes) && Objects.equals(dataJshandyManServices, that.dataJshandyManServices) && Objects.equals(status, that.status) && Objects.equals(userImagen, that.userImagen) && Objects.equals(configurationRedy, that.configurationRedy) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorization, token, username, userCode, taxes, dataJshandyManServices, status, userImagen, configurationRedy, company);
    }
}
