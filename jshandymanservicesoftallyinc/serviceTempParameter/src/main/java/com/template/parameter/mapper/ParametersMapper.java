package com.template.parameter.mapper;


import com.template.parameter.entitys.Parameters;
import com.template.parameter.pojo.ParametersPojo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
