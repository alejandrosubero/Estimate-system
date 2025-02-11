package com.jshandyman.service.mapper;

import com.jshandyman.service.entitys.Product;
import com.jshandyman.service.entitys.SendMailTraker;
import com.jshandyman.service.pojo.ProductPojo;
import com.jshandyman.service.pojo.SendMailTrakerPojo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SendMailTrakerMapper {

    public SendMailTraker pojoToEntity(SendMailTrakerPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        SendMailTraker entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, SendMailTraker.class);
        }
        return entity;
    }

    public SendMailTrakerPojo entityToPojo(SendMailTraker entity) {
        ModelMapper modelMapper = new ModelMapper();
        SendMailTrakerPojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, SendMailTrakerPojo.class);
        }
        return pojo;
    }
}
