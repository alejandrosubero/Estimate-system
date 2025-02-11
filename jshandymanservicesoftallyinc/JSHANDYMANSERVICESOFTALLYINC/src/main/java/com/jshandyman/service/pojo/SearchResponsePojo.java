package com.jshandyman.service.pojo;


import java.util.ArrayList;
import java.util.List;

public class SearchResponsePojo  {

    private List<WorkPojo> works;
    private List<EstimatePojo> estimates;
    private List<ServiceHandyManTallyPojo> services;
    private List<SubcontractorPojo> subcontractors;
    private List<EstimateListTabletPojo> estimateListTabletPojo;
    private List<WorkListTabletPojo> workListTabletPojo;

    public SearchResponsePojo() {
        works = new ArrayList<WorkPojo>();
        estimates = new ArrayList<EstimatePojo>();
        services = new ArrayList<ServiceHandyManTallyPojo>();
        subcontractors = new ArrayList<SubcontractorPojo>();
        estimateListTabletPojo = new ArrayList<EstimateListTabletPojo>();
        workListTabletPojo = new ArrayList<WorkListTabletPojo>();
    }

    public SearchResponsePojo(List<WorkPojo> worksR, List<EstimatePojo> estimatesR, List<ServiceHandyManTallyPojo> servicesR) {

        works = new ArrayList<WorkPojo>();
        estimates = new ArrayList<EstimatePojo>();
        services = new ArrayList<ServiceHandyManTallyPojo>();
        subcontractors = new ArrayList<SubcontractorPojo>();
        estimateListTabletPojo = new ArrayList<EstimateListTabletPojo>();
        workListTabletPojo = new ArrayList<WorkListTabletPojo>();


        if (worksR != null && worksR.size() > 0) {
            this.works.addAll(worksR);
        }

        if (estimatesR != null && estimatesR.size() > 0) {
            this.estimates.addAll(estimatesR);
        }

        if (servicesR != null && servicesR.size() > 0) {
            this.services.addAll(servicesR);
        }
    }

    public SearchResponsePojo(List<WorkListTabletPojo> workListTabletR, List<EstimateListTabletPojo> estimateListTabletR, List<ServiceHandyManTallyPojo> servicesR, List<WorkPojo> worksR, List<EstimatePojo> estimatesR, List<SubcontractorPojo> subcontractorsR) {

        works = new ArrayList<WorkPojo>();
        estimates = new ArrayList<EstimatePojo>();
        services = new ArrayList<ServiceHandyManTallyPojo>();
        subcontractors = new ArrayList<SubcontractorPojo>();
        estimateListTabletPojo = new ArrayList<EstimateListTabletPojo>();
        workListTabletPojo = new ArrayList<WorkListTabletPojo>();

        if (workListTabletR != null && workListTabletR.size() > 0) {
            this.workListTabletPojo.addAll(workListTabletR);
        }

        if (estimateListTabletR != null && estimateListTabletR.size() > 0) {
            this.estimateListTabletPojo.addAll(estimateListTabletR);
        }

        if (servicesR != null && servicesR.size() > 0) {
            this.services.addAll(servicesR);
        }

        if (worksR != null && worksR.size() > 0) {
            this.works.addAll(worksR);
        }

        if (estimatesR != null && estimatesR.size() > 0) {
            this.estimates.addAll(estimatesR);
        }

        if (subcontractorsR != null && subcontractorsR.size() > 0) {
            this.subcontractors.addAll(subcontractorsR);
        }

    }


    public List<WorkPojo> getWorks() {
        return works;
    }

    public void setWorks(List<WorkPojo> works) {
        this.works = works;
    }

    public List<EstimatePojo> getEstimates() {
        return estimates;
    }

    public void setEstimates(List<EstimatePojo> estimates) {
        this.estimates = estimates;
    }

    public List<ServiceHandyManTallyPojo> getServices() {
        return services;
    }

    public void setServices(List<ServiceHandyManTallyPojo> services) {
        this.services = services;
    }

    public List<EstimateListTabletPojo> getEstimateListTabletPojo() {
        return estimateListTabletPojo;
    }

    public void setEstimateListTabletPojo(List<EstimateListTabletPojo> estimateListTabletPojo) {
        this.estimateListTabletPojo = estimateListTabletPojo;
    }

    public List<WorkListTabletPojo> getWorkListTabletPojo() {
        return workListTabletPojo;
    }

    public void setWorkListTabletPojo(List<WorkListTabletPojo> workListTabletPojo) {
        this.workListTabletPojo = workListTabletPojo;
    }

    public List<SubcontractorPojo> getSubcontractors() {
        return subcontractors;
    }

    public void setSubcontractors(List<SubcontractorPojo> subcontractors) {
        this.subcontractors = subcontractors;
    }
}
