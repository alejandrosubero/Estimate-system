package com.jshandyman.service.entitys;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "serviceHandyManTally")
@Audited
public class ServiceHandyManTally extends Auditable  {

    private static final long serialVersionUID = -887170061181593659L;

    @Id
    @GeneratedValue(generator = "sequence_services_id_generator")
    @SequenceGenerator(name = "sequence_services_id_generator", initialValue = 1, allocationSize = 10000)
    @Column(name = "idServices", updatable = true, nullable = false, length = 25)
    private Long idServices;

    @Column(name = "servicesCost", updatable = true, nullable = false, length = 200)
    private Double servicesCost;

    @Column(name = "descriptionOfServicesCost", updatable = true, nullable = false, length = 2000)
    private String descriptionOfServicesCost;

    @Column(name = "overhead", updatable = true, nullable = true, length = 200)
    private Double overhead;

    @Column(name = "work_id")
    private Long idWork;

    @Column(name = "estimate_id")
    private Long idEstimate;

    @Column(name = "itemDeliteEdit", updatable = true, nullable = true, length = 10)
    private boolean itemDeliteEdit;

    @Column(name = "referenceEstimate")
    private Long referenceEstimate;


    public ServiceHandyManTally() {
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

    public Long getIdEstimate() {
        return idEstimate;
    }

    public void setIdEstimate(Long idEstimate) {
        this.idEstimate = idEstimate;
    }

    public Long getIdServices() {
        return idServices;
    }

    public void setIdServices(Long idServices) {
        this.idServices = idServices;
    }

    public Double getServicesCost() {
        return servicesCost;
    }

    public void setServicesCost(Double servicesCost) {
        this.servicesCost = servicesCost;
    }

    public String getDescriptionOfServicesCost() {
        return descriptionOfServicesCost;
    }

    public void setDescriptionOfServicesCost(String descriptionOfServicesCost) {
        this.descriptionOfServicesCost = descriptionOfServicesCost;
    }

    public Double getOverhead() {
        return overhead;
    }

    public void setOverhead(Double overhead) {
        this.overhead = overhead;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceHandyManTally)) return false;
        if (!super.equals(o)) return false;
        ServiceHandyManTally that = (ServiceHandyManTally) o;
        return itemDeliteEdit == that.itemDeliteEdit && Objects.equals(idServices, that.idServices) && Objects.equals(servicesCost, that.servicesCost) && Objects.equals(descriptionOfServicesCost, that.descriptionOfServicesCost) && Objects.equals(overhead, that.overhead) && Objects.equals(idWork, that.idWork) && Objects.equals(idEstimate, that.idEstimate) && Objects.equals(referenceEstimate, that.referenceEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idServices, servicesCost, descriptionOfServicesCost, overhead, idWork, idEstimate, itemDeliteEdit, referenceEstimate);
    }
}
