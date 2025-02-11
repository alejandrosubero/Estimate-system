package com.jshandyman.service.mapper;

import com.jshandyman.service.entitys.Estimate;
import com.jshandyman.service.entitys.Work;
import com.jshandyman.service.pojo.EstimatePojo;
import com.jshandyman.service.pojo.WorkPojo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EstimateMapper {


    public Estimate pojoToEntity(EstimatePojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Estimate entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, Estimate.class);
        }
        return entity;
    }

    public EstimatePojo entityToPojo(Estimate entity) {
        ModelMapper modelMapper = new ModelMapper();
        EstimatePojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, EstimatePojo.class);
        }
        return pojo;
    }

    public List<EstimatePojo> entityToPojoList(List<Estimate> list){
        List<EstimatePojo> listaPojo = new ArrayList<EstimatePojo>();
        list.stream().forEach(entity -> {
            listaPojo.add(entityToPojo(entity));
        });
        return listaPojo;
    }

}
