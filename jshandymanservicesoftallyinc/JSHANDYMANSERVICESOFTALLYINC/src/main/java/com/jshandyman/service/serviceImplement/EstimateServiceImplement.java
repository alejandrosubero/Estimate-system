package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.*;
import com.jshandyman.service.mapper.*;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.repository.EstimateRepository;
import com.jshandyman.service.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class EstimateServiceImplement implements EstimateService {


    protected static final Log logger = LogFactory.getLog(WorkServiceImplement.class);

    @Autowired
    private EstimateRepository estimateRepository;

    @Autowired
    private EstimateMapper mapper;

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
    private EstimateToInvoiceMapper estimateToInvoiceMapper;

    @Autowired
    private WorkService workService;

    @Autowired
    private AuditableService auditableService;

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    private Double totalSubcontractorsCost(List<Subcontractor> subcontractorList) {
        Double subcontractorsCost = 0.0;
        for (Subcontractor sub : subcontractorList) {
            if (sub.getTotalCost() != null) {
                subcontractorsCost += sub.getTotalCost();
            }
        }
        return subcontractorsCost;
    }

    private Double totalBillsCost(List<Bill> bills) {
        Double billCost = 0.0;
        for (Bill bill : bills) {
            if (bill.getBillTotal() != null) {
                billCost += bill.getBillTotal();
            }
        }
        return billCost;
    }

    private Estimate costCalculate(Estimate estimate) {

        Double subcontractorsCost = 0.0;
        Double billCost = 0.0;
        Double totalCostWork = 0.0;
        Double remainingPayable = 0.0;
        Double totalServicesCost = 0.0;

        if (estimate.getSubcontractors().size() > 0) {
            subcontractorsCost = this.totalSubcontractorsCost(estimate.getSubcontractors());
        }

        if (estimate.getBills().size() > 0) {
            billCost = totalBillsCost(estimate.getBills());
        }

        if (estimate.getServices().size() > 0) {
            for (ServiceHandyManTally service : estimate.getServices()) {
                if (service.getServicesCost() != null) {
                    totalServicesCost += service.getServicesCost();
                }
            }
        }

        totalCostWork = totalServicesCost + subcontractorsCost + billCost;
        estimate.setTotalCostWork(totalCostWork);

        if (estimate.getTotalAmountPaind() == null) {
            estimate.setTotalAmountPaind(0.0);
        }

        remainingPayable = estimate.getTotalCostWork() - estimate.getTotalAmountPaind();
        estimate.setRemainingPayable(remainingPayable);

        return estimate;
    }


    private Estimate setDelineAndUpdateAndCalulateAmountPaind(Estimate entity, Boolean one) {

        if (entity.getDedline() != null) {
            entity = setDelineM(entity);
            UpdateDedlinedays(entity.getDaysToDeline(), entity.getIdEstimate());
        }

        if(entity.getPayments() != null && entity.getPayments().size() > 0){
            entity.setTotalAmountPaind(totalAmountPaind(entity));
        }

       if(entity.getClient().getIdClient() != null){
        Client client = null;
        if (clientService.checkClienteInMap(entity.getClient().getIdClient())){
            client = clientMapper.pojoToEntity(clientService.returnClienteForMap(entity.getClient().getIdClient(), entity.getCompany()));
            entity.setClient(client);
        } else {
            entity.setClient(clientMapper.pojoToEntity(clientService.decrypt(entity.getClient())));
            clientService.addNewClientToMap(clientMapper.entityToPojo(entity.getClient()));
        }
    }
        return entity;
    }

    private Estimate setDelineAndUpdateAndCalulateAmountPaind(Estimate entity) {

        if (entity.getDedline() != null) {
            entity = setDelineM(entity);
            UpdateDedlinedays(entity.getDaysToDeline(), entity.getIdEstimate());
        }

        if(entity.getPayments() != null && entity.getPayments().size() > 0){
            entity.setTotalAmountPaind(totalAmountPaind(entity));
        }

        if(entity.getClient().getIdClient() != null){
            Client client = null;
            client = clientMapper.pojoToEntity(clientService.returnClienteForMap(entity.getClient().getIdClient(), entity.getCompany()));

            if (client.getIdClient() != null) {
                entity.setClient(client);
            } else {
                entity.setClient(clientMapper.pojoToEntity(clientService.decrypt(entity.getClient())));
            }
        }
//        entity = costCalculate(entity);
        return entity;
    }


    private Estimate setDelineM(Estimate w) {

        Long totalDaysToDedLine = 0L;

        if (w.getDedline() != null) {
            totalDaysToDedLine = diferencia(w.getDedline());
        }

        if (totalDaysToDedLine > 0) {
            w.setDaysToDeline(totalDaysToDedLine);
        } else {
            w.setDaysToDeline(0L);
            w.setDaysLate(totalDaysToDedLine * -1);
        }
        return w;
    }

    private void UpdateDedlinedays(Long daysToDeline, Long idWork) {
        estimateRepository.updateDedline(daysToDeline, idWork);
    }

    public Long diferencia(Date endDate) {
        LocalDate dateNow = convertToLocalDateViaInstant(new Date());
        LocalDate dedline = convertToLocalDateViaInstant(endDate);
        Long daysBetween = ChronoUnit.DAYS.between(dateNow, dedline);
        // Long dias = dateNow.until(dedline, ChronoUnit.DAYS);
        return daysBetween;
    }

    public Double totalAmountPaind(Estimate estimate) {
        Double total = 0.0;
        if (estimate.getPayments() != null && estimate.getPayments().size() > 0) {
            for (Payment paiment : estimate.getPayments()) {
                total += paiment.getAmountPaind();
            }
        }
        return total;
    }

    @Override
    public List<PaymentPojo> getAllPamentToEstimate(Long idWork) {
        return paymentService.findByIdWorkContaining(idWork);
    }


    @Override
    public List<EstimatePojo> getAllEstimate() {
        logger.info("execute all-Estimate");
        List<EstimatePojo> listaWork = new ArrayList<EstimatePojo>();
        estimateRepository.findAll()
                .forEach(estimate -> listaWork.add(
                        mapper.entityToPojo(
                                this.setDelineAndUpdateAndCalulateAmountPaind(estimate)
                        )));
        return listaWork;
    }

    @Override
    public List<EstimatePojo> getAllEstimateCompany(String company) {
        logger.info("execute all-Estimate");
        List<EstimatePojo> listaWork = new ArrayList<EstimatePojo>();
        estimateRepository.findByCompanyAndActive(company, true)
                .forEach(estimate -> listaWork.add(
                        mapper.entityToPojo(
                                this.setDelineAndUpdateAndCalulateAmountPaind(estimate)
                        )));
        return listaWork;
    }

    @Override
    public EstimatePojo findByCodeWork(String codeWork) {
        logger.info("Starting findByCodeWork");

        Estimate entity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByCodeWork(codeWork);

        if (fileOptional1.isPresent()) {
            try {
                entity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        logger.info("END => Starting findByCodeWork");
        return mapper.entityToPojo(entity);
    }


    @Override
    public List<ProductPojo> AllProductInEstimate(String codeWork) {
        List<ProductPojo> list = new ArrayList<ProductPojo>();

        EstimatePojo Estimate = this.findByCodeWork(codeWork);

        if (Estimate.getSubcontractors().size() > 0) {
            for (SubcontractorPojo sub : Estimate.getSubcontractors()) {
                sub.getBillListSubcontractor().stream()
                        .forEach(bill -> bill.getProductsAndServices()
                                .stream().forEach(product -> list.add(product)));
            }
        }
        if (Estimate.getBills().size() > 0) {
            Estimate.getBills().stream()
                    .forEach(billPojo -> billPojo.getProductsAndServices()
                            .stream().forEach(productPojo -> list.add(productPojo)));
        }
        return list;
    }


    @Override
    public List<BillPojo> AllBillInEstimate(String codeWork) {

        List<BillPojo> list = new ArrayList<BillPojo>();
        EstimatePojo estimate = this.findByCodeWork(codeWork);
        if (estimate.getSubcontractors().size() > 0 && estimate.getSubcontractors().get(0).getBillListSubcontractor().size() > 0) {
            for (SubcontractorPojo sub : estimate.getSubcontractors()) {
                sub.getBillListSubcontractor().stream()
                        .forEach(bill -> list.add(bill));
            }
        }
        if (estimate.getBills().size() > 0) {
            estimate.getBills().stream()
                    .forEach(billPojo -> list.add(billPojo));
        }
        return list;
    }


    @Override
    public Boolean covertEstimateToWork(EstimatePojo estimate) {

        try {
            if (estimate == null){ return false; }
            EstimatePojo estimateNew = new EstimatePojo(estimate);
            estimateNew.setIdEstimate(estimate.getIdEstimate());
            Work work = estimateToInvoiceMapper.estimateToInvoicePojo(estimateNew);
            work.setCreateDay(new Date());
            work.setActive(true);
            work.setStatus(Constant.IN_PROGRESS);
            return workService.saveWork(work);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean saveEstimate(Estimate estimate) {
        logger.info("Save estimate");
        try {
            Client clientEncript = clientMapper.pojoToEntity(clientMapper.entityToPojo(clientService.encript(estimate.getClient())));
            estimate.setClient(clientEncript);
            estimateRepository.save(estimate);
            logger.info("End Save estimate = true");
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            logger.info("End Save estimate = false");
            return false;
        }
    }


    @Override
    public EstimatePojo saveUpdateEstimate(Estimate estimate) {
        logger.info("save and Update Estimate");
        try {
//            logger.info("start print pones in estimate");
//            estimate.getClient().getPhoneNumbers().forEach(System.out::println);
//            logger.info("end print pones in estimate");

            clientService.replaceClientToMap(clientMapper.entityToPojo(estimate.getClient()));
            estimate.setOwner(estimate.getClient().getName());
            Client clientEncript = clientMapper.pojoToEntity(clientMapper.entityToPojo(clientService.encript(estimate.getClient())));
            estimate.setClient(clientEncript);
            Estimate estimateBase = estimateRepository.findById(estimate.getIdEstimate()).get();
            Estimate estimateAuditable = auditableService.recursionUpdate(estimate, estimateBase);
            estimateRepository.save(estimateAuditable);
            EstimatePojo estimatePojo = this.findById(estimate.getIdEstimate());

            logger.info("End save&Update Estimate");
            return estimatePojo;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return null;
        }
    }



    @Override
    public EstimatePojo saveNewEstimate(Estimate estimate) {
        logger.info("save a New Estimate");
        try {
            EstimatePojo estimatePojo = null;
            String clave = "Estimate";
            if(estimate.getCodeWork() != null){
                clave = estimate.getCodeWork();
            }
            String code = this.createCode(clave);
            estimate.setCodeWork(code);
            estimate.setCreateDay(new Date());
            estimate.getClient().setCodeWork(code);
            estimate.getClient().setCompany(estimate.getCompany());
            String fullName = estimate.getClient().getName() +" "+estimate.getClient().getLastName();
            estimate.getClient().setFullName(fullName);
            estimate.getClient().setActive(true);
            estimate.setOwner(estimate.getClient().getName()); ///
            estimate.setActive(true);
            estimate.setStatus(Constant.IN_PROGRESS);
            this.saveEstimate(estimate);

            logger.info("End save a New Estimate...");

            return this.findByCodeWorkStandar(code);

        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return null;
        }
    }

    private EstimatePojo findByCodeWorkStandar(String codeWork) {
        logger.info("Starting findByCodeWorkStandar");
        EstimatePojo response = new EstimatePojo();
        Estimate entity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByCodeWork(codeWork);
        if (fileOptional1.isPresent()) {
            try {
                entity = addIdEstimate(fileOptional1.get());
                if (entity.getIdEstimate() != null){
                    response =  mapper.entityToPojo(entity);
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

    private Estimate addIdEstimate(Estimate estimate) {
        Long id = estimate.getIdEstimate();
        if (estimate.getIdEstimate() != null) {
            if (estimate.getBills() != null && estimate.getBills().size() > 0) {
                estimate.getBills().stream().forEach(billPojo -> {
                    billPojo.setIdEstimate(id);
                    billPojo.getProductsAndServices().stream().forEach(productPojo -> productPojo.setIdBill(billPojo.getIdBill()));
                });
            }
            if (estimate.getServices() != null && estimate.getServices().size() > 0) {
                estimate.getServices().stream().forEach(pojo -> pojo.setIdEstimate(id));
            }
            if (estimate.getSubcontractors() != null && estimate.getSubcontractors().size() > 0){
                estimate.getSubcontractors().stream().forEach(pojo -> pojo.setIdEstimate(id));
            }

            if(estimate.getClient() != null){
                estimate.getClient().setEstimateId(id);
            }
            estimateRepository.save(estimate);
        }
        return estimate;
    }


    private String createCode(String code) {
        return generateCode(code);
    }

    private String generateCode(String cod) {
        String code = cod + "*" + UUID.randomUUID().toString();
        EstimatePojo estimatePojo = this.findByCodeWork(code);
        if (estimatePojo.getCodeWork() != null && estimatePojo.getCodeWork().equals(code)) {
            generateCode(cod);
        }
        return code;
    }


    @Override
    public List<EstimatePojo> finBySearch(String keyword) {
        return this.entityToPojoList(estimateRepository.finBySearch(keyword));
    }


    @Override
    public List<EstimateListTabletPojo> findByCreateDayMonthYear(Double month, Double year) {
        logger.info("execute find By Create Day and Month of Year");
        return this.pojoList(estimateRepository.finByCreateDayMonthYear(month.intValue(), year.intValue()));
    }


    @Override
    public List<EstimateListTabletPojo> findByStatusContaining(String status) {
        logger.info("execute find By Create Day and Month of Year");
        return this.pojoList(estimateRepository.findByStatusContaining(status));
    }


    @Override
    public List<EstimateListTabletPojo> findByCreateDayBetween(Date startDate, Date endDay) {
        logger.info("execute findBy Create Day Between");
        try {
            return this.pojoList(estimateRepository.finByCreateDayBetween(startDate, endDay));
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<EstimateListTabletPojo>();
        }
    }


    @Override
    public List<EstimateListTabletPojo> finBySearchToListTablet(String keyword){
        logger.info("execute find By Search ListTablet ");
        return pojoList(estimateRepository.finBySearch(keyword));
    }


    private List<EstimateListTabletPojo> pojoList(List<Estimate> estimates){
        logger.info("execute pojo List");
        List<EstimateListTabletPojo> lista = new ArrayList<EstimateListTabletPojo>();

        estimates.stream().forEach(estimate -> {
            if(estimate.getActive()){
                lista.add(new EstimateListTabletPojo(
                                estimate.getIdEstimate(),
                                estimate.getTotalCostWork(),
                                estimate.getCreateDay(),
                                estimate.getOwner(),
                                estimate.getTitle(),
                                estimate.getDescription(),
                                estimate.getStatus()
                        ));
            }
        });
        return lista;
    }




    @Override
    public List<EstimateListTabletPojo> estimateListTablet(List<Long> listId) {
        logger.info("execute get-Estimate List-Tablet estimate id");
        List<EstimateListTabletPojo> listaWork = new ArrayList<EstimateListTabletPojo>();
        List<Estimate> listEstimate = new ArrayList<Estimate>();
        listId.stream().forEach(id -> listEstimate.add(estimateRepository.findById(id).get()));
        listEstimate.stream().forEach(estimate -> {
            if(estimate.getActive()){
                listaWork.add(new EstimateListTabletPojo(estimate.getIdEstimate(),
                        estimate.getTotalCostWork(), estimate.getCreateDay(), estimate.getOwner(),
                        estimate.getTitle(), estimate.getDescription(), estimate.getStatus()));
            }
        });
        return listaWork;
    }


    @Override
    public List<EstimateListTabletPojo> getEstimateListTablet(String company) {
        logger.info("execute get-Estimate List-Tablet");
        List<EstimateListTabletPojo> listaWork = new ArrayList<EstimateListTabletPojo>();

        estimateRepository.findByCompanyAndActive(company, true).forEach(estimate -> {
                        listaWork.add(new EstimateListTabletPojo(
                                estimate.getIdEstimate(),
                                estimate.getTotalCostWork(),
                                estimate.getCreateDay(),
                                estimate.getOwner(),
                                estimate.getTitle(),
                                estimate.getDescription(),
                                estimate.getStatus()
                        ));
                });


//        estimateRepository.findAll()
//                .forEach(estimate -> {
//            if(estimate.getActive()){
//                listaWork.add(new EstimateListTabletPojo(
//                        estimate.getIdEstimate(),
//                        estimate.getTotalCostWork(),
//                        estimate.getCreateDay(),
//                        estimate.getOwner(),
//                        estimate.getTitle(),
//                        estimate.getDescription(),
//                        estimate.getStatus()
//                ));
//            }
//        });
        return listaWork;
    }


    @Override
    public List<EstimatePojo>findByActive(Boolean active){
        return this.entityToPojoList(estimateRepository.findByActive(active));
    }

    @Override
    public EstimatePojo findByDescription(String description) {
        logger.info("Starting getWork");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByDescription(description);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }

    @Override
    public EstimatePojo findByDedline(Date dedline) {
        logger.info("Starting getWork");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByDedline(dedline);

        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }

    @Override
    public EstimatePojo findByStarDate(Date StarDate) {
        logger.info("Starting getWork");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByStarDate(StarDate);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }


    @Override
    public EstimatePojo findByDaysToDeline(Long daysToDeline) {
        logger.info("Starting daysToDeline");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByDaysToDeline(daysToDeline);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR in findByDaysToDeline: " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }


    @Override
    public EstimatePojo findByDaysLate(Long daysLate) {
        logger.info("Starting findByDaysLate");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByDaysLate(daysLate);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }

    @Override
    public EstimatePojo findByTotalCostEstimateWithoutTaxes(Double totalCostWorkWithoutTaxes) {

        logger.info("Starting findByTotalCostWorkWithoutTaxes");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByTotalCostWorkWithoutTaxes(totalCostWorkWithoutTaxes);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }

    @Override
    public EstimatePojo findByTotalCostEstimate(Double totalCostWork) {
        logger.info("Starting getWork");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByTotalCostWork(totalCostWork);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }

    @Override
    public EstimatePojo findByRemainingPayable(Double remainingPayable) {
        logger.info("Starting getWork");
        Estimate workEntity = new Estimate();
        Optional<Estimate> fileOptional1 = estimateRepository.findByRemainingPayable(remainingPayable);
        if (fileOptional1.isPresent()) {
            try {
                workEntity = setDelineAndUpdateAndCalulateAmountPaind(fileOptional1.get());
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return mapper.entityToPojo(workEntity);
    }


    @Override
    public boolean deleteEstimate(Long id) {
        logger.info("Delete Estimate");
        boolean clave = false;
        try {
            estimateRepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public boolean deleteEstimateLogic(Long id) {
        logger.info("Delete Estimate");
        boolean clave = false;
        try {
            estimateRepository.updateActive(false,id);
            clientService.updateActiveForEstimateId(id);
            clientService.remuveClientToMapForId(id, Constant.ESTIMATE);

            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public EstimatePojo findById(Long id) {
        Estimate w = setDelineAndUpdateAndCalulateAmountPaind(estimateRepository.findById(id).get(), null);
        if (w.getDedline() != null) {
            w.setDaysToDeline(diferencia(w.getDedline()));
        }
        return mapper.entityToPojo(w);
    }


    private List<EstimatePojo> entityToPojoList(List<Estimate> estimate) {
        List<EstimatePojo> listaWork = new ArrayList<EstimatePojo>();
        estimate.stream().forEach(est -> listaWork.add(mapper.entityToPojo(setDelineAndUpdateAndCalulateAmountPaind(est))));
        return listaWork;
    }





    //===========================================================================================

    @Override
    public List<EstimatePojo> findByDaysToDelineContaining(Long daystodeline) {
        logger.info("Get findByDaysToDelineContaining");
        List<EstimatePojo> lista = new ArrayList<EstimatePojo>();
        lista = entityToPojoList(estimateRepository.findByDaysToDelineContaining(daystodeline));
        return lista;
    }


    @Override
    public List<EstimatePojo> findByCodeEstimateContaining(String codework) {
        logger.info("Get findByCodeEstimateContaining");
        List<EstimatePojo> listaWork = entityToPojoList(estimateRepository.findByCodeWorkContaining(codework));
        return listaWork;
    }

    @Override
    public List<EstimatePojo> findByDescriptionContaining(String description) {
        logger.info("execute findByDescriptionContaining");
        List<EstimatePojo> listaWork = entityToPojoList(
                estimateRepository.findByDescriptionContaining(description)
        );
        return listaWork;
    }

    @Override
    public List<EstimatePojo> findByDedlineContaining(Date dedline) {
        logger.info("execute findByDedlineContaining");
        List<EstimatePojo> listaWork = entityToPojoList(estimateRepository.findByDedlineContaining(dedline));
        return listaWork;
    }

    @Override
    public List<EstimatePojo> findByStarDateContaining(Date stardate) {
        logger.info("execute findByStarDateContaining");
        List<EstimatePojo> listaWork = entityToPojoList(estimateRepository.findByStarDateContaining(stardate));
        return listaWork;
    }


    @Override
    public List<EstimatePojo> findByDaysLateContaining(Long dayslate) {
        logger.info("execute findByDaysLateContaining");
        List<EstimatePojo> listaWork = entityToPojoList(estimateRepository.findByDaysLateContaining(dayslate));
        return listaWork;
    }

    @Override
    public List<EstimatePojo> findByTotalCostEstimateWithoutTaxesContaining(Double totalcostworkwithouttaxes) {
        logger.info("execute findByTotalCostEstimateWithoutTaxesContaining");
        List<EstimatePojo> listaWork = entityToPojoList(estimateRepository.findByTotalCostWorkWithoutTaxesContaining(totalcostworkwithouttaxes));
        return listaWork;
    }

    @Override
    public List<EstimatePojo> findByTotalCostEstimateContaining(Double totalcostwork) {
        logger.info("execute findByTotalCostWorkContaining");
        List<EstimatePojo> listaWork = entityToPojoList(estimateRepository.findByTotalCostWorkContaining(totalcostwork));
        return listaWork;
    }

    @Override
    public List<EstimatePojo> findByRemainingPayableContaining(Double remainingpayable) {
        logger.info("execute findByRemainingPayableContaining");
        List<EstimatePojo> listaWork = entityToPojoList(estimateRepository.findByRemainingPayableContaining(remainingpayable));
        return listaWork;
    }


    @Override
    public List<EstimatePojo> findByRelacionClient(Client client) {
        List<EstimatePojo> listaWork = new ArrayList<EstimatePojo>();
        for (EstimatePojo work : this.getAllEstimate()) {
            if (work.getClient().equals(clientMapper.entityToPojo(client))) {
                listaWork.add(work);
            }
        }
        listaWork.stream().forEach(work -> work.setDaysToDeline(this.diferencia(work.getDedline())));
        return listaWork;
    }


    @Override
    public List<EstimatePojo> findByPaymentContaining(Payment payments) {
        logger.info("metodo: metodContainingRelacion NEW ");

        List<EstimatePojo> listaWork = new ArrayList<EstimatePojo>();

        for (EstimatePojo estimate : this.getAllEstimate()) {
            for (PaymentPojo paymentsx : estimate.getPayments()) {
                if (paymentsx.equals(paymentMapper.entityToPojo(payments))) {
                    listaWork.add(estimate);
                }
            }
        }
        listaWork.stream().forEach(work -> work.setDaysToDeline(this.diferencia(work.getDedline())));
        return listaWork;

    }


    @Override
    public List<EstimatePojo> findByBillContaining(Bill bills) {
        logger.info(" execute findByBillContaining");
        List<EstimatePojo> listaWork = new ArrayList<EstimatePojo>();
        for (EstimatePojo estimate : this.getAllEstimate()) {
            for (BillPojo billsx : estimate.getBills()) {
                if (billsx.equals(billMapper.entityToPojo(bills))) {
                    listaWork.add(estimate);
                }
            }
        }
        listaWork.stream().forEach(work -> work.setDaysToDeline(this.diferencia(work.getDedline())));
        return listaWork;
    }




}
