package com.service.mail.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Adjuntos {

    List<String> adjuntos = new ArrayList<String>();

    public Adjuntos() {
    }

    public Adjuntos(List<String> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public List<String> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<String> adjuntos) {
        this.adjuntos = adjuntos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adjuntos)) return false;
        Adjuntos adjuntos1 = (Adjuntos) o;
        return Objects.equals(adjuntos, adjuntos1.adjuntos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adjuntos);
    }
}
