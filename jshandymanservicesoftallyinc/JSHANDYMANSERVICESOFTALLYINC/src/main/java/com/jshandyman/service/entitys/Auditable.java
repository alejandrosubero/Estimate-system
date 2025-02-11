package com.jshandyman.service.entitys;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

   

    @CreatedBy
    protected String creadoPor;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaHoraCreacion;

    @LastModifiedBy
    protected String ultimaActualizacionPor;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaHoraUltimaActualizacion;


    public Auditable() {
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(String ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Date getFechaHoraUltimaActualizacion() {
        return fechaHoraUltimaActualizacion;
    }

    public void setFechaHoraUltimaActualizacion(Date fechaHoraUltimaActualizacion) {
        this.fechaHoraUltimaActualizacion = fechaHoraUltimaActualizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditable)) return false;
        Auditable auditable = (Auditable) o;
        return Objects.equals(creadoPor, auditable.creadoPor) && Objects.equals(fechaHoraCreacion, auditable.fechaHoraCreacion) && Objects.equals(ultimaActualizacionPor, auditable.ultimaActualizacionPor) && Objects.equals(fechaHoraUltimaActualizacion, auditable.fechaHoraUltimaActualizacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creadoPor, fechaHoraCreacion, ultimaActualizacionPor, fechaHoraUltimaActualizacion);
    }
}
