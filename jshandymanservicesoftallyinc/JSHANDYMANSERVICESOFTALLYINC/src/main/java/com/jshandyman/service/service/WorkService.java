
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.service;

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.entitys.Client;
import com.jshandyman.service.entitys.Payment;
import com.jshandyman.service.entitys.Work;
import com.jshandyman.service.pojo.*;

import java.util.Date;
import java.util.List;


public interface WorkService {

    public List<WorkListTabletPojo> pojoList(List<Work> works);
    public List<WorkListTabletPojo>  workListTablet(List<Long> idList);;

    public List<WorkListTabletPojo> findByActiveAndStatus(Boolean active, String status, String company);

    public List<Work>finByStartDayYear(Integer year,  String company);
    public List<Work>finByCreateDayYear(Integer year, String company);
    public List<WorkListTabletPojo> findByActiveAndStatusAndDaysToDelineGreaterThanEqual(Boolean active, String status, Long daysToDeline, String company);
    public List<WorkListTabletPojo> worksBeforeDedLineAlert(Long daysToDeline,String progress, String pause, String company);

    public WorkPojo findByIdEstimate(Long idEstimate);
    public List<WorkListTabletPojo> findByStatusContaining(String status);
    public List<WorkListTabletPojo> findByStartDayBetween(Date startDate, Date endDay);
    public List<WorkListTabletPojo> findByStartDayMonthYear(Double month, Double year);
    public List<WorkListTabletPojo>findByCreateDayMonthYear(Double month, Double year);
    public List<WorkListTabletPojo>findByCreateDayBetween(Date startDate, Date endDay);
    public List<WorkListTabletPojo> findBySearchToListTablet(String keyword);

    public List<WorkPojo> findBySearch(String keyword);
    public boolean deleteWorkLogic(Long id);
    public List<WorkPojo>findByActive(Boolean active);
    public WorkPojo saveUpdateWork(Work work);
    public List<WorkListTabletPojo> getWorkListTablet(String company);
    public WorkPojo saveNewWork(Work work);

    public List<PaymentPojo> getAllPamentToWork(Long idWork);

    public List<ProductPojo> AllProductInWork(String codeWork);

    public WorkPojo findByCodeWork(String codeWork);

    public WorkPojo findByDescription(String description);

    public WorkPojo findByDedline(Date dedline);

    public WorkPojo findByStarDate(Date StarDate);

    public WorkPojo findByDaysToDeline(Long daysToDeline);

    public WorkPojo findByDaysLate(Long daysLate);

    public List<WorkPojo> findByDaysLateContaining(Long daysLate);

    public WorkPojo findByTotalCostWorkWithoutTaxes(Double totalCostWorkWithoutTaxes);

    public WorkPojo findByTotalCostWork(Double totalCostWork);

    public WorkPojo findByRemainingPayable(Double remainingPayable);

    public List<WorkPojo> findByCodeWorkContaining(String codeWork);

    public List<WorkPojo> findByDescriptionContaining(String description);

    public List<WorkPojo> findByDedlineContaining(Date dedline);

    public List<WorkPojo> findByStarDateContaining(Date StarDate);

    public List<WorkPojo> findByDaysToDelineContaining(Long daysToDeline);

    public List<WorkPojo> findByTotalCostWorkWithoutTaxesContaining(Double totalCostWorkWithoutTaxes);

    public List<WorkPojo> findByTotalCostWorkContaining(Double totalCostWork);

    public List<WorkPojo> findByRemainingPayableContaining(Double remainingPayable);

    public WorkPojo findById(Long id);

    public boolean saveWork(Work work);

    public List<WorkPojo> getAllWork();

    public boolean deleteWork(Long id);

    public List<WorkPojo> findByRelacionClient(Client client);

    public List<WorkPojo> findByPaymentContaining(Payment payments);

    public List<WorkPojo> findByBillContaining(Bill bills);

    public List<BillPojo> AllBillInWork(String codeWork);
}
 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


