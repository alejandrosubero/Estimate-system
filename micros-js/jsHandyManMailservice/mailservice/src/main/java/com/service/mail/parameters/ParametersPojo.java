package com.template.parameter.pojo;


import java.util.Objects;

public class ParametersPojo  {

    private Long id;
    private String clave;
    private String valor;
    private String description;
    private String nota;
    private Boolean activo;

    public ParametersPojo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof ParametersPojo)) return false;
        ParametersPojo that = (ParametersPojo) o;
        return Objects.equals(id, that.id) && Objects.equals(clave, that.clave) && Objects.equals(valor, that.valor) && Objects.equals(description, that.description) && Objects.equals(nota, that.nota) && Objects.equals(activo, that.activo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clave, valor, description, nota, activo);
    }
}
