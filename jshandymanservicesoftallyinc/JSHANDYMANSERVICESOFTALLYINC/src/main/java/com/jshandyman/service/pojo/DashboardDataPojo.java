package com.jshandyman.service.pojo;

import com.jshandyman.service.entitys.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardDataPojo {

    private Long numberWorksCreate;
    private Long worksCreateInTheYear;
    private Long worksCreateInTheMonth;
    private Long numberWorksStarted;
    private Long numberWorksEnd;
    private Long numberWorksInProgress;
    private Long numberWorksApproved;
    private Long numberWorksSend;
    private Long numberWorksEndBeforeDedLine;
    private Long numberWorksEndAfterDedLine;
    private Double totalInvoicedInTheYear;
    private Double totalAmountPaindIntYear;
    private Double totalRemainingPayableInTheYear;
    private Long  workFinalDateInMonth;
    private Long  workFinalDateInYear;
    private Long numberEstimatesCreate;
    private Long estimatesCreateInTheYear;
    private Long estimatesCreateInTheMonth;
    private Long numberEstimatesApproved;
    private Long numberEstimatesSend;
    private Long numberEstimatesInProgress;
    private Double totalEstimateApprovedInYear;
    private Long numberWorksInPause;
    private List<WorkListTabletPojo> worksEndBeforeDedLine = new ArrayList<>();
    private List<WorkListTabletPojo> worksBeforeDedLineAlert = new ArrayList<>();
    private List<WorkListTabletPojo> listAmountPaind = new ArrayList<>();
    private List<WorkListTabletPojo> listRemainingPayable = new ArrayList<>();
    private List<WorkListTabletPojo> listInvoiceInYear = new ArrayList<>();
    private List<WorkListTabletPojo> listWorksSend = new ArrayList<>();
    private List<EstimateListTabletPojo> listEstimatesSend = new ArrayList<>();
    private List<WorkListTabletPojo> listWorksFinalices = new ArrayList<>();
    private List<WorkListTabletPojo> listWorksInProgress = new ArrayList<>();
    private List<WorkListTabletPojo> listWorksPause = new ArrayList<>();

    public DashboardDataPojo() {
    }

    public List<WorkListTabletPojo> getListWorksPause() {
        return listWorksPause;
    }

    public void setListWorksPause(List<WorkListTabletPojo> listWorksPause) {
        this.listWorksPause = listWorksPause;
    }

    public List<WorkListTabletPojo> getListWorksInProgress() {
        return listWorksInProgress;
    }

    public void setListWorksInProgress(List<WorkListTabletPojo> listWorksInProgress) {
        this.listWorksInProgress = listWorksInProgress;
    }

    public List<WorkListTabletPojo> getListWorksFinalices() {
        return listWorksFinalices;
    }

    public void setListWorksFinalices(List<WorkListTabletPojo> listWorksFinalices) {
        this.listWorksFinalices = listWorksFinalices;
    }

    public List<WorkListTabletPojo> getListAmountPaind() {
        return listAmountPaind;
    }

    public void setListAmountPaind(List<WorkListTabletPojo> listAmountPaind) {
        this.listAmountPaind = listAmountPaind;
    }

    public List<WorkListTabletPojo> getListRemainingPayable() {
        return listRemainingPayable;
    }

    public void setListRemainingPayable(List<WorkListTabletPojo> listRemainingPayable) {
        this.listRemainingPayable = listRemainingPayable;
    }

    public List<WorkListTabletPojo> getListInvoiceInYear() {
        return listInvoiceInYear;
    }

    public void setListInvoiceInYear(List<WorkListTabletPojo> listInvoiceInYear) {
        this.listInvoiceInYear = listInvoiceInYear;
    }

    public List<WorkListTabletPojo> getListWorksSend() {
        return listWorksSend;
    }

    public void setListWorksSend(List<WorkListTabletPojo> listWorksSend) {
        this.listWorksSend = listWorksSend;
    }

    public List<EstimateListTabletPojo> getListEstimatesSend() {
        return listEstimatesSend;
    }

    public void setListEstimatesSend(List<EstimateListTabletPojo> listEstimatesSend) {
        this.listEstimatesSend = listEstimatesSend;
    }

    public Double getTotalEstimateApprovedInYear() {
        return totalEstimateApprovedInYear;
    }

    public void setTotalEstimateApprovedInYear(Double totalEstimateApprovedInYear) {
        this.totalEstimateApprovedInYear = totalEstimateApprovedInYear;
    }

    public Double getTotalRemainingPayableInTheYear() {
        return totalRemainingPayableInTheYear;
    }

    public void setTotalRemainingPayableInTheYear(Double totalRemainingPayableInTheYear) {
        this.totalRemainingPayableInTheYear = totalRemainingPayableInTheYear;
    }

    public Long getNumberWorksInPause() {
        return numberWorksInPause;
    }

    public void setNumberWorksInPause(Long numberWorksInPause) {
        this.numberWorksInPause = numberWorksInPause;
    }

    public Long getWorkFinalDateInMonth() {
        return workFinalDateInMonth;
    }

    public void setWorkFinalDateInMonth(Long workFinalDateInMonth) {
        this.workFinalDateInMonth = workFinalDateInMonth;
    }

    public Long getWorkFinalDateInYear() {
        return workFinalDateInYear;
    }

    public void setWorkFinalDateInYear(Long workFinalDateInYear) {
        this.workFinalDateInYear = workFinalDateInYear;
    }

    public Double getTotalInvoicedInTheYear() {
        return totalInvoicedInTheYear;
    }

    public void setTotalInvoicedInTheYear(Double totalInvoicedInTheYear) {
        this.totalInvoicedInTheYear = totalInvoicedInTheYear;
    }

    public List<WorkListTabletPojo> getWorksEndBeforeDedLine() {
        return worksEndBeforeDedLine;
    }

    public void setWorksEndBeforeDedLine(List<WorkListTabletPojo> worksEndBeforeDedLine) {
        this.worksEndBeforeDedLine = worksEndBeforeDedLine;
    }

    public List<WorkListTabletPojo> getWorksBeforeDedLineAlert() {
        return worksBeforeDedLineAlert;
    }

    public void setWorksBeforeDedLineAlert(List<WorkListTabletPojo> worksBeforeDedLineAlert) {
        this.worksBeforeDedLineAlert = worksBeforeDedLineAlert;
    }

    public Long getNumberWorksEndAfterDedLine() {
        return numberWorksEndAfterDedLine;
    }

    public void setNumberWorksEndAfterDedLine(Long numberWorksEndAfterDedLine) {
        this.numberWorksEndAfterDedLine = numberWorksEndAfterDedLine;
    }

    public Long getEstimatesCreateInTheYear() {
        return estimatesCreateInTheYear;
    }

    public void setEstimatesCreateInTheYear(Long estimatesCreateInTheYear) {
        this.estimatesCreateInTheYear = estimatesCreateInTheYear;
    }

    public Long getEstimatesCreateInTheMonth() {
        return estimatesCreateInTheMonth;
    }

    public void setEstimatesCreateInTheMonth(Long estimatesCreateInTheMonth) {
        this.estimatesCreateInTheMonth = estimatesCreateInTheMonth;
    }

    public Long getNumberEstimatesInProgress() {
        return numberEstimatesInProgress;
    }

    public void setNumberEstimatesInProgress(Long numberEstimatesInProgress) {
        this.numberEstimatesInProgress = numberEstimatesInProgress;
    }

    public Long getNumberWorksCreate() {
        return numberWorksCreate;
    }

    public void setNumberWorksCreate(Long numberWorksCreate) {
        this.numberWorksCreate = numberWorksCreate;
    }

    public Long getWorksCreateInTheYear() {
        return worksCreateInTheYear;
    }

    public void setWorksCreateInTheYear(Long worksCreateInTheYear) {
        this.worksCreateInTheYear = worksCreateInTheYear;
    }

    public Long getWorksCreateInTheMonth() {
        return worksCreateInTheMonth;
    }

    public void setWorksCreateInTheMonth(Long worksCreateInTheMonth) {
        this.worksCreateInTheMonth = worksCreateInTheMonth;
    }

    public Long getNumberWorksStarted() {
        return numberWorksStarted;
    }

    public void setNumberWorksStarted(Long numberWorksStarted) {
        this.numberWorksStarted = numberWorksStarted;
    }

    public Long getNumberWorksEnd() {
        return numberWorksEnd;
    }

    public void setNumberWorksEnd(Long numberWorksEnd) {
        this.numberWorksEnd = numberWorksEnd;
    }

    public Long getNumberWorksInProgress() {
        return numberWorksInProgress;
    }

    public void setNumberWorksInProgress(Long numberWorksInProgress) {
        this.numberWorksInProgress = numberWorksInProgress;
    }

    public Long getNumberWorksApproved() {
        return numberWorksApproved;
    }

    public void setNumberWorksApproved(Long numberWorksApproved) {
        this.numberWorksApproved = numberWorksApproved;
    }

    public Long getNumberWorksSend() {
        return numberWorksSend;
    }

    public void setNumberWorksSend(Long numberWorksSend) {
        this.numberWorksSend = numberWorksSend;
    }

    public Long getNumberWorksEndBeforeDedLine() {
        return numberWorksEndBeforeDedLine;
    }

    public void setNumberWorksEndBeforeDedLine(Long numberWorksEndBeforeDedLine) {
        this.numberWorksEndBeforeDedLine = numberWorksEndBeforeDedLine;
    }

    public Double getTotalAmountPaindIntYear() {
        return totalAmountPaindIntYear;
    }

    public void setTotalAmountPaindIntYear(Double totalAmountPaindIntYear) {
        this.totalAmountPaindIntYear = totalAmountPaindIntYear;
    }



    public Long getNumberEstimatesCreate() {
        return numberEstimatesCreate;
    }

    public void setNumberEstimatesCreate(Long numberEstimatesCreate) {
        this.numberEstimatesCreate = numberEstimatesCreate;
    }

    public Long getNumberEstimatesApproved() {
        return numberEstimatesApproved;
    }

    public void setNumberEstimatesApproved(Long numberEstimatesApproved) {
        this.numberEstimatesApproved = numberEstimatesApproved;
    }

    public Long getNumberEstimatesSend() {
        return numberEstimatesSend;
    }

    public void setNumberEstimatesSend(Long numberEstimatesSend) {
        this.numberEstimatesSend = numberEstimatesSend;
    }
}
