package com.jshandyman.service.pojo;


import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SendMailTrakerPojo  {


    private Long idSendMail;

    private Date sendDay;

    private String toSend;

    private String cc;

    private String bcc;

    private Long idEstimateReferene;

    private Long idWorkReferene;

    private String company;

    public SendMailTrakerPojo() {
    }


    public SendMailTrakerPojo(List<String>  to, List<String>  cc, List<String>  bcc, Long idEstimateReferene, Long idWorkReferene, String company) {

        if(to != null){
            this.toSend = this.listToString(to);
        }
        if(cc != null){
            this.cc = this.listToString(cc);
        }else{
            this.cc =" ";
        }
        if(bcc != null){
            this.bcc = this.listToString(bcc);
        }else{
            this.bcc =" ";
        }
        this.sendDay = new Date();


        if(idEstimateReferene != null){
            this.idEstimateReferene = idEstimateReferene;
        }

        if( idWorkReferene != null){
            this.idWorkReferene = idWorkReferene;
        }

        if(company != null){
            this.company = company;
        }
    }



    public SendMailTrakerPojo(Date sendDay, String to, String cc, String bcc) {
        if(sendDay != null){
            this.sendDay = sendDay;
        }
        if(to != null){
            this.toSend = to;
        }
        if(cc != null){
            this.cc = cc;
        }
        if(bcc != null){
            this.bcc = bcc;
        }
    }


    public SendMailTrakerPojo(String to, String cc, String bcc) {
        if(to != null){
            this.toSend = to;
        }
        if(cc != null){
            this.cc = cc;
        }
        if(bcc != null){
            this.bcc = bcc;
        }
        this.sendDay = new Date();
    }


    private String listToString(List<String>  list){
        StringBuilder builder = new StringBuilder();
        list.stream().forEach(tex ->builder.append(tex));
        return builder.toString();
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getIdSendMail() {
        return idSendMail;
    }

    public void setIdSendMail(Long idSendMail) {
        this.idSendMail = idSendMail;
    }

    public Date getSendDay() {
        return sendDay;
    }

    public void setSendDay(Date sendDay) {
        this.sendDay = sendDay;
    }

    public String getTo() {
        return toSend;
    }

    public void setTo(String to) {
        this.toSend = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public Long getIdEstimateReferene() {
        return idEstimateReferene;
    }

    public void setIdEstimateReferene(Long idEstimateReferene) {
        this.idEstimateReferene = idEstimateReferene;
    }

    public Long getIdWorkReferene() {
        return idWorkReferene;
    }

    public void setIdWorkReferene(Long idWorkReferene) {
        this.idWorkReferene = idWorkReferene;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SendMailTrakerPojo)) return false;
        SendMailTrakerPojo that = (SendMailTrakerPojo) o;
        return Objects.equals(idSendMail, that.idSendMail) && Objects.equals(sendDay, that.sendDay) && Objects.equals(toSend, that.toSend) && Objects.equals(cc, that.cc) && Objects.equals(bcc, that.bcc) && Objects.equals(idEstimateReferene, that.idEstimateReferene) && Objects.equals(idWorkReferene, that.idWorkReferene);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSendMail, sendDay, toSend, cc, bcc, idEstimateReferene, idWorkReferene);
    }
}
