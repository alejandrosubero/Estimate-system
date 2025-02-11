package com.service.crom.entitys;


import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "parameters")
public class Parameters{

    @Id
    @GeneratedValue(generator = "sequence_mat_id_Parameters_generator")
    @SequenceGenerator(name = "sequence_mat_id_Parameters_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idParametee", updatable = true, nullable = false, length = 25)
    private Long idParametee;

    @Column(name = "clave", updatable = true, nullable = true, length = 500)
    private String clave;

    @Column(name = "valor", updatable = true, nullable = true, length = 4000)
    private String valor;

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "nota", updatable = true, nullable = true, length = 2000)
    private String nota;

    @Column(name = "activo", updatable = true, nullable = true, length = 10)
    private Boolean activo;

    public Parameters() {
    }

    public Long getIdParametee() {
        return idParametee;
    }

    public void setIdParametee(Long idParametee) {
        this.idParametee = idParametee;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameters)) return false;
        if (!super.equals(o)) return false;
        Parameters that = (Parameters) o;
        return Objects.equals(idParametee, that.idParametee) && Objects.equals(clave, that.clave) && Objects.equals(valor, that.valor) && Objects.equals(description, that.description) && Objects.equals(nota, that.nota) && Objects.equals(activo, that.activo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idParametee, clave, valor, description, nota, activo);
    }
}
