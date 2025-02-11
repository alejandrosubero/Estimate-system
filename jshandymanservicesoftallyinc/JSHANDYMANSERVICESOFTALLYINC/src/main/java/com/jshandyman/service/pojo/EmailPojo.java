package com.jshandyman.service.pojo;

import java.util.Arrays;
import java.util.Objects;

public class EmailPojo {

    private String to;
    private String from;
    private String subject;
    private String content;
    private byte[] adjunto;
    private String template;


    public EmailPojo() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(byte[] adjunto) {
        this.adjunto = adjunto;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailPojo)) return false;
        EmailPojo emailPojo = (EmailPojo) o;
        return Objects.equals(to, emailPojo.to) && Objects.equals(from, emailPojo.from) && Objects.equals(subject, emailPojo.subject) && Objects.equals(content, emailPojo.content) && Arrays.equals(adjunto, emailPojo.adjunto) && Objects.equals(template, emailPojo.template);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(to, from, subject, content, template);
        result = 31 * result + Arrays.hashCode(adjunto);
        return result;
    }
}
