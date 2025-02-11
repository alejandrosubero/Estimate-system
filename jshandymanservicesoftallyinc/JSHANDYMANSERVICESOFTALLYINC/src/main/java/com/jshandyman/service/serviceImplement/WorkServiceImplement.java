
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.*;
import com.jshandyman.service.mapper.BillMapper;
import com.jshandyman.service.mapper.ClientMapper;
import com.jshandyman.service.mapper.PaymentMapper;
import com.jshandyman.service.mapper.WorkMapper;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.repository.WorkRepository;
import com.jshandyman.service.service.AuditableService;
import com.jshandyman.service.service.ClientService;
import com.jshandyman.service.service.PaymentService;
import com.jshandyman.service.service.WorkService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class WorkServiceImplement implements WorkService {

    protected static final Log logger = LogFactory.getLog(WorkServiceImplement.class);

    @Autowired
    private WorkRepository workrepository;

	@Autowired
	private WorkMapper workMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AuditableService auditableService;


    @Override
    public List<WorkPojo> findBySearch(String keyword) {
        return this.entityToPojoList(workrepository.finBySearch(keyword));
    }

    @Override
    public List<WorkListTabletPojo> findByCreateDayMonthYear(Double month, Double year) {
        logger.info("execute find By Create Day and Month of Year");
        return this.pojoList(workrepository.finByCreateDayMonthYear(month.intValue(), year.intValue()));
    }

    @Override
    public List<WorkListTabletPojo> findByCreateDayBetween(Date startDate, Date endDay) {
        logger.info("execute findBy Create Day Between");
        try {
            return this.pojoList(workrepository.finByCreateDayBetween(startDate, endDay));
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<WorkListTabletPojo>();
        }
    }


    @Override
    public List<WorkListTabletPojo> findByStartDayMonthYear(Double month, Double year) {
        logger.info("execute find By Create Day and Month of Year");
        return this.pojoList(workrepository.finByStartDayMonthYear(month.intValue(), year.intValue()));
    }



    @Override
    public List<WorkListTabletPojo> findByStatusContaining(String status) {
        logger.info("execute find By Status Containing");
        return this.pojoList(workrepository.findByStatusContaining(status));
    }


    @Override
    public List<WorkListTabletPojo> findByStartDayBetween(Date startDate, Date endDay) {
        logger.info("execute findBy Create Day Between");
        try {
            return this.pojoList(workrepository.finByStartDayBetween(startDate, endDay));
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<WorkListTabletPojo>();
        }
    }


    @Override
    public List<WorkListTabletPojo> findBySearchToListTablet(String keyword){
        logger.info("execute findBySearch To List Tablet");
        return this.pojoList(workrepository.finBySearch(keyword));
    }


    public List<WorkListTabletPojo> pojoList(List<Work> works){
        logger.info("execute pojo List");
        List<WorkListTabletPojo> listaWork = new ArrayList<WorkListTabletPojo>();

        works.stream().forEach(work -> {
            if(work.getActive()){
                listaWork.add(new WorkListTabletPojo(
                        work.getOwner(),
                        work.getIdWork(),
                        work.getTitle(),
                        work.getDescription(),
                        work.getTotalCostWork(),
                        work.getCreateDay(),
                        work.getStarDate(),
                        work.getDaysToDeline(),
                        work.getDaysLate(),
                        work.getIdEstimate(),
                        work.getStatus(),
                        work.getTotalAmountPaind(),
                        work.getRemainingPayable(),
                        work.getFinalDate()
                ));
            }
        });
        return listaWork;
    }


    @Override
    public List<WorkListTabletPojo> getWorkListTablet(String company) {
        logger.info("execute get Work List Tablet");
        List<WorkListTabletPojo> listaWork = new ArrayList<WorkListTabletPojo>();
        workrepository.findByCompany(company).forEach(work -> {
            if(work.getActive()){
                listaWork.add(new WorkListTabletPojo(
                        work.getOwner(),
                        work.getIdWork(),
                        work.getTitle(),
                        work.getDescription(),
                        work.getTotalCostWork(),
                        work.getCreateDay(),
                        work.getStarDate(),
                        work.getDaysToDeline(),
                        work.getDaysLate(),
                        work.getIdEstimate(),
                        work.getStatus(),
                        work.getTotalAmountPaind(),
                        work.getRemainingPayable(),
                        work.getFinalDate()
                ));
            }
        });
        return listaWork;
    }


    @Override
    public List<WorkListTabletPojo> workListTablet(List<Long> idList){
        logger.info("execute get Work List Tablet fomt id");
        List<WorkListTabletPojo> listaWork = new ArrayList<WorkListTabletPojo>();
        List<Work> list = new ArrayList<Work>();
        idList.stream().forEach(id-> list.add(workrepository.findById(id).get()));
        list.stream().forEach(work -> {
            if(work.getActive()){
                listaWork.add(new WorkListTabletPojo(
                        work.getOwner(), work.getIdWork(), work.getTitle(), work.getDescription(),
                        work.getTotalCostWork(), work.getCreateDay(), work.getStarDate(),
                        work.getDaysToDeline(), work.getDaysLate(), work.getIdEstimate(), work.getStatus(),
                        work.getTotalAmountPaind(), work.getRemainingPayable(), work.getFinalDate()));
            }
        });
        return listaWork;
    }


    @Override
    public List<WorkPojo>findByActive(Boolean active){
        return this.entityToPojoList(workrepository.findByActive(active));
    }


    @Override
    public List<Work> finByCreateDayYear(Integer year,  String company) {
        List<Work> ListWorkCreateDayInYear = this.workrepository.finByCreateDayYear(year, company);
        ListWorkCreateDayInYear.stream().forEach( work -> {work = this.setRemainingPayable(work);});
        return ListWorkCreateDayInYear;
    }

    @Override
    public List<Work> finByStartDayYear(Integer year, String company) {
        List <Work> finByStartDayYear =  this.workrepository.finByStartDayYear(year, company);
        finByStartDayYear.stream().forEach( work -> {
            work = this.setRemainingPayable(work);
        });
        return finByStartDayYear;
    }



    @Override
    public List<WorkListTabletPojo> findByActiveAndStatusAndDaysToDelineGreaterThanEqual(Boolean active, String status, Long daysToDeline, String company) {
        try{
            List<Work> entityList = this.workrepository.findByActiveAndCompanyAndStatusAndDaysToDelineGreaterThanEqual(active, company ,status, daysToDeline);
            return this.pojoList(entityList);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<WorkListTabletPojo>();
        }
    }




    @Override
    public List<WorkListTabletPojo> findByActiveAndStatus(Boolean active, String status, String company) {
        try{
            List<Work> entityList = this.workrepository.findByActiveAndStatusAndCompany(active, status, company);
            return this.pojoList(entityList);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<WorkListTabletPojo>();
        }
    }

    @Override
    public List<WorkListTabletPojo> worksBeforeDedLineAlert(Long daysToDeline, String progress, String pause,  String company) {
        try{
            List<Work> entityList = this.workrepository.worksBeforeDedLineAlert(daysToDeline, progress, company);
            return this.pojoList(entityList);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<WorkListTabletPojo>();
        }
    }


    @Override
    public WorkPojo findByIdEstimate(Long idEstimate) {
        try{
           Work work = workrepository.findByIdEstimate(idEstimate);
           if (work != null
                   && work.getIdWork() != null
                   && !work.getStatus().equals(Constant.CANCELED)
                   && !work.getStatus().equals(Constant.FINALIZED)) {
               return workMapper.entityToPojo(work);
           }
        }catch (Exception e){
            e.printStackTrace();
            return  new WorkPojo();
        }
        return new WorkPojo();
    }


    @Override
    public boolean saveWork(Work work) {
        logger.info("Save saveWork");
        try {
            Client clientEncript = clientMapper.pojoToEntity(clientMapper.entityToPojo(clientService.encript(work.getClient())));
            work.setClient(clientEncript);

            Work workResponse = workrepository.save(work);
            workResponse.getClient().setWorkId(workResponse.getIdWork());
            workrepository.save(work);

            logger.info("End Save Work = true");
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error(" ERROR : " + e);
            logger.info("End Save Work = false");
            return false;
        }
    }

    private Work setRemainingPayable(Work work){

        if (work.getPayments() != null && work.getPayments().size() > 0){
            Double totalAumont = 0.0;
            for (Payment payment : work.getPayments()){
                totalAumont += payment.getAmountPaind();
            }
            work.setTotalAmountPaind(totalAumont);
        }
        return  setRemainingPayableCancel(work);
    }


    private Work setRemainingPayableCancel(Work work){

        if(!work.getStatus().equals(Constant.CANCELED)){
            if(work.getPayments() != null && work.getPayments().size() > 0){
                work.setRemainingPayable(work.getTotalCostWork()-work.getTotalAmountPaind());
            }else{
                work.setRemainingPayable(work.getTotalCostWork());
            }
        }
        if(work.getStatus().equals(Constant.CANCELED)){
            work.setRemainingPayable(0.0);
        }
        return  work;
    }


    @Override
    public WorkPojo saveNewWork(Work work) {
        logger.info("save New Work");
        try {
            WorkPojo workPojo = null;
            String clave = "Invoice";
            if(work.getCodeWork() != null){
                clave = work.getCodeWork();
            }
            String code = this.createCode(clave);
            work.setCodeWork(code);
            work.setCreateDay(new Date());
            work.getClient().setCodeWork(code);
            String fullName = work.getClient().getName() +" "+work.getClient().getLastName();
            work.getClient().setFullName(fullName);
            work.getClient().setActive(true);

            work.setOwner(work.getClient().getName());
            work.setActive(true);
            work.setStatus(Constant.IN_PROGRESS);
            Client clientEncript = clientMapper.pojoToEntity(clientMapper.entityToPojo(clientService.encript(work.getClient())));
            work.setClient(clientEncript);

            //TODO: BIFORE TO RETURN A WORK we need SET WORKID INTO THE CLIENTE...
            Work workResponse = workrepository.save(work);
            workResponse.getClient().setWorkId(workResponse.getIdWork());
            workPojo = this.saveUpdateWork(workResponse);

//            workPojo = workMapper.entityToPojo(setDelineAndUpdateAndCalulateAmountPaind(workrepository.save(work), null));
            logger.info("End save a New Work...");
            return  workPojo;

        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return null;
        }
    }

    @Override
    public WorkPojo saveUpdateWork(Work work) {
        logger.info("save and Update Estimate");
        WorkPojo workPojo = null;
        try {
            clientService.replaceClientToMap(clientMapper.entityToPojo(work.getClient()));
            work.setOwner(work.getClient().getName());
            Client clientEncript = clientMapper.pojoToEntity(clientMapper.entityToPojo(clientService.encript(work.getClient())));
            work.setClient(clientEncript);
            work = auditableService.recursionUpdate(work, workrepository.findById(work.getIdWork()).get());

            if(work.getStatus().equals(Constant.FINALIZED)){
                work.setFinalDate(new Date());
            }

            workPojo = workMapper.entityToPojo(setDelineAndUpdateAndCalulateAmountPaind(workrepository.save(work), null));

            logger.info("End save & Update Work");
            return workPojo;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return null;
        }
    }


    @Override
    public WorkPojo findById(Long id) {
        Work w = setDelineAndUpdateAndCalulateAmountPaind(workrepository.findById(id).get(), null);
        if (w.getDedline() != null) {
            w.setDaysToDeline(diferencia(w.getDedline()));
        }
        return workMapper.entityToPojo(w);
    }

    @Override
    public boolean deleteWorkLogic(Long id) {
        logger.info("Delete Work");
        boolean clave = false;
        try {
            workrepository.updateActiveDelete(false,id);
//            clientService.updateActiveForWorkId(id);
//            clientService.remuveClientToMapForId(id, Constant.WORK);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public boolean deleteWork(Long id) {
        logger.info("Delete deleteWork");
        boolean clave = false;
        try {
            workrepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private Double totalSubcontractorsCost(List<Subcontractor> subcontractorList ){
        Double subcontractorsCost =0.0;
        for (Subcontractor sub: subcontractorList){
            subcontractorsCost += sub.getTotalCost();
        }
        return  subcontractorsCost;
    }

    private Double totalBillsCost(List<Bill> bills ){
        Double billCost =0.0;
        for (Bill bill: bills){
            billCost += bill.getBillTotal();
        }
        return  billCost;
    }


    private Work costCalculate(Work work){

        Double subcontractorsCost = 0.0;
        Double billCost =0.0;
        Double totalCostWork = 0.0;
        Double remainingPayable = 0.0;
        Double totalServicesCost = 0.0;

        if (work.getSubcontractors().size() >0){
            subcontractorsCost = this.totalSubcontractorsCost(work.getSubcontractors());
        }

        if (work.getBills().size() >0){
            billCost = totalBillsCost(work.getBills());
        }

        if (work.getServices().size() > 0 ) {
            for ( ServiceHandyManTally service : work.getServices() ) {
                totalServicesCost += service.getServicesCost();
            }
        }

        totalCostWork = totalServicesCost + subcontractorsCost + billCost;
        work.setTotalCostWork(totalCostWork);

        remainingPayable = work.getTotalCostWork() - work.getTotalAmountPaind();
        work.setRemainingPayable(remainingPayable);

        return work;
    }

    private  Work setDelineAndUpdateAndCalulateAmountPaind(Work w){

        if(w.getDedline() != null){
            w = setDelineM(w);
            UpdateDedlinedays(w.getDaysToDeline(),w.getIdWork());
        }

        if(w.getPayments() != null && w.getPayments().size() > 0){
            w.setTotalAmountPaind(totalAmountPaind(w));
        }

        if(w.getClient().getIdClient() != null){
            Client client = null;
            client = clientMapper.pojoToEntity(clientService.returnClienteForMap(w.getClient().getIdClient(), w.getCompany()));

            if (client.getIdClient() != null) {
                w.setClient(client);
            } else {
                w.setClient(clientMapper.pojoToEntity(clientService.decrypt(w.getClient())));
            }
        }
        return w ;
    }


    private  Work setDelineAndUpdateAndCalulateAmountPaind(Work w, Boolean one){

        try {

            if(w.getDedline() != null){
                w = setDelineM(w);
                if(one == null){
                    UpdateDedlinedays(w.getDaysToDeline(),w.getIdWork());
                }
            }

            w = this.setRemainingPayable(w);

            if(w.getClient().getIdClient() != null){
                Client client = null;
                if (clientService.checkClienteInMap(w.getClient().getIdClient())){
                    client = clientMapper.pojoToEntity(clientService.returnClienteForMap(w.getClient().getIdClient(), w.getCompany()));
                    w.setClient(client);
                } else {
                    w.setClient(clientMapper.pojoToEntity(clientService.decrypt(w.getClient())));
                    clientService.addNewClientToMap(clientMapper.entityToPojo(w.getClient()));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error(" ERROR : " + e);
        }

        return w ;
    }


    private  Work setDelineM(Work w){
        Long totalDaysToDedLine = diferencia(w.getDedline());
        if(totalDaysToDedLine > 0){
            w.setDaysToDeline(totalDaysToDedLine);
        }else {
            w.setDaysToDeline(0L);
            w.setDaysLate(totalDaysToDedLine * -1);
        }
        return w;
    }


    private void UpdateDedlinedays(Long daysToDeline, Long idWork){
         workrepository.updateDedline(daysToDeline, idWork);
    }


    public Long diferencia(Date endDate){
        LocalDate dateNow = convertToLocalDateViaInstant(new Date());
        LocalDate dedline = convertToLocalDateViaInstant(endDate);
        Long daysBetween = ChronoUnit.DAYS.between(dateNow, dedline);
        // Long dias = dateNow.until(dedline, ChronoUnit.DAYS);
        return daysBetween;
    }


    public Double totalAmountPaind(Work work){
        Double total = 0.0;
        if (work.getPayments().size() >0){
            for ( Payment paiment : work.getPayments()){
                total += paiment.getAmountPaind();
            }
        }
        return total;
    }


    private WorkPojo findByCodeWorkStandar(String codeWork) {
        logger.info("Starting findByCodeWorkStandar");
        WorkPojo response = new WorkPojo();
        Work entity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByCodeWork(codeWork);
        if (fileOptional1.isPresent()) {
            try {
                response = addIdWork( workMapper.entityToPojo(fileOptional1.get()));
                if (entity.getIdEstimate() != null){
                    response.setClient(clientService.decrypt(entity.getClient()));
                    clientService.addNewClientToMap(response.getClient());
                }
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        logger.info("END =>  findByCodeWorkStandar");
        return  response;
    }


    private WorkPojo addIdWork(WorkPojo workPojo){

        if(workPojo.getIdWork() != null){
            Long id = workPojo.getIdWork();
            if(workPojo.getBills() != null && workPojo.getBills().size() > 0){
                workPojo.getBills().stream().forEach( billPojo -> {
                    billPojo.setIdWork(id);
                    billPojo.getProductsAndServices().stream().forEach(productPojo -> productPojo.setIdBill(billPojo.getIdBill()));
                });
            }

            if(workPojo.getServices() !=null && workPojo.getServices().size() > 0) {
                workPojo.getServices().stream().forEach(pojo -> pojo.setIdWork(id));
            }

            if( workPojo.getSubcontractors() != null && workPojo.getSubcontractors().size() >0 ) {
                workPojo.getSubcontractors().stream().forEach(pojo -> pojo.setIdWork(id));
            }

            if(workPojo.getClient() != null){
                workPojo.getClient().setEstimateId(id);
            }
            workrepository.save(this.workMapper.pojoToEntity(workPojo));
        }
        return workPojo;
    }


    private String createCode(String code) {
        return generateCode(code);
    }


    private String generateCode(String cod) {
        String code = "W-"+cod+"*"+UUID.randomUUID().toString();
        WorkPojo file = findByCodeWork(code);
        if (file.getCodeWork() != null && file.getCodeWork().equals(code)){
            generateCode(cod);
        }
        return code;
    }


    @Override
    public List<PaymentPojo> getAllPamentToWork(Long idWork){
        return paymentService.findByIdWorkContaining(idWork);
    }


    @Override
    public List<WorkPojo> getAllWork() {
        logger.info("execute allProyect");
        List<Work> works = new ArrayList<Work>();
        List<WorkPojo> listaWork = new ArrayList<WorkPojo>();

        workrepository.findAll().forEach(work -> works.add(work));

        if(works.size()>0){
            works.stream().forEach(work -> {
                listaWork.add(workMapper.entityToPojo(this.setDelineAndUpdateAndCalulateAmountPaind(work)));
            } );

        }
        return listaWork;
    }


    @Override
    public List<ProductPojo> AllProductInWork( String codeWork){
        List<ProductPojo> list = new ArrayList<ProductPojo>();

        WorkPojo work = this.findByCodeWork(codeWork);

        if (work.getSubcontractors().size() >0){
            for (SubcontractorPojo sub: work.getSubcontractors()) {
                sub.getBillListSubcontractor().stream()
                        .forEach(bill -> bill.getProductsAndServices()
                                .stream().forEach(product -> list.add(product)));
            }
        }

        if(work.getBills().size() > 0){
            work.getBills().stream()
                    .forEach(billPojo -> billPojo.getProductsAndServices()
                            .stream().forEach(productPojo -> list.add(productPojo)));
        }

        return list;
    }



    @Override
    public List<BillPojo> AllBillInWork( String codeWork){

        List<BillPojo> list = new ArrayList<BillPojo>();
        WorkPojo work = this.findByCodeWork(codeWork);
        if (work.getSubcontractors().size() > 0 && work.getSubcontractors().get(0).getBillListSubcontractor().size() > 0){
            for (SubcontractorPojo sub: work.getSubcontractors()) {
                sub.getBillListSubcontractor().stream()
                        .forEach(bill -> list.add(bill));
            }
        }

        if(work.getBills().size() > 0){
            work.getBills().stream()
                    .forEach(billPojo -> list.add(billPojo));
        }
        return list;
    }


    @Override
    public WorkPojo findByCodeWork(String codeWork) {
        logger.info("Starting getWork");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByCodeWork(codeWork);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return workMapper.entityToPojo(workEntity);
    }

    //===========================================================================================


    @Override
    public WorkPojo findByDescription(String description) {
        logger.info("Starting getWork");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByDescription(description);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return workMapper.entityToPojo(workEntity);
    }

    @Override
    public WorkPojo findByDedline(Date dedline) {
        logger.info("Starting getWork");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByDedline(dedline);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return workMapper.entityToPojo(workEntity);
    }

    @Override
    public WorkPojo findByStarDate(Date StarDate) {
        logger.info("Starting getWork");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByStarDate(StarDate);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return workMapper.entityToPojo(workEntity);
    }


    @Override
    public WorkPojo findByDaysToDeline(Long daysToDeline) {
        logger.info("Starting daysToDeline");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByDaysToDeline(daysToDeline);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR in findByDaysToDeline: " + e);
            }
        }
        return workMapper.entityToPojo(workEntity);
    }


    @Override
    public WorkPojo findByDaysLate(Long daysLate) {

        logger.info("Starting findByDaysLate");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByDaysLate(daysLate);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return workMapper.entityToPojo(workEntity);
    }

    @Override
    public WorkPojo findByTotalCostWorkWithoutTaxes(Double totalCostWorkWithoutTaxes) {

        logger.info("Starting findByTotalCostWorkWithoutTaxes");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByTotalCostWorkWithoutTaxes(totalCostWorkWithoutTaxes);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return workMapper.entityToPojo(workEntity);
    }

    @Override
    public WorkPojo findByTotalCostWork(Double totalCostWork) {

        logger.info("Starting getWork");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByTotalCostWork(totalCostWork);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return workMapper.entityToPojo(workEntity);
    }

    @Override
    public WorkPojo findByRemainingPayable(Double remainingPayable) {
        logger.info("Starting getWork");
        Work workEntity = new Work();
        Optional<Work> fileOptional1 = workrepository.findByRemainingPayable(remainingPayable);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return workMapper.entityToPojo(workEntity);
    }




    private List<WorkPojo> entityToPojoList(List<Work> works){
        List<WorkPojo> listaWork = new ArrayList<WorkPojo>();
        works.stream().forEach(work -> {
            listaWork.add(workMapper.entityToPojo(this.setDelineAndUpdateAndCalulateAmountPaind(work)));
        });
        return listaWork;
    }


    @Override
    public List<WorkPojo> findByDaysToDelineContaining(Long daystodeline) {
        logger.info("Get findByDaysToDelineContaining");
        List<WorkPojo> listaWork = new ArrayList<WorkPojo>();
        listaWork =  entityToPojoList( workrepository.findByDaysToDelineContaining(daystodeline));
        return listaWork;
    }


    @Override
    public List<WorkPojo> findByCodeWorkContaining(String codework) {
        logger.info("Get allProyect");
        List<WorkPojo> listaWork = entityToPojoList( workrepository.findByCodeWorkContaining(codework));
        return listaWork;
    }

    @Override
    public List<WorkPojo> findByDescriptionContaining(String description) {
        logger.info("execute findByDescriptionContaining");
        List<WorkPojo> listaWork = entityToPojoList(workrepository.findByDescriptionContaining(description));
        return listaWork;
    }

    @Override
    public List<WorkPojo> findByDedlineContaining(Date dedline) {
        logger.info("execute findByDedlineContaining");
        List<WorkPojo> listaWork = entityToPojoList(workrepository.findByDedlineContaining(dedline));
        return listaWork;
    }

    @Override
    public List<WorkPojo> findByStarDateContaining(Date stardate) {
        logger.info("execute findByStarDateContaining");
        List<WorkPojo> listaWork = entityToPojoList(workrepository.findByStarDateContaining(stardate));
        return listaWork;
    }


    @Override
    public List<WorkPojo> findByDaysLateContaining(Long dayslate) {
        logger.info("execute findByDaysLateContaining");
        List<WorkPojo> listaWork = entityToPojoList(workrepository.findByDaysLateContaining(dayslate));
        return listaWork;
    }

    @Override
    public List<WorkPojo> findByTotalCostWorkWithoutTaxesContaining(Double totalcostworkwithouttaxes) {
        logger.info("execute findByTotalCostWorkWithoutTaxesContaining");
        List<WorkPojo> listaWork = entityToPojoList(workrepository.findByTotalCostWorkWithoutTaxesContaining(totalcostworkwithouttaxes));
        return listaWork;
    }

    @Override
    public List<WorkPojo> findByTotalCostWorkContaining(Double totalcostwork) {
        logger.info("execute findByTotalCostWorkContaining");
        List<WorkPojo> listaWork = entityToPojoList(workrepository.findByTotalCostWorkContaining(totalcostwork));
        return listaWork;
    }

    @Override
    public List<WorkPojo> findByRemainingPayableContaining(Double remainingpayable) {
        logger.info("execute findByRemainingPayableContaining");
        List<WorkPojo> listaWork = entityToPojoList(workrepository.findByRemainingPayableContaining(remainingpayable));
        return listaWork;
    }


    @Override
    public List<WorkPojo> findByRelacionClient(Client client) {
        List<WorkPojo> listaWork = new ArrayList<WorkPojo>();
        for (WorkPojo work : this.getAllWork()) {
            if (work.getClient().equals(clientMapper.entityToPojo(client))) {
                listaWork.add(work);
            }
        }
        listaWork.stream().forEach(work -> work.setDaysToDeline(this.diferencia(work.getDedline())));
        return listaWork;
    }


    @Override
    public List<WorkPojo> findByPaymentContaining(Payment payments) {
        logger.info("metodo: metodContainingRelacion NEW ");

        List<WorkPojo> listaWork = new ArrayList<WorkPojo>();

        for (WorkPojo work : this.getAllWork()) {
            for (PaymentPojo paymentsx : work.getPayments()) {
                if (paymentsx.equals(paymentMapper.entityToPojo(payments))) {
                    listaWork.add(work);
                }
            }
        }
        listaWork.stream().forEach(work -> work.setDaysToDeline(this.diferencia(work.getDedline())));
        return listaWork;

    }


    @Override
    public List<WorkPojo> findByBillContaining(Bill bills) {
        logger.info(" execute findByBillContaining");

        List<WorkPojo> listaWork = new ArrayList<WorkPojo>();

        for (WorkPojo work : this.getAllWork()) {
            for (BillPojo billsx : work.getBills()) {
                if (billsx.equals(billMapper.entityToPojo(bills))) {
                    listaWork.add(work);
                }
            }
        }
        listaWork.stream().forEach(work -> work.setDaysToDeline(this.diferencia(work.getDedline())));
        return listaWork;
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


}
