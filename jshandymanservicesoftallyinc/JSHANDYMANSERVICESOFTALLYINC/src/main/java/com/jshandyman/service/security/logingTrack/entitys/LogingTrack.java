package com.jshandyman.service.security.logingTrack.entitys;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "logingTrack")
@Audited
public class LogingTrack {

    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 2500, allocationSize = 150000)
    @Column(name = "idLogingTrack", updatable = true, nullable = false, length = 200)
    private Long idLogingTrack;

    @Column(name = "codeWork", updatable = true, nullable = true, length = 200)
    private String codeWork;

    @Lob
    @Column(name = "token", updatable = true, nullable = true)
    private String token;

    @Column(name = "active", updatable = true, nullable = false)
    private Boolean active;

    @Column(name = "userName", updatable = true, nullable = true, length = 200)
    private String userName;

    @Column(name = "loguingDay", updatable = true, nullable = true)
    private Date loguingDay;

    @Column(name = "logUpDayOficial", updatable = true, nullable = true)
    private Date logUpDayOficial;


    public LogingTrack() {
    }

    public LogingTrack(String codeWork, String token, String userName) {
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
        LogingTrack that = (LogingTrack) o;
        return Objects.equals(idLogingTrack, that.idLogingTrack) && Objects.equals(codeWork, that.codeWork) && Objects.equals(token, that.token) && Objects.equals(active, that.active) && Objects.equals(userName, that.userName) && Objects.equals(loguingDay, that.loguingDay) && Objects.equals(logUpDayOficial, that.logUpDayOficial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogingTrack, codeWork, token, active, userName, loguingDay, logUpDayOficial);
    }
}
