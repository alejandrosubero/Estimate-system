package com.jshandyman.service.security.logingTrack.pojos;


import java.util.Date;
import java.util.Objects;

public class LogingTrackPojo {

    private Long idLogingTrack;

    private String codeWork;

    private String token;

    private Boolean active;

    private String userName;

    private Date loguingDay;

    private Date logUpDayOficial;

    public LogingTrackPojo() {
    }

    public LogingTrackPojo(String codeWork, String token, String userName) {
        this.codeWork = codeWork;
        this.token = token;
        this.userName = userName;
        this.active = true;
        this.loguingDay = new Date();
    }

    public Long getIdLogingTrack() {
        return idLogingTrack;
    }

    public void setIdLogingTrack(Long idLogingTrack) {
        this.idLogingTrack = idLogingTrack;
    }

    public String getCodeWork() {
        return codeWork;
    }

    public void setCodeWork(String codeWork) {
        this.codeWork = codeWork;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActivo() {
        return active;
    }

    public void setActivo(Boolean activo) {
        this.active = activo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLoguingDay() {
        return loguingDay;
    }

    public void setLoguingDay(Date loguingDay) {
        this.loguingDay = loguingDay;
    }

    public Date getLogUpDayOficial() {
        return logUpDayOficial;
    }

    public void setLogUpDayOficial(Date logUpDayOficial) {
        this.logUpDayOficial = logUpDayOficial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogingTrackPojo that = (LogingTrackPojo) o;
        return Objects.equals(idLogingTrack, that.idLogingTrack) && Objects.equals(codeWork, that.codeWork) && Objects.equals(token, that.token) && Objects.equals(active, that.active) && Objects.equals(userName, that.userName) && Objects.equals(loguingDay, that.loguingDay) && Objects.equals(logUpDayOficial, that.logUpDayOficial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogingTrack, codeWork, token, active, userName, loguingDay, logUpDayOficial);
    }
}
