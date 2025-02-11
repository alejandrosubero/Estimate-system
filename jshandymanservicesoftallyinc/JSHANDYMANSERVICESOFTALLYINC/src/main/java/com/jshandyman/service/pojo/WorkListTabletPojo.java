package com.jshandyman.service.pojo;


import java.util.Date;
import java.util.Objects;

public class WorkListTabletPojo {

    private Long idWork;
    private Long idEstimate;
    private Long daysToDeline;
    private Long daysLate;

    private String owner;
    private String title;
    private String description;
    private String status;

    private Double totalCostWork;
    private Double remainingPayable;
    private Double totalAmountPaind;

    private Date createDay;
    private Date starDate;
    private Date finalDate;


    public WorkListTabletPojo() {
    }


    public WorkListTabletPojo(String owner, Long idWork, String title,
                              String description, Double totalCostWork, Date createDay,
                              Date starDate, Long daysToDeline, Long daysLate, Long idEstimate, String status,
                              Double totalAmountPaind, Double remainingPayable, Date finalDate) {

        if(owner!= null){ this.owner = owner;}

        if(idWork!= null){ this.idWork = idWork;}

        if(title!= null){this.title = title; }

        if(description!= null){this.description = description; }

        if(totalCostWork!= null){this.totalCostWork = totalCostWork; }

        if(createDay!= null){this.createDay = createDay;}

        if(starDate!= null){this.starDate = starDate;}

        if(daysToDeline!= null){ this.daysToDeline = daysToDeline; }

        if(daysLate!= null){this.daysLate = daysLate; }

        if(idEstimate!= null){this.idEstimate = idEstimate; }

        if(status!= null){this.status = status; }

        if(finalDate!= null){this.finalDate = finalDate; }

        if(remainingPayable!= null){this.remainingPayable = remainingPayable; }

        if(totalAmountPaind!= null){this.totalAmountPaind = totalAmountPaind; }

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

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getIdWork() {
        return idWork;
    }

    public void setIdWork(Long idWork) {
        this.idWork = idWork;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getIdEstimate() {
        return idEstimate;
    }

    public void setIdEstimate(Long idEstimate) {
        this.idEstimate = idEstimate;
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
        if (!(o instanceof WorkListTabletPojo)) return false;
        WorkListTabletPojo that = (WorkListTabletPojo) o;
        return Objects.equals(idWork, that.idWork) && Objects.equals(idEstimate, that.idEstimate) && Objects.equals(daysToDeline, that.daysToDeline) && Objects.equals(daysLate, that.daysLate) && Objects.equals(owner, that.owner) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(status, that.status) && Objects.equals(totalCostWork, that.totalCostWork) && Objects.equals(remainingPayable, that.remainingPayable) && Objects.equals(totalAmountPaind, that.totalAmountPaind) && Objects.equals(createDay, that.createDay) && Objects.equals(starDate, that.starDate) && Objects.equals(finalDate, that.finalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWork, idEstimate, daysToDeline, daysLate, owner, title, description, status, totalCostWork, remainingPayable, totalAmountPaind, createDay, starDate, finalDate);
    }
}
