package com.service.mail.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EmailsPojo {

    private List<String> to = new ArrayList<String>();
    private List<String> cc = new ArrayList<String>();
    private List<String> bcc= new ArrayList<String>();

    private String from;
    private String subject;
    private String content;
    private String body;
    private byte[] adjunto;

    public EmailsPojo() {
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(byte[] adjunto) {
        this.adjunto = adjunto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailsPojo)) return false;
        EmailsPojo that = (EmailsPojo) o;
        return Objects.equals(to, that.to) && Objects.equals(cc, that.cc) && Objects.equals(bcc, that.bcc) && Objects.equals(from, that.from) && Objects.equals(subject, that.subject) && Objects.equals(content, that.content) && Objects.equals(body, that.body) && Arrays.equals(adjunto, that.adjunto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(to, cc, bcc, from, subject, content, body);
        result = 31 * result + Arrays.hashCode(adjunto);
        return result;
    }
}
