package com.jshandyman.service.mapper;

import com.jshandyman.service.entitys.Estimate;
import com.jshandyman.service.entitys.Work;
import com.jshandyman.service.pojo.EstimatePojo;
import com.jshandyman.service.pojo.WorkPojo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstimateToInvoiceMapper {


    @Autowired
    private WorkMapper workMapper;



    public Work estimateToInvoicePojo(EstimatePojo estimate) {
        ModelMapper modelMapper = new ModelMapper();
        WorkPojo work = null;

        if (estimate != null) {
            work = modelMapper.map(estimate, WorkPojo.class);
            String code = "W-"+work.getCodeWork();
            work.setCodeWork(code);
            work.getClient().setCodeWork(code);
        }
        return workMapper.pojoToEntity(work);
    }



    public Work estimateToInvoice(Estimate estimate) {
        ModelMapper modelMapper = new ModelMapper();
        Work work = null;

        if (estimate != null) {
            work = modelMapper.map(estimate, Work.class);
        }
        return work;
    }



    public Estimate invoiceToEstimate(Work work) {
        ModelMapper modelMapper = new ModelMapper();
        Estimate estimate = null;

        if (work != null) {
            estimate = modelMapper.map(work, Estimate.class);
        }
        return estimate;
    }

}
