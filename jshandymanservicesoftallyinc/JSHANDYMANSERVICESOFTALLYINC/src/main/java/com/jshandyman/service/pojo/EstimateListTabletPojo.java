package com.jshandyman.service.pojo;


import java.util.Date;
import java.util.Objects;

public class EstimateListTabletPojo  {

    private Long idEstimate;
    private String title;
    private String owner;
    private Date createDay;
    private String description;
    private Double totalCostWork;
    private String status;


    public EstimateListTabletPojo() {
    }

    public EstimateListTabletPojo( Long idEstimate, Double totalCostWork , Date createDay, String owner, String title, String description,String status) {
        this.owner = owner;
        this.idEstimate = idEstimate;
        this.title = title;
        this.description = description;
        this.totalCostWork = totalCostWork;
        this.createDay = createDay;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalCostWork() {
        return totalCostWork;
    }

    public void setTotalCostWork(Double totalCostWork) {
        this.totalCostWork = totalCostWork;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstimateListTabletPojo)) return false;
        EstimateListTabletPojo that = (EstimateListTabletPojo) o;
        return Objects.equals(idEstimate, that.idEstimate) && Objects.equals(title, that.title) && Objects.equals(owner, that.owner) && Objects.equals(createDay, that.createDay) && Objects.equals(description, that.description) && Objects.equals(totalCostWork, that.totalCostWork) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstimate, title, owner, createDay, description, totalCostWork, status);
    }
}
