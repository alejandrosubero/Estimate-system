
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  

@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.repository;

import com.jshandyman.service.entitys.Work;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends CrudRepository<Work, Long> {

    public List<Work> findByCompany(String company);

    public Work save(Work work);

    public List<Work> findByStatus(String status);

    public List<Work> findByStatusContaining(String status);

    public List<Work> findByActive(Boolean active);

    public Optional<Work> findByCodeWork(String codeWork);

    public List<Work> findByCodeWorkContaining(String codeWork);

    public Optional<Work> findByDescription(String description);

    public List<Work> findByDescriptionContaining(String description);

    public Optional<Work> findByDedline(Date dedline);

    public List<Work> findByDedlineContaining(Date dedline);

    public Optional<Work> findByDaysToDeline(Long daysToDeline);

    public List<Work> findByDaysToDelineContaining(Long daysToDeline);

    public Optional<Work> findByDaysLate(Long daysLate);

    public List<Work> findByDaysLateContaining(Long daysLate);

    public Optional<Work> findByStarDate(Date StarDate);

    public List<Work> findByStarDateContaining(Date StarDate);

    public Optional<Work> findByTotalCostWorkWithoutTaxes(Double totalCostWorkWithoutTaxes);

    public List<Work> findByTotalCostWorkWithoutTaxesContaining(Double totalCostWorkWithoutTaxes);

    public Optional<Work> findByTotalCostWork(Double totalCostWork);

    public List<Work> findByTotalCostWorkContaining(Double totalCostWork);

    public Optional<Work> findByRemainingPayable(Double remainingPayable);

    public List<Work> findByRemainingPayableContaining(Double remainingPayable);

    public Work findByIdEstimate(Long idEstimate);


    @Transactional
    @Modifying
    @Query("update Work u set u.daysToDeline = ?1 where u.idWork = ?2")
    void updateDedline(Long daysToDeline, Long idWork);

    @Transactional
    @Modifying
    @Query("update Work u set u.active = ?1 where u.idWork = ?2")
    void updateActive(Boolean active, Long idWork);

    @Transactional
    @Modifying
    @Query("update Work u set u.active = ?1 , u.status ='CANCELED' where u.idWork = ?2")
    void updateActiveDelete(Boolean active, Long idWork);


//    @Query(value = "SELECT p FROM Work p WHERE p.daysToDeline > 0 and p.active = true")
//	public List<Work> findByDedlineSearch();

    @Query(value = "SELECT p FROM Work p WHERE p.daysToDeline > 0 and p.active = true and p.status ='IN PROGRESS' or p.status ='APPROVED' or p.status ='SEND' or p.status ='PAUSE'")
    public List<Work> findByDedlineSearch();

    
    @Query(value = "SELECT p FROM Work p WHERE p.daysToDeline <= ?1 and p.active = true")
    public List<Work> findByDedlineSearchUperTo(Integer days);


    @Query(value = "SELECT p FROM Work p WHERE CONCAT_WS(p.idWork, ' ', p.idEstimate, ' ', p.title , ' ', p.owner, ' ', p.createDay, ' ', p.description, ' ',  p.totalCostWork) LIKE %?1% AND p.active = true")
    public List<Work> finBySearch(String keyword);


    // SELECT * FROM estimate WHERE create_day BETWEEN '2022-03-08 00:00:00' AND '2022-03-09 23:59:59';
    @Query("SELECT p FROM Work p WHERE p.createDay BETWEEN ?1 AND ?2 AND p.active = true")
    public List<Work>finByCreateDayBetween(Date startDate, Date endDay);


    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;
    @Query("SELECT p FROM Work p WHERE MONTH(p.createDay) = ?1 AND YEAR(p.createDay) = ?2 AND p.active = true")
    public List<Work>finByCreateDayMonthYear(Integer month, Integer year);


    // SELECT * FROM estimate WHERE create_day BETWEEN '2022-03-08 00:00:00' AND '2022-03-09 23:59:59';
    @Query("SELECT p FROM Work p WHERE p.starDate BETWEEN ?1 AND ?2 AND p.active = true")
    public List<Work>finByStartDayBetween(Date startDate, Date endDay);

    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;
    @Query("SELECT p FROM Work p WHERE MONTH(p.starDate) = ?1 AND YEAR(p.starDate) = ?2 AND p.active = true")
    public List<Work>finByStartDayMonthYear(Integer month, Integer year);


    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;
    @Query("SELECT p FROM Work p WHERE MONTH(p.starDate) = ?1 AND p.active = true")
    public List<Work>finByStartDayMonth(Integer month);

    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;
    @Query("SELECT p FROM Work p WHERE YEAR(p.starDate) = ?1 AND p.company = ?2 AND p.active = true and p.status ='IN PROGRESS' or p.status ='PAUSE'  or p.status ='APPROVED' or p.status ='SEND' or p.status ='FINALIZED'")
    public List<Work>finByStartDayYear(Integer year, String company);

    @Query("SELECT p FROM Work p WHERE YEAR(p.createDay) = ?1 AND p.company = ?2 AND p.active = true")
    public List<Work>finByCreateDayYear(Integer year,  String company);

    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;
    @Query("SELECT p FROM Work p WHERE MONTH(p.createDay) = ?1 AND p.company = ?2 AND p.active = true and p.status ='IN PROGRESS' or p.status ='PAUSE'  or p.status ='APPROVED' or p.status ='SEND'")
    public List<Work>finByCreateDayMonth(Integer month,  String company);

    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;

    @Query("SELECT p FROM Work p WHERE MONTH(p.finalDate) = ?1  AND p.company = ?2 AND p.active = true")
    public List<Work> finByfinalDateInMonth(Integer month,  String company);


    @Query("SELECT p FROM Work p WHERE YEAR(p.finalDate) = ?1 AND p.company = ?2 AND p.active = true")
    public List<Work> finByfinalDateInYear(Integer year, String company);




//    //SELECT COUNT(*) FROM work p WHERE p.active = true and p.status ='IN PROGRESS' or p.status ='PAUSE'  or p.status ='APPROVED' or p.status ='SEND';

//    @Query("SELECT P FROM work p WHERE p.active = true and p.status ='IN PROGRESS' or p.status ='PAUSE' or p.status ='APPROVED' or p.status ='SEND'")
//    public Integer getCountWorkActive();

//    @Query("SELECT P FROM work p WHERE YEAR(p.finalDate) = ?1 AND p.active = true and p.status ='IN PROGRESS' or p.status ='PAUSE' or p.status ='APPROVED' or p.status ='SEND'")
//    public Integer countWorkInYear(Integer year);

//    @Query("SELECT P FROM work p WHERE MONTH(p.finalDate) = ?1 AND p.active = true and p.status ='IN PROGRESS' or p.status ='PAUSE' or p.status ='APPROVED' or p.status ='SEND'")
//    public Integer getCountWorkInMonth(Integer month)


    public long countByActiveAndStatusAndCompanyOrStatusOrStatus(Boolean active, String progress , String company, String pause, String send);

    public long countByActiveAndStatusAndCompany(Boolean active, String status, String company);
    public List<Work> findByActiveAndStatusAndCompany(Boolean active, String status, String company);

//    SELECT * FROM work WHERE days_to_deline >= 4 and status = 'FINALIZED';
    public long countByActiveAndStatusAndDaysToDelineGreaterThanEqual(Boolean active, String status, Long daysToDeline);
    public long countByActiveAndCompanyAndStatusAndDaysToDelineGreaterThan(Boolean active,  String company, String status, Long daysToDeline);
    public long countByActiveAndStatusAndCompanyAndDaysToDelineLessThanEqual(Boolean active, String status, String company,  Long daysToDeline);
    public List<Work> findByActiveAndCompanyAndStatusAndDaysToDelineGreaterThanEqual(Boolean active, String company, String status, Long daysToDeline);
    public List<Work> findByDaysToDelineIsNotNullAndActiveAndStatusOrStatusAndDaysToDelineGreaterThanEqual(Boolean active, String progress, String pause, Long daysToDeline);

//SELECT * FROM work WHERE  days_to_deline is not null and days_to_deline >= 4 and status = 'IN PROGRESS' Or status = 'PAUSE' ;
//        @Query("SELECT p FROM Work p WHERE p.daysToDeline is not null AND p.daysToDeline <= ?1 AND p.status = ?2 Or p.status = ?3 AND p.active = true")
//        public List<Work> worksBeforeDedLineAlert(Long daysToDeline,String progress, String pause);

    @Query("SELECT p FROM Work p WHERE p.daysToDeline is not null AND p.daysToDeline <= ?1 AND p.status = ?2 AND p.company = ?3 AND p.active = true")
    public List<Work> worksBeforeDedLineAlert(Long daysToDeline,String progress, String company);

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


