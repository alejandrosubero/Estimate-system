package com.jshandyman.service.pojo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SubcontractorPojo  {

    private Long idSubContractor;
    private String company;
    private String codeClient;
    private String phoneNumber;
    private String mail;
    private Double costOfwork;
    private Date dateOfWork;
    private Date datePain;
    private Double totalCost;
    private List<BillPojo> billListSubcontractor = new ArrayList<BillPojo>();
    private Double profit;
    private Long idWork;
    private Long idEstimate;
    private String description;
    private Boolean itemDeliteEdit;
    private Long referenceEstimate;

    public SubcontractorPojo() {
    }

    public SubcontractorPojo(String company, String codeClient, String phoneNumber, String mail,
                             Double costOfwork, Date dateOfWork, Date datePain, Double totalCost,
                             List<BillPojo> billListSubcontractor, Double profit, Long idWork, Long idEstimate,
                             String description, Boolean itemDeliteEdit) {
      if(company !=null)
        this.company = company;

        if(codeClient !=null)
        this.codeClient = codeClient;

        if(phoneNumber !=null)
        this.phoneNumber = phoneNumber;

        if( mail !=null)
        this.mail = mail;

        if( costOfwork!=null)
        this.costOfwork = costOfwork;

        if(dateOfWork !=null)
        this.dateOfWork = dateOfWork;

        if(datePain !=null)
        this.datePain = datePain;

        if(totalCost !=null)
        this.totalCost = totalCost;

        if(billListSubcontractor !=null)
        this.billListSubcontractor = billListSubcontractor;

        if(profit !=null)
        this.profit = profit;

        if(idWork !=null)
        this.idWork = idWork;

        if(idEstimate !=null)
        this.idEstimate = idEstimate;

        if(description !=null)
        this.description = description;

        if(itemDeliteEdit !=null){
            this.itemDeliteEdit = itemDeliteEdit;
        }
    }


    public SubcontractorPojo(SubcontractorPojo oterSubcontractor ) {
        if(oterSubcontractor != null){
            if(oterSubcontractor.getCompany() !=null)
                this.company = oterSubcontractor.getCompany();

            if(oterSubcontractor.getCodeClient() !=null)
                this.codeClient = oterSubcontractor.getCodeClient();

            if(oterSubcontractor.getPhoneNumber() !=null)
                this.phoneNumber = oterSubcontractor.getPhoneNumber();

            if( oterSubcontractor.getMail() !=null)
                this.mail =  oterSubcontractor.getMail();

            if( oterSubcontractor.getCostOfwork()!=null)
                this.costOfwork =  oterSubcontractor.getCostOfwork();

            if(oterSubcontractor.getDateOfWork() !=null)
                this.dateOfWork = oterSubcontractor.getDateOfWork();

            if(oterSubcontractor.getDatePain() !=null)
                this.datePain = oterSubcontractor.getDatePain();

            if(oterSubcontractor.getTotalCost() !=null)
                this.totalCost = oterSubcontractor.getTotalCost();

            if(oterSubcontractor.getProfit() !=null)
                this.profit = oterSubcontractor.getProfit();

            if(oterSubcontractor.getIdWork() !=null)
                this.idWork = oterSubcontractor.getIdWork();

//            if(oterSubcontractor.getIdEstimate() !=null)
//                this.idEstimate = oterSubcontractor.getIdEstimate();

            if(oterSubcontractor.getReferenceEstimate() == null)
                this.referenceEstimate = oterSubcontractor.getIdEstimate();


            if(oterSubcontractor.getDescription() !=null)
                this.description = oterSubcontractor.getDescription();

            if(oterSubcontractor.isItemDeliteEdit() !=null){
                this.itemDeliteEdit = oterSubcontractor.isItemDeliteEdit();
            }

            if(oterSubcontractor.getBillListSubcontractor() !=null){
                if(oterSubcontractor.getBillListSubcontractor().size() >0){
                    this.billListSubcontractor = this.cloneListBillPojo(oterSubcontractor.getBillListSubcontractor());
                }else {
                    this.billListSubcontractor =  new ArrayList<BillPojo>();;
                }
            }
        }
    }


    private List<BillPojo>  cloneListBillPojo(List<BillPojo> billListSubcontractor){
        List<BillPojo> billList = new ArrayList<BillPojo>();
        billListSubcontractor.stream().forEach(bill -> billList.add(new BillPojo(bill)));
        return billList;
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

    public List<BillPojo> getBillListSubcontractor() {
        return billListSubcontractor;
    }

    public void setBillListSubcontractor(List<BillPojo> billListSubcontractor) {
        this.billListSubcontractor = billListSubcontractor;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof SubcontractorPojo)) return false;
        SubcontractorPojo that = (SubcontractorPojo) o;
        return Objects.equals(idSubContractor, that.idSubContractor) && Objects.equals(company, that.company) && Objects.equals(codeClient, that.codeClient) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(mail, that.mail) && Objects.equals(costOfwork, that.costOfwork) && Objects.equals(dateOfWork, that.dateOfWork) && Objects.equals(datePain, that.datePain) && Objects.equals(totalCost, that.totalCost) && Objects.equals(billListSubcontractor, that.billListSubcontractor) && Objects.equals(profit, that.profit) && Objects.equals(idWork, that.idWork) && Objects.equals(idEstimate, that.idEstimate) && Objects.equals(description, that.description) && Objects.equals(itemDeliteEdit, that.itemDeliteEdit) && Objects.equals(referenceEstimate, that.referenceEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubContractor, company, codeClient, phoneNumber, mail, costOfwork, dateOfWork, datePain, totalCost, billListSubcontractor, profit, idWork, idEstimate, description, itemDeliteEdit, referenceEstimate);
    }
}
