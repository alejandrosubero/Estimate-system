package com.jshandyman.service.entitys;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "traker")
public class SendMailTraker extends Auditable  {

    private static final long serialVersionUID = -1663691089507836419L;

    @Id
    @GeneratedValue(generator = "sequence_send_mail_traker_id_generator")
    @SequenceGenerator(name = "sequence_send_mail_traker_id_generator", initialValue = 1, allocationSize = 10000)
    @Column(name = "idSendMail", updatable = true, nullable = false, length = 25)
    private Long idSendMail;

    @Column(name = "sendDay", updatable = true, nullable = true, length = 200)
    private Date sendDay;

    @Column(name = "toSend", updatable = true, nullable = true, length = 1000)
    private String toSend;

    @Column(name = "cc", updatable = true, nullable = true, length = 1000)
    private String cc;

    @Column(name = "bcc", updatable = true, nullable = true, length = 1000)
    private String bcc;

    @Column(name = "id_Estimate_Referene", updatable = true, nullable = true, length = 1000)
    private Long idEstimateReferene;

    @Column(name = "id_Work_Referene", updatable = true, nullable = true, length = 1000)
    private Long idWorkReferene;

    @Column(name = "company", updatable = true, nullable = true, length = 500)
    private String company;

    public SendMailTraker() {
    }


    public SendMailTraker(Date sendDay, String to, String cc, String bcc, String company) {
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
        if(company != null){
            this.company = company;
        }
    }

    public SendMailTraker(Date sendDay, String to, String cc, String bcc) {
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


    public SendMailTraker(String to, String cc, String bcc) {
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
        if (!(o instanceof SendMailTraker)) return false;
        if (!super.equals(o)) return false;
        SendMailTraker that = (SendMailTraker) o;
        return Objects.equals(idSendMail, that.idSendMail) && Objects.equals(sendDay, that.sendDay) && Objects.equals(toSend, that.toSend) && Objects.equals(cc, that.cc) && Objects.equals(bcc, that.bcc) && Objects.equals(idEstimateReferene, that.idEstimateReferene) && Objects.equals(idWorkReferene, that.idWorkReferene);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSendMail, sendDay, toSend, cc, bcc, idEstimateReferene, idWorkReferene);
    }
}
