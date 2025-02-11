package com.jshandyman.service.entitys;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.util.*;

// TODO: SE PUEDE IMPLEMENTAR UNA BIBLIOTECA DE SUBCONTRATISTAS PARA UNA FACIL SELECCION
// Todo:  list - Subcontractor y se alimentara de los sub contractors cargados.

@Entity
@Table(name = "Subcontractor")
@Audited
public class Subcontractor extends Auditable {

    private static final long serialVersionUID = 2056669479011338461L;

    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idSubContractor", updatable = true, nullable = false, length = 25)
    private Long idSubContractor;

    @Column(name = "company", updatable = true, nullable = true, length = 200)
    private String company;

    @Column(name = "codeClient", updatable = true, nullable = true, length = 200)
    private String codeClient;

    @Column(name = "phoneNumber", updatable = true, nullable = true, length = 200)
    private String phoneNumber;

    @Column(name = "mail", updatable = true, nullable = true, length = 200)
    private String mail;

    @Column(name = "costOfwork", updatable = true, nullable = true, length = 200)
    private Double costOfwork;

    @Column(name = "dateOfWork", updatable = true, nullable = true, length = 200)
    private Date dateOfWork;

    @Column(name = "datePain", updatable = true, nullable = true, length = 200)
    private Date datePain;

    @Column(name = "totalCost", updatable = true, nullable = true, length = 200)
    private Double totalCost;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "SubContractor_id", referencedColumnName = "idSubContractor")
    private List<Bill> billListSubcontractor = new ArrayList<Bill>();

    @Column(name = "profit", updatable = true, nullable = true, length = 200)
    private Double profit;

    @Column(name = "work_id")
    private Long idWork;

    @Column(name = "estimate_id")
    private Long idEstimate;

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "itemDeliteEdit", updatable = true, nullable = true, length = 10)
    private boolean itemDeliteEdit;

    @Column(name = "referenceEstimate")
    private Long referenceEstimate;

    public Subcontractor() {
    }

    public Long getReferenceEstimate() {
        return referenceEstimate;
    }

    public void setReferenceEstimate(Long referenceEstimate) {
        this.referenceEstimate = referenceEstimate;
    }

    public boolean isItemDeliteEdit() {
        return itemDeliteEdit;
    }

    public void setItemDeliteEdit(boolean itemDeliteEdit) {
        this.itemDeliteEdit = itemDeliteEdit;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdEstimate() {
        return idEstimate;
    }

    public void setIdEstimate(Long idEstimate) {
        this.idEstimate = idEstimate;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public Long getIdSubContractor() {
        return idSubContractor;
    }

    public void setIdSubContractor(Long idSubContractor) {
        this.idSubContractor = idSubContractor;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getCostOfwork() {
        return costOfwork;
    }

    public void setCostOfwork(Double costOfwork) {
        this.costOfwork = costOfwork;
    }

    public Date getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(Date dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public Date getDatePain() {
        return datePain;
    }

    public void setDatePain(Date datePain) {
        this.datePain = datePain;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Bill> getBillListSubcontractor() {
        return billListSubcontractor;
    }

    public void setBillListSubcontractor(List<Bill> billListSubcontractor) {
        this.billListSubcontractor = billListSubcontractor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subcontractor)) return false;
        if (!super.equals(o)) return false;
        Subcontractor that = (Subcontractor) o;
        return itemDeliteEdit == that.itemDeliteEdit && Objects.equals(idSubContractor, that.idSubContractor) && Objects.equals(company, that.company) && Objects.equals(codeClient, that.codeClient) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(mail, that.mail) && Objects.equals(costOfwork, that.costOfwork) && Objects.equals(dateOfWork, that.dateOfWork) && Objects.equals(datePain, that.datePain) && Objects.equals(totalCost, that.totalCost) && Objects.equals(billListSubcontractor, that.billListSubcontractor) && Objects.equals(profit, that.profit) && Objects.equals(idWork, that.idWork) && Objects.equals(idEstimate, that.idEstimate) && Objects.equals(description, that.description) && Objects.equals(referenceEstimate, that.referenceEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSubContractor, company, codeClient, phoneNumber, mail, costOfwork, dateOfWork, datePain, totalCost, billListSubcontractor, profit, idWork, idEstimate, description, itemDeliteEdit, referenceEstimate);
    }
}
