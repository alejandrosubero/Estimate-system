package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.mapper.EstimateMapper;
import com.jshandyman.service.mapper.WorkMapper;
import com.jshandyman.service.pojo.SearchResponsePojo;
import com.jshandyman.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SearchServiceImplement implements SearchService {

    @Autowired
    private EstimateMapper estimateMapper;

    @Autowired
    private EstimateService estimateService;

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private ServiceHandyManTallyService serviceHandyManTallyService;

    @Autowired
    private SubcontractorService subcontractorService;


    @Override
    public SearchResponsePojo finByCreateDayBetween(Date startDate, Date endDay, String clave) {

        if (Constant.CREATE.equals(clave.toLowerCase())) {
            return new SearchResponsePojo(
                    workService.findByCreateDayBetween(startDate, endDay),
                    estimateService.findByCreateDayBetween(startDate, endDay),
                    null,
                    null,
                    null,
                    null
            );
        } else {
            return new SearchResponsePojo(
                    workService.findByStartDayBetween(startDate, endDay),
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
    }


    @Override
    public SearchResponsePojo findByCreateDayMonthYear(Double month, Double year, String clave) {

        if (Constant.CREATE.equals(clave.toLowerCase())) {
            return new SearchResponsePojo(
                    workService.findByCreateDayMonthYear(month, year),
                    estimateService.findByCreateDayMonthYear(month, year),
                    null,
                    null,
                    null,
                    null
            );
        } else {
            return new SearchResponsePojo(
                    workService.findByStartDayMonthYear(month, year),
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
    }

    /***
     *
     * @param keyword
     * @return SearchResponsePojo only wich data work in tablet, Estimate in tablet, service and subcontractors
     */
    @Override
    public SearchResponsePojo finBySearch(String keyword) {

        return new SearchResponsePojo(
                workService.findBySearchToListTablet(keyword),
                estimateService.finBySearchToListTablet(keyword),
                serviceHandyManTallyService.finBySearch(keyword),
                null,
                null,
                subcontractorService.finBySearch(keyword)
        );
    }

    /***
     *
     * @param keyword
     * @return  SearchResponsePojo only data invoice, estimate and service
     */
    @Override
    public SearchResponsePojo finBySearchResponsePojos(String keyword) {

        return new SearchResponsePojo(
                workService.findBySearch(keyword),
                estimateService.finBySearch(keyword),
                serviceHandyManTallyService.finBySearch(keyword)
        );
    }


}
