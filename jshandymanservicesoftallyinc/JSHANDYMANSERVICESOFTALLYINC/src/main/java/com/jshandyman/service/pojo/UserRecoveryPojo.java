package com.jshandyman.service.pojo;


import java.util.Objects;

public class UserRecoveryPojo {

    private String pregunta;
    private String respuesta;
    private String mail;


    public UserRecoveryPojo() {
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRecoveryPojo)) return false;
        UserRecoveryPojo that = (UserRecoveryPojo) o;
        return Objects.equals(pregunta, that.pregunta) && Objects.equals(respuesta, that.respuesta) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pregunta, respuesta, mail);
    }

    @Override
    public String toString() {
        return "UserRecoveryPojo{" +
                "pregunta='" + pregunta + '\'' +
                ", respuesta='" + respuesta + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
