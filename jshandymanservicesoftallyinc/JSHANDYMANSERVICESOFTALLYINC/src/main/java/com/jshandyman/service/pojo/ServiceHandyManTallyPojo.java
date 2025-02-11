package com.jshandyman.service.pojo;

import java.util.Objects;

public class ServiceHandyManTallyPojo {

  
    private Long idServices;
    private Double servicesCost;
    private String descriptionOfServicesCost;
    private Double overhead;
    private Long idWork;
    private Long idEstimate;
    private Boolean itemDeliteEdit;
    private Long referenceEstimate;


    public ServiceHandyManTallyPojo() {
    }


    public ServiceHandyManTallyPojo(Double servicesCost, String descriptionOfServicesCost, Double overhead, Long idWork, Long idEstimate, Boolean itemDeliteEdit) {

        if(servicesCost !=null)
        this.servicesCost = servicesCost;

        if(descriptionOfServicesCost !=null)
        this.descriptionOfServicesCost = descriptionOfServicesCost;

        if(overhead !=null)
        this.overhead = overhead;

        if(idWork !=null)
        this.idWork = idWork;

        if(idEstimate !=null)
        this.idEstimate = idEstimate;

        if(itemDeliteEdit !=null){
            this.itemDeliteEdit = itemDeliteEdit;
        }
    }

    public ServiceHandyManTallyPojo(ServiceHandyManTallyPojo oterHandyManTally ) {

        if(oterHandyManTally != null){
            if(oterHandyManTally.getServicesCost() !=null){
                this.servicesCost = oterHandyManTally.getServicesCost();
            }

            if(oterHandyManTally.getDescriptionOfServicesCost() !=null){
                this.descriptionOfServicesCost = oterHandyManTally.getDescriptionOfServicesCost();
            }

            if(oterHandyManTally.getOverhead() !=null){
                this.overhead = oterHandyManTally.getOverhead();
            }

            if(oterHandyManTally.getIdWork() !=null){
                this.idWork = oterHandyManTally.getIdWork();
            }

            if(oterHandyManTally.getReferenceEstimate() == null){
                this.referenceEstimate = oterHandyManTally.getIdEstimate();
            }

//            if(oterHandyManTally.getIdEstimate() !=null){
//                this.idEstimate = oterHandyManTally.getIdEstimate();
//            }

            if(oterHandyManTally.isItemDeliteEdit() !=null){
                this.itemDeliteEdit = oterHandyManTally.isItemDeliteEdit();
            }
        }

    }

    public Long getReferenceEstimate() {
        return referenceEstimate;
    }

    public void setReferenceEstimate(Long referenceEstimate) {
        this.referenceEstimate = referenceEstimate;
    }

    public Boolean isItemDeliteEdit() {
        return itemDeliteEdit;
    }

    public void setItemDeliteEdit(Boolean itemDeliteEdit) {
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
        if (!(o instanceof ServiceHandyManTallyPojo)) return false;
        ServiceHandyManTallyPojo that = (ServiceHandyManTallyPojo) o;
        return Objects.equals(idServices, that.idServices) && Objects.equals(servicesCost, that.servicesCost) && Objects.equals(descriptionOfServicesCost, that.descriptionOfServicesCost) && Objects.equals(overhead, that.overhead) && Objects.equals(idWork, that.idWork) && Objects.equals(idEstimate, that.idEstimate) && Objects.equals(itemDeliteEdit, that.itemDeliteEdit) && Objects.equals(referenceEstimate, that.referenceEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idServices, servicesCost, descriptionOfServicesCost, overhead, idWork, idEstimate, itemDeliteEdit, referenceEstimate);
    }
}
