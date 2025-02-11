package com.jshandyman.service.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "template")
public class Template  {


    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idTemplete", updatable = true, nullable = false, length = 25)
    private Long idTemplete;

    @Lob
    @Column(name = "template", updatable = true, nullable = true)
    private String template;

    @Column(name = "active", updatable = true, nullable = true)
    private boolean active;

    @Column(name = "descripcion", updatable = true, nullable = true)
    private String descripcion;

    @Column(name = "tipo", updatable = true, nullable = true)
    private String tipo;

    @Column(name = "codeTemplate", updatable = true, nullable = true)
    private String codeTemplate;

    @Column(name = "company", updatable = true, nullable = true, length = 200)
    private String company;

    public Template() {
    }

    public Template(String template, boolean active, String descripcion, String tipo, String codeTemplate) {
        this.template = template;
        this.active = active;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.codeTemplate = codeTemplate;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template1 = (Template) o;
        return active == template1.active && Objects.equals(idTemplete, template1.idTemplete) && Objects.equals(template, template1.template) && Objects.equals(descripcion, template1.descripcion) && Objects.equals(tipo, template1.tipo) && Objects.equals(codeTemplate, template1.codeTemplate) && Objects.equals(company, template1.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTemplete, template, active, descripcion, tipo, codeTemplate, company);
    }


}
