package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.Estimate;
import com.jshandyman.service.entitys.Payment;
import com.jshandyman.service.entitys.Work;
import com.jshandyman.service.mapper.EstimateMapper;
import com.jshandyman.service.mapper.PaymentMapper;
import com.jshandyman.service.mapper.WorkMapper;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.repository.EstimateRepository;
import com.jshandyman.service.repository.SendMailTrakerRepository;
import com.jshandyman.service.repository.WorkRepository;
import com.jshandyman.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class DashboardDataImplement implements DashboardDataService {

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkRepository workrepository;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private EstimateService estimateService;

    @Autowired
    private EstimateRepository estimateRepository;

    @Autowired
    private EstimateMapper mapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AuditableService auditableService;

    @Autowired
    private SendMailTrakerRepository sendMailTrakerRepository;

    @Autowired
    private SendMailTrakerService trakerService;

    @Autowired
    private DataJshandyManServicesConfigServiceImplement dataJshandyMan;

    @Override
    public DashboardDataPojo getStatistics(String company) {

        System.out.printf(company);
        DashboardDataPojo pojo = new DashboardDataPojo();
        Date date = new Date();
        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
        int month = getLocalDate.getMonthValue();
        int year = getLocalDate.getYear();

        try {
            Double totalCharge = 0.0;
            Double totalAumontInYear = 0.0;
            Double totalRemainPayableInYear = 0.0;
            Double totalEstimateApprovedInYear = 0.0;

            List <Work> finByStartDayYear =  this.workService.finByStartDayYear(year, company);
            List<Work> ListWorkCreateDayInYear = this.workService.finByCreateDayYear(year, company);
            List<Work> ListWorkCreateDayInMonth = this.workrepository.finByCreateDayMonth(month, company);
            List<Work> ListWorkFinalDateInMonth = this.workrepository.finByfinalDateInMonth(month, company);
            List<Work> ListWorkFinalDateInYear = this.workrepository.finByfinalDateInYear(year, company);
            List<Estimate> ListEstimateCreateDayInMonth = this.estimateRepository.finByCreateDayMonth(month, company);
            List<Estimate> ListEstimateCreateDayInYear = this.estimateRepository.finByCreateDayYear(year, company);
            List<Estimate> ListEstimateCreateDayInYearApproved = this.estimateRepository.finByCreateDayYearApproved(year, Constant.APPROVED, company);



            if(finByStartDayYear.size() > 0){

                 List<Work> listAmountPaind = new ArrayList<>();
                 List<Work> listRemainingPayable = new ArrayList<>();
                 List<Work> listInvoiceInYear = new ArrayList<>();

                for (Work work : finByStartDayYear ){

                    if(work.getTotalAmountPaind() != null && work.getTotalAmountPaind() != 0 ) {
                        totalAumontInYear += work.getTotalAmountPaind();
                        listAmountPaind.add(work);
                    }

                    if(work.getRemainingPayable() != null && work.getRemainingPayable() > 0){
                        totalRemainPayableInYear += work.getRemainingPayable();
                        listRemainingPayable.add(work);
                    }

                    if(work.getTotalCostWork() != null){
                        totalCharge += work.getTotalCostWork();
                        listInvoiceInYear.add(work);
                    }
                }
                pojo.setListAmountPaind( this.workService.pojoList(listAmountPaind));
                pojo.setListRemainingPayable(this.workService.pojoList(listRemainingPayable));
                pojo.setListInvoiceInYear(this.workService.pojoList(listInvoiceInYear));
            }

            if( ListEstimateCreateDayInYearApproved != null && ListEstimateCreateDayInYearApproved.size() > 0){
                for ( Estimate estimate: ListEstimateCreateDayInYearApproved) {
                    totalEstimateApprovedInYear += estimate.getTotalCostWork();
                }
            }

            pojo.setTotalAmountPaindIntYear(totalAumontInYear);
            pojo.setTotalInvoicedInTheYear(totalCharge);
            pojo.setTotalRemainingPayableInTheYear(totalRemainPayableInYear);
            pojo.setWorksCreateInTheMonth((long) ListWorkCreateDayInMonth.size());
            pojo.setWorksCreateInTheYear((long) ListWorkCreateDayInYear.size());
            pojo.setNumberWorksStarted((long) finByStartDayYear.size());
            pojo.setWorkFinalDateInMonth((long) ListWorkFinalDateInMonth.size());
            pojo.setWorkFinalDateInYear((long) ListWorkFinalDateInYear.size() );

            List<SendMailTrakerPojo> listTraker= trakerService.allSendMailTraker(company);

            if(listTraker != null && listTraker.size() > 0) {
                HashMap<Long, SendMailTrakerPojo> mapTrakerWork = new HashMap<Long, SendMailTrakerPojo>();
                HashMap<Long, SendMailTrakerPojo> mapTrakerEstimete = new HashMap<Long, SendMailTrakerPojo>();
                List<Long> idWorksend = new ArrayList<>();
                List<Long> idEstimatesend = new ArrayList<>();

                for (SendMailTrakerPojo tracker : listTraker) {
                    if (tracker.getIdWorkReferene() != null && !mapTrakerWork.containsKey(tracker.getIdWorkReferene())) {
                        mapTrakerWork.put(tracker.getIdWorkReferene(), tracker);
                        idWorksend.add(tracker.getIdWorkReferene());
                    }

                    if (tracker.getIdEstimateReferene() != null && !mapTrakerEstimete.containsKey(tracker.getIdEstimateReferene())) {
                        mapTrakerEstimete.put(tracker.getIdEstimateReferene(), tracker);
                        idEstimatesend.add(tracker.getIdEstimateReferene());
                    }
                }
                pojo.setNumberWorksSend((long) mapTrakerWork.size());
                pojo.setNumberEstimatesSend((long) mapTrakerEstimete.size());
                pojo.setListWorksSend(workService.workListTablet(idWorksend));
                pojo.setListEstimatesSend(estimateService.estimateListTablet(idEstimatesend));
            }else{
                pojo.setNumberWorksSend(0L);
                pojo.setNumberEstimatesSend(0l);
            }


            pojo.setListWorksFinalices(this.workService.findByActiveAndStatus(true, Constant.FINALIZED, company));
            pojo.setNumberWorksEnd(this.workrepository.countByActiveAndStatusAndCompany(true,Constant.FINALIZED , company));


            pojo.setNumberWorksCreate( this.workrepository.countByActiveAndStatusAndCompanyOrStatusOrStatus(true, Constant.IN_PROGRESS,company, Constant.PAUSE, Constant.SEND));

            pojo.setNumberWorksInProgress(this.workrepository.countByActiveAndStatusAndCompany(true,Constant.IN_PROGRESS, company ));
            pojo.setListWorksInProgress(this.workService.findByActiveAndStatus(true, Constant.IN_PROGRESS, company));

            pojo.setNumberWorksInPause(this.workrepository.countByActiveAndStatusAndCompany(true,Constant.PAUSE, company));
            pojo.setListWorksPause(this.workService.findByActiveAndStatus(true, Constant.PAUSE, company));

            pojo.setNumberWorksEndBeforeDedLine(this.workrepository.countByActiveAndCompanyAndStatusAndDaysToDelineGreaterThan(true, company, Constant.FINALIZED, 0L));
            pojo.setNumberWorksEndAfterDedLine(this.workrepository.countByActiveAndStatusAndCompanyAndDaysToDelineLessThanEqual(true, Constant.FINALIZED, company, 0L));
            pojo.setWorksEndBeforeDedLine(this.workService.findByActiveAndStatusAndDaysToDelineGreaterThanEqual(true, Constant.FINALIZED, 0l, company));

            DataJshandyManServicesConfigPojo confipojo = dataJshandyMan.getActive(company);
            if(confipojo!= null && confipojo.getDeadLineAlert() != null){
                 pojo.setWorksBeforeDedLineAlert(this.workService.worksBeforeDedLineAlert(Long.valueOf((confipojo.getDeadLineAlert())), Constant.IN_PROGRESS,Constant.PAUSE, company));
            }else {
                pojo.setWorksBeforeDedLineAlert(new ArrayList<WorkListTabletPojo>());
            }

            pojo.setNumberEstimatesCreate(this.estimateRepository.countByActiveAndCompanyAndStatusOrStatusOrStatusOrStatus(true, company, Constant.IN_PROGRESS, Constant.PAUSE, Constant.SEND, Constant.APPROVED));
            pojo.setNumberEstimatesApproved(this.estimateRepository.countByActiveAndStatusAndCompany(true,Constant.APPROVED, company));
            pojo.setNumberEstimatesInProgress(this.estimateRepository.countByActiveAndStatusAndCompany(true,Constant.IN_PROGRESS, company));
            pojo.setEstimatesCreateInTheMonth((long) ListEstimateCreateDayInMonth.size());
            pojo.setEstimatesCreateInTheYear((long) ListEstimateCreateDayInYear.size());
            pojo.setTotalEstimateApprovedInYear(totalEstimateApprovedInYear);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return pojo;
    }

    @Override
    public MapTrakerMail trakerSendMails(String company) {
        List<SendMailTrakerPojo> listTraker = trakerService.allSendMailTraker(company);
        HashMap<Long, SendMailTrakerPojo> mapTrakerWork =null;
        HashMap<Long, SendMailTrakerPojo> mapTrakerEstimete = null;

        if (listTraker != null && listTraker.size() > 0) {
           mapTrakerWork = new HashMap<Long, SendMailTrakerPojo>();
           mapTrakerEstimete = new HashMap<Long, SendMailTrakerPojo>();

            for (SendMailTrakerPojo tracker : listTraker) {
                if (tracker.getIdWorkReferene() != null && !mapTrakerWork.containsKey(tracker.getIdWorkReferene())) {
                    mapTrakerWork.put(tracker.getIdWorkReferene(), tracker);
                }

                if (tracker.getIdEstimateReferene() != null && !mapTrakerEstimete.containsKey(tracker.getIdEstimateReferene())) {
                    mapTrakerEstimete.put(tracker.getIdEstimateReferene(), tracker);
                }
            }
        }
        return new MapTrakerMail(mapTrakerWork, mapTrakerEstimete);
    }

}
