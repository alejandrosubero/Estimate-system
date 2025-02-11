package com.jshandyman.service.mapper;

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.entitys.Estimate;
import com.jshandyman.service.entitys.Subcontractor;
import com.jshandyman.service.pojo.BillPojo;
import com.jshandyman.service.pojo.EstimatePojo;
import com.jshandyman.service.pojo.SubcontractorPojo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubcontractorMapper {

    public Subcontractor pojoToEntity(SubcontractorPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Subcontractor entity = null;
        if (pojo != null) {
            entity = modelMapper.map(pojo, Subcontractor.class);
        }
        return entity;
    }

    public SubcontractorPojo entityToPojo(Subcontractor entity) {
        ModelMapper modelMapper = new ModelMapper();
        SubcontractorPojo pojo = null;
        if (entity != null) {
            pojo = modelMapper.map(entity, SubcontractorPojo.class);
        }
        return pojo;
    }


    public List<SubcontractorPojo> entityToPojoList(List<Subcontractor> list){
        List<SubcontractorPojo> listaPojo = new ArrayList<SubcontractorPojo>();
        list.stream().forEach(entity ->  listaPojo.add(entityToPojo(entity)));
        return listaPojo;
    }

}
