package com.jshandyman.service.mapper;

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.entitys.ServiceHandyManTally;
import com.jshandyman.service.pojo.BillPojo;
import com.jshandyman.service.pojo.ServiceHandyManTallyPojo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceHandyManTallyMapper {

    public ServiceHandyManTally pojoToEntity(ServiceHandyManTallyPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceHandyManTally entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, ServiceHandyManTally.class);
        }
        return entity;
    }

    public ServiceHandyManTallyPojo entityToPojo(ServiceHandyManTally entity) {
        ModelMapper modelMapper = new ModelMapper();
        ServiceHandyManTallyPojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, ServiceHandyManTallyPojo.class);
        }
        return pojo;
    }

    public List<ServiceHandyManTallyPojo> entityToPojoList(List<ServiceHandyManTally> listEntity){
        List<ServiceHandyManTallyPojo> list = new ArrayList<ServiceHandyManTallyPojo>();
        listEntity.stream().forEach(entity->list.add(this.entityToPojo(entity)));
        return  list;
    }
}
