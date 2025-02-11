package com.jshandyman.service.service;

import com.jshandyman.service.entitys.*;
import com.jshandyman.service.pojo.*;

import java.util.Date;
import java.util.List;

public interface EstimateService {
    public List<EstimateListTabletPojo> estimateListTablet(List<Long> listId);
    public List<EstimateListTabletPojo> findByStatusContaining(String status);
    public List<EstimateListTabletPojo> findByCreateDayBetween(Date startDate, Date endDay);
    public List<EstimateListTabletPojo> findByCreateDayMonthYear(Double month, Double year);
    public List<EstimateListTabletPojo> finBySearchToListTablet(String keyword);
    public List<EstimatePojo> finBySearch(String keyword);
    public List<EstimatePojo>findByActive(Boolean active);
    public boolean deleteEstimateLogic(Long id);
    public List<EstimateListTabletPojo> getEstimateListTablet(String company);
    public List<EstimatePojo> getAllEstimate();
    public List<EstimatePojo> getAllEstimateCompany(String company);
    public EstimatePojo saveUpdateEstimate(Estimate estimate);
    public EstimatePojo saveNewEstimate(Estimate estimate);

    public List<PaymentPojo> getAllPamentToEstimate(Long idWork);
    public List<ProductPojo> AllProductInEstimate(String codeWork);
    public List<BillPojo> AllBillInEstimate(String codeWork);

    public Boolean covertEstimateToWork(EstimatePojo estimate);

    public boolean saveEstimate(Estimate estimate);

    public boolean deleteEstimate(Long id);

    public EstimatePojo findByCodeWork(String codeWork);

    public EstimatePojo findByDescription(String description);

    public EstimatePojo findById(Long id);

    public EstimatePojo findByDedline(Date dedline);

    public EstimatePojo findByStarDate(Date StarDate);

    public EstimatePojo findByDaysToDeline(Long daysToDeline);

    public EstimatePojo findByDaysLate(Long daysLate);

    public List<EstimatePojo> findByDaysLateContaining(Long daysLate);

    public EstimatePojo findByTotalCostEstimateWithoutTaxes(Double totalCostWorkWithoutTaxes);

    public EstimatePojo findByTotalCostEstimate(Double totalCostWork);

    public EstimatePojo findByRemainingPayable(Double remainingPayable);

    public List<EstimatePojo> findByCodeEstimateContaining(String codeWork);

    public List<EstimatePojo> findByDescriptionContaining(String description);

    public List<EstimatePojo> findByDedlineContaining(Date dedline);

    public List<EstimatePojo> findByStarDateContaining(Date StarDate);

    public List<EstimatePojo> findByDaysToDelineContaining(Long daysToDeline);

    public List<EstimatePojo> findByTotalCostEstimateWithoutTaxesContaining(Double totalCostWorkWithoutTaxes);

    public List<EstimatePojo> findByTotalCostEstimateContaining(Double totalCostWork);

    public List<EstimatePojo> findByRemainingPayableContaining(Double remainingPayable);

    public List<EstimatePojo> findByRelacionClient(Client client);

    public List<EstimatePojo> findByPaymentContaining(Payment payments);

    public List<EstimatePojo> findByBillContaining(Bill bills);


}
