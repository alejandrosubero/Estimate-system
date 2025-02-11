package com.jshandyman.service.entitys;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estimate")
@Audited
public class Estimate extends Auditable {

    

    @Id
    @GeneratedValue(generator = "sequence_estimate_id_generator")
    @SequenceGenerator(name = "sequence_estimate_id_generator", initialValue = 1, allocationSize = 10000)
    @Column(name = "idEstimate", updatable = true, nullable = false, length = 25)
    private Long idEstimate;

    @Column(name = "title", updatable = true, nullable = true, length = 200)
    private String title;

    @Column(name = "codeWork", updatable = true, nullable = true, length = 200)
    private String codeWork;

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "dedline", updatable = true, nullable = true, length = 200)
    private Date dedline;

    @Column(name = "createDay", updatable = true, nullable = true)
    private Date createDay;

    @Column(name = "StarDate", updatable = true, nullable = true, length = 200)
    private Date starDate;

    @Column(name = "daysToDeline", updatable = true, nullable = true, length = 200)
    private Long daysToDeline;

    @Column(name = "daysLate", updatable = true, nullable = true, length = 200)
    private Long daysLate;

    @Column(name = "totalCostWorkWithoutTaxes", updatable = true, nullable = true, length = 200)
    private Double totalCostWorkWithoutTaxes;

    @Column(name = "totalCostWork", updatable = true, nullable = true, length = 200)
    private Double totalCostWork;

    @Column(name = "remainingPayable", updatable = true, nullable = true, length = 200)
    private Double remainingPayable;

    @Column(name = "totalAmountPaind", updatable = true, nullable = true, length = 200)
    private Double totalAmountPaind;

    @Column(name = "overhead", updatable = true, nullable = true, length = 200)
    private Double overhead;

    @Column(name = "owner", updatable = true, nullable = true, length = 200)
    private String owner;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "estimate_id", referencedColumnName = "idEstimate")
    private List<Payment> payments = new ArrayList<Payment>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "estimate_id", referencedColumnName = "idEstimate")
    private List<Bill> bills = new ArrayList<Bill>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "estimate_id", referencedColumnName = "idEstimate")
    private List<Subcontractor> subcontractors = new ArrayList<Subcontractor>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "estimate_id", referencedColumnName = "idEstimate")
    private List<ServiceHandyManTally> services = new ArrayList<ServiceHandyManTally>();

    @Column(name = "active", updatable = true, nullable = true, length = 200)
    private Boolean active;

    @Column(name = "status", updatable = true, nullable = true, length = 200)
    private String status;

    @Column(name = "budgetNumber", updatable = true, nullable = true, length = 200)
    private Long budgetNumber;

    @Column(name = "company", updatable = true, nullable = true, length = 500)
    private String company;

    public Estimate() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getBudgetNumber() {
        return budgetNumber;
    }

    public void setBudgetNumber(Long budgetNumber) {
        this.budgetNumber = budgetNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdEstimate() {
        return idEstimate;
    }

    public void setIdEstimate(Long idEstimate) {
        this.idEstimate = idEstimate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCodeWork() {
        return codeWork;
    }

    public void setCodeWork(String codeWork) {
        this.codeWork = codeWork;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDedline() {
        return dedline;
    }

    public void setDedline(Date dedline) {
        this.dedline = dedline;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Long getDaysToDeline() {
        return daysToDeline;
    }

    public void setDaysToDeline(Long daysToDeline) {
        this.daysToDeline = daysToDeline;
    }

    public Long getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(Long daysLate) {
        this.daysLate = daysLate;
    }

    public Double getTotalCostWorkWithoutTaxes() {
        return totalCostWorkWithoutTaxes;
    }

    public void setTotalCostWorkWithoutTaxes(Double totalCostWorkWithoutTaxes) {
        this.totalCostWorkWithoutTaxes = totalCostWorkWithoutTaxes;
    }

    public Double getTotalCostWork() {
        return totalCostWork;
    }

    public void setTotalCostWork(Double totalCostWork) {
        this.totalCostWork = totalCostWork;
    }

    public Double getRemainingPayable() {
        return remainingPayable;
    }

    public void setRemainingPayable(Double remainingPayable) {
        this.remainingPayable = remainingPayable;
    }

    public Double getTotalAmountPaind() {
        return totalAmountPaind;
    }

    public void setTotalAmountPaind(Double totalAmountPaind) {
        this.totalAmountPaind = totalAmountPaind;
    }

    public Double getOverhead() {
        return overhead;
    }

    public void setOverhead(Double overhead) {
        this.overhead = overhead;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Subcontractor> getSubcontractors() {
        return subcontractors;
    }

    public void setSubcontractors(List<Subcontractor> subcontractors) {
        this.subcontractors = subcontractors;
    }

    public List<ServiceHandyManTally> getServices() {
        return services;
    }

    public void setServices(List<ServiceHandyManTally> services) {
        this.services = services;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Estimate estimate = (Estimate) o;
        return Objects.equals(idEstimate, estimate.idEstimate) && Objects.equals(title, estimate.title) && Objects.equals(codeWork, estimate.codeWork) && Objects.equals(description, estimate.description) && Objects.equals(dedline, estimate.dedline) && Objects.equals(createDay, estimate.createDay) && Objects.equals(starDate, estimate.starDate) && Objects.equals(daysToDeline, estimate.daysToDeline) && Objects.equals(daysLate, estimate.daysLate) && Objects.equals(totalCostWorkWithoutTaxes, estimate.totalCostWorkWithoutTaxes) && Objects.equals(totalCostWork, estimate.totalCostWork) && Objects.equals(remainingPayable, estimate.remainingPayable) && Objects.equals(totalAmountPaind, estimate.totalAmountPaind) && Objects.equals(overhead, estimate.overhead) && Objects.equals(owner, estimate.owner) && Objects.equals(client, estimate.client) && Objects.equals(payments, estimate.payments) && Objects.equals(bills, estimate.bills) && Objects.equals(subcontractors, estimate.subcontractors) && Objects.equals(services, estimate.services) && Objects.equals(active, estimate.active) && Objects.equals(status, estimate.status) && Objects.equals(budgetNumber, estimate.budgetNumber) && Objects.equals(company, estimate.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idEstimate, title, codeWork, description, dedline, createDay, starDate, daysToDeline, daysLate, totalCostWorkWithoutTaxes, totalCostWork, remainingPayable, totalAmountPaind, overhead, owner, client, payments, bills, subcontractors, services, active, status, budgetNumber, company);
    }
}
