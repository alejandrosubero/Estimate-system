package com.jshandyman.service.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EstimatePojo {

    private Long idEstimate;
    private String codeWork;
    private String description;
    private String title;
    private Date createDay;
    private Date dedline;
    private String owner;
    private Date starDate;
    private Long daysToDeline;
    private Long daysLate;
    private Double totalCostWorkWithoutTaxes;
    private Double totalCostWork;
    private Double remainingPayable;
    private Double totalAmountPaind;
    private Double overhead;
    private Boolean active;
    private ClientPojo client;
    private List<PaymentPojo> payments = new ArrayList<PaymentPojo>();
    private List<BillPojo> bills = new ArrayList<BillPojo>();
    private List<SubcontractorPojo> subcontractors = new ArrayList<SubcontractorPojo>();
    private List<ServiceHandyManTallyPojo> services = new ArrayList<ServiceHandyManTallyPojo>();
    private String status;
    private Long budgetNumber;

    private String company;

    public EstimatePojo() {
    }

    public EstimatePojo(String codeWork, String description, Date dedline, Date starDate, Long daysToDeline,
                        Long daysLate, Double totalCostWorkWithoutTaxes, Double totalCostWork, Double remainingPayable,
                        Double totalAmountPaind, Double overhead, ClientPojo client, List<PaymentPojo> payments,
                        List<BillPojo> bills, List<SubcontractorPojo> subcontractors, List<ServiceHandyManTallyPojo> services,
                        String title, String owner, String status) {


        if(owner != null){
            this.owner = owner;
        }

       if(title != null)
         this.title = title;

      if(codeWork !=null)
        this.codeWork = codeWork;

        if(description !=null)
      this.description = description;

        if(dedline !=null)
        this.dedline = dedline;

        if(starDate !=null)
        this.starDate = starDate;

        if(daysToDeline !=null)
        this.daysToDeline = daysToDeline;

        if(daysLate !=null)
        this.daysLate = daysLate;

        if(totalCostWorkWithoutTaxes !=null)
        this.totalCostWorkWithoutTaxes = totalCostWorkWithoutTaxes;

        if(totalCostWork !=null)
        this.totalCostWork = totalCostWork;

        if(remainingPayable !=null)
        this.remainingPayable = remainingPayable;

        if(totalAmountPaind !=null)
        this.totalAmountPaind = totalAmountPaind;

        if(overhead !=null)
        this.overhead = overhead;

        if(client !=null)
        this.client = client;

        if(payments !=null)
        this.payments = payments;

        if(bills !=null)
        this.bills = bills;

        if(subcontractors !=null)
        this.subcontractors = subcontractors;

        if(services !=null){
           this.services = services;
        }

        if(status !=null){
            this.status = status;
        }

        this.active = true;
    }



    public EstimatePojo(EstimatePojo oterEstimatePojo) {

        if(oterEstimatePojo != null){
            if(oterEstimatePojo.getOwner() != null){
                this.owner = oterEstimatePojo.getOwner();
            }

            if(oterEstimatePojo.getTitle() != null){
                this.title = oterEstimatePojo.getTitle();
            }

            if(oterEstimatePojo.getCodeWork() !=null){
                this.codeWork = oterEstimatePojo.getCodeWork();
            }

            if(oterEstimatePojo.getDescription() !=null){
                this.description = oterEstimatePojo.getDescription();
            }

            if(oterEstimatePojo.getDedline() !=null){
                this.dedline = oterEstimatePojo.getDedline();
            }

            if(oterEstimatePojo.getStarDate() !=null){
                this.starDate = oterEstimatePojo.getStarDate();
            }

            if(oterEstimatePojo.getDaysToDeline() !=null){
                this.daysToDeline = oterEstimatePojo.getDaysToDeline();
            }

            if(oterEstimatePojo.getDaysLate() !=null){
                this.daysLate = oterEstimatePojo.getDaysLate();
            }

            if(oterEstimatePojo.getTotalCostWorkWithoutTaxes() !=null){
                this.totalCostWorkWithoutTaxes = oterEstimatePojo.getTotalCostWorkWithoutTaxes();
            }

            if(oterEstimatePojo.getTotalCostWork() !=null){
                this.totalCostWork = oterEstimatePojo.getTotalCostWork();
            }

            if(oterEstimatePojo.getRemainingPayable() !=null){
                this.remainingPayable = oterEstimatePojo.getRemainingPayable();
            }

            if(oterEstimatePojo.getTotalAmountPaind() !=null){
                this.totalAmountPaind = oterEstimatePojo.getTotalAmountPaind();
            }

            if(oterEstimatePojo.getOverhead() !=null){
                this.overhead = oterEstimatePojo.getOverhead();
            }

            if(oterEstimatePojo.getStatus() !=null){
                this.status = oterEstimatePojo.getStatus();
            }

            if(oterEstimatePojo.getClient() !=null){
                this.client = new ClientPojo(oterEstimatePojo.getClient());
            }

            if(oterEstimatePojo.getCompany() != null){
                this.company = oterEstimatePojo.getCompany();
            }

            if(oterEstimatePojo.getPayments() !=null){
                if (oterEstimatePojo.getPayments().size() >0){
                    this.payments = cloneListPaymentPojo(oterEstimatePojo.getPayments());
                }else {
                    this.payments = new ArrayList<PaymentPojo>();
                }
            }

            if(oterEstimatePojo.getBills() !=null){
               if(oterEstimatePojo.getBills().size() >0){
                   this.bills = this.cloneListBillPojo(oterEstimatePojo.getBills());
               }else {
                   this.bills = new ArrayList<BillPojo>();
               }
            }

            if(oterEstimatePojo.getSubcontractors() !=null){
                if(oterEstimatePojo.getSubcontractors().size() >0 ) {
                    this.subcontractors = this.cloneListSubcontractorPojo(oterEstimatePojo.getSubcontractors());
                }else {
                    this.subcontractors = new ArrayList<SubcontractorPojo>();
                }
            }

            if(oterEstimatePojo.getServices() !=null){
                if(oterEstimatePojo.getServices().size() >0){
                    this.services = this.cloneListServiceHandyManTallyPojo(oterEstimatePojo.getServices());
                }else {
                    this.services = new ArrayList<ServiceHandyManTallyPojo>();
                }
            }
            this.active = true;

        }
    }


    private List<ServiceHandyManTallyPojo>  cloneListServiceHandyManTallyPojo(List<ServiceHandyManTallyPojo> servicesList){
        List<ServiceHandyManTallyPojo> services = new ArrayList<ServiceHandyManTallyPojo>();
        servicesList.stream().forEach(service -> services.add(new ServiceHandyManTallyPojo(service)));
        return services;
    }

    private List<PaymentPojo>  cloneListPaymentPojo(List<PaymentPojo> paymentsList){
        List<PaymentPojo> payments = new ArrayList<PaymentPojo>();
        paymentsList.stream().forEach(payment -> payments.add(new PaymentPojo(payment)));
        return payments;
    }

    private List<SubcontractorPojo>  cloneListSubcontractorPojo(List<SubcontractorPojo> subcontractorsList){
        List<SubcontractorPojo> subcontractors = new ArrayList<SubcontractorPojo>();
        subcontractorsList.stream().forEach(subcontractor -> subcontractors.add(new SubcontractorPojo(subcontractor)));
        return subcontractors;
    }

    private List<BillPojo>  cloneListBillPojo(List<BillPojo> billPojoList){
        List<BillPojo> billList = new ArrayList<BillPojo>();
        billPojoList.stream().forEach(bill -> billList.add(new BillPojo(bill)));
        return billList;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Date getDedline() {
        return dedline;
    }

    public void setDedline(Date dedline) {
        this.dedline = dedline;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ClientPojo getClient() {
        return client;
    }

    public void setClient(ClientPojo client) {
        this.client = client;
    }

    public List<PaymentPojo> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentPojo> payments) {
        this.payments = payments;
    }

    public List<BillPojo> getBills() {
        return bills;
    }

    public void setBills(List<BillPojo> bills) {
        this.bills = bills;
    }

    public List<SubcontractorPojo> getSubcontractors() {
        return subcontractors;
    }

    public void setSubcontractors(List<SubcontractorPojo> subcontractors) {
        this.subcontractors = subcontractors;
    }

    public List<ServiceHandyManTallyPojo> getServices() {
        return services;
    }

    public void setServices(List<ServiceHandyManTallyPojo> services) {
        this.services = services;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstimatePojo that = (EstimatePojo) o;
        return Objects.equals(idEstimate, that.idEstimate) && Objects.equals(codeWork, that.codeWork) && Objects.equals(description, that.description) && Objects.equals(title, that.title) && Objects.equals(createDay, that.createDay) && Objects.equals(dedline, that.dedline) && Objects.equals(owner, that.owner) && Objects.equals(starDate, that.starDate) && Objects.equals(daysToDeline, that.daysToDeline) && Objects.equals(daysLate, that.daysLate) && Objects.equals(totalCostWorkWithoutTaxes, that.totalCostWorkWithoutTaxes) && Objects.equals(totalCostWork, that.totalCostWork) && Objects.equals(remainingPayable, that.remainingPayable) && Objects.equals(totalAmountPaind, that.totalAmountPaind) && Objects.equals(overhead, that.overhead) && Objects.equals(active, that.active) && Objects.equals(client, that.client) && Objects.equals(payments, that.payments) && Objects.equals(bills, that.bills) && Objects.equals(subcontractors, that.subcontractors) && Objects.equals(services, that.services) && Objects.equals(status, that.status) && Objects.equals(budgetNumber, that.budgetNumber) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstimate, codeWork, description, title, createDay, dedline, owner, starDate, daysToDeline, daysLate, totalCostWorkWithoutTaxes, totalCostWork, remainingPayable, totalAmountPaind, overhead, active, client, payments, bills, subcontractors, services, status, budgetNumber, company);
    }
}
