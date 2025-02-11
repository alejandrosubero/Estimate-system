package com.jshandyman.service.service;

import com.jshandyman.service.entitys.*;

import java.util.List;

public interface AuditableService {

    public Work recursionUpdate(Work work, Work workBase);
    public Estimate recursionUpdate(Estimate estimate, Estimate estimateBase);
    public  List<Bill> billsUpdate(List<Bill> billNew, List<Bill> billBase);
    public List<ServiceHandyManTally> servicesUpdate(List<ServiceHandyManTally> servicesNew, List<ServiceHandyManTally> servicesBase);
    public List<Product> productUpdate(List<Product> productNew, List<Product> productBase);
    public List<Subcontractor> subcontractorUpdate(List<Subcontractor> subcontractorNew, List<Subcontractor> subcontractorBase);
    public List<Payment> paymentUpdate(List<Payment> paymentsmentNew, List<Payment> paymentsBase);

}

