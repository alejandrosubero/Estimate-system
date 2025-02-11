package com.jshandyman.service.repository;

import com.jshandyman.service.entitys.Estimate;

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
public interface EstimateRepository extends CrudRepository<Estimate, Long> {

    public List<Estimate> findByCompanyAndActive(String company, Boolean active);
    public List<Work> findByStatus(String status);
    public List<Estimate> findByStatusContaining(String status);

    public Optional<Estimate> findByCodeWork(String codeWork);

    public List<Estimate> findByCodeWorkContaining(String codeWork);

    public Optional<Estimate> findByDescription(String description);
    public List<Estimate> findByDescriptionContaining(String description);

    public Optional<Estimate> findByDedline(Date dedline);
    public List<Estimate> findByDedlineContaining(Date dedline);

    public Optional<Estimate> findByStarDate(Date StarDate);
    public List<Estimate> findByStarDateContaining(Date StarDate);

    public Optional<Estimate> findByDaysToDeline(Long daysToDeline);
    public List<Estimate> findByDaysToDelineContaining(Long daysToDeline);

    public Optional<Estimate> findByDaysLate(Long daysLate);
    public List<Estimate> findByDaysLateContaining(Long daysLate);

    public Optional<Estimate> findByTotalCostWorkWithoutTaxes(Double totalCostWorkWithoutTaxes);
    public List<Estimate> findByTotalCostWorkWithoutTaxesContaining(Double totalCostWorkWithoutTaxes);

    public Optional<Estimate> findByTotalCostWork(Double totalCostWork);
    public List<Estimate> findByTotalCostWorkContaining(Double totalCostWork);

    public Optional<Estimate> findByRemainingPayable(Double remainingPayable);
    public List<Estimate> findByRemainingPayableContaining(Double remainingPayable);
    public List<Estimate>findByActive(Boolean active);

    @Transactional
    @Modifying
    @Query("update Estimate u set u.daysToDeline = ?1 where u.idEstimate = ?2")
    void updateDedline(Long daysToDeline, Long idEstimate);


    @Transactional
    @Modifying
    @Query("update Estimate u set u.active = ?1 where u.idEstimate = ?2")
    void updateActive(Boolean active, Long idEstimate);


    @Query(value = "SELECT p FROM Estimate p WHERE CONCAT_WS( p.idEstimate, ' ', p.title , ' ', p.owner, ' ', p.createDay, ' ', p.description, ' ',  p.totalCostWork) LIKE %?1%")
    public List<Estimate> finBySearch(String keyword);


    @Query("SELECT p FROM Estimate p WHERE p.createDay BETWEEN ?1 AND ?2")
    public List<Estimate>finByCreateDayBetween(Date startDate, Date endDay);


    @Query("SELECT p FROM Estimate p WHERE MONTH(p.createDay) = ?1 AND YEAR(p.createDay) = ?2")
    public List<Estimate>finByCreateDayMonthYear(Integer month, Integer year);


    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;
    @Query("SELECT p FROM Estimate p WHERE MONTH(p.createDay) = ?1 AND p.company = ?2 AND p.active = true")
    public List<Estimate> finByCreateDayMonth(Integer month, String company);

    //SELECT * FROM estimate WHERE MONTH(create_day) = 05 AND YEAR(create_day) = 2022;
    @Query("SELECT p FROM Estimate p WHERE YEAR(p.createDay) = ?1 AND p.company = ?2 AND  p.active = true")
    public List<Estimate> finByCreateDayYear(Integer year, String company);

    @Query("SELECT p FROM Estimate p WHERE YEAR(p.createDay) = ?1 AND p.active = true and p.status =?2 AND  p.company = ?3" )
    public List<Estimate> finByCreateDayYearApproved(Integer year, String status,  String company);


    public long countByActiveAndCompanyAndStatusOrStatusOrStatusOrStatus(Boolean active, String company, String progress, String pause, String send, String approved);

    public long countByActiveAndStatusAndCompany(Boolean active, String status, String company);


}

