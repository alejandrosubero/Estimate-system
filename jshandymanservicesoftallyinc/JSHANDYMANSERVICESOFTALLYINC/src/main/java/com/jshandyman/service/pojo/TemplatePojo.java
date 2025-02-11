package com.jshandyman.service.pojo;

import com.jshandyman.service.entitys.Template;

import javax.persistence.*;

import java.util.Objects;

public class TemplatePojo  {

    private Long idTemplete;
    private String template;
    private boolean active;
    private String descripcion;
    private String tipo;
    private String codeTemplate;
    private String company;

    public TemplatePojo() {
    }

    public TemplatePojo(String template, boolean active, String descripcion, String tipo, String codeTemplate) {
        this.template = template;
        this.active = active;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.codeTemplate = codeTemplate;
    }

    public static TemplatePojo newTemplate(Template entity){
        TemplatePojo temp = new TemplatePojo();
        temp.setTemplate(entity.getTemplate());
        temp.setActive(entity.isActive());
        temp.setCodeTemplate(entity.getCodeTemplate());
        temp.setDescripcion(entity.getDescripcion());
        temp.setTipo(entity.getTipo());
        temp.setIdTemplete(entity.getIdTemplete());
        temp.setCompany(entity.getCompany());
        return temp;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getIdTemplete() {
        return idTemplete;
    }

    public void setIdTemplete(Long idTemplete) {
        this.idTemplete = idTemplete;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodeTemplate() {
        return codeTemplate;
    }

    public void setCodeTemplate(String codeTemplate) {
        this.codeTemplate = codeTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplatePojo that = (TemplatePojo) o;
        return active == that.active && Objects.equals(idTemplete, that.idTemplete) && Objects.equals(template, that.template) && Objects.equals(descripcion, that.descripcion) && Objects.equals(tipo, that.tipo) && Objects.equals(codeTemplate, that.codeTemplate) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTemplete, template, active, descripcion, tipo, codeTemplate, company);
    }
}
