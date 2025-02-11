package com.service.mail.mapper;


import com.service.mail.entitys.Parameters;
import com.service.mail.pojo.ParametersPojo;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

@Component
public class ParametersMapper {

    public Parameters pojoToEntity(ParametersPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Parameters entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, Parameters.class);
        }
        return entity;
    }

    public ParametersPojo entityToPojo(Parameters entity) {
        ModelMapper modelMapper = new ModelMapper();
        ParametersPojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, ParametersPojo.class);
        }
        return pojo;
    }

}
