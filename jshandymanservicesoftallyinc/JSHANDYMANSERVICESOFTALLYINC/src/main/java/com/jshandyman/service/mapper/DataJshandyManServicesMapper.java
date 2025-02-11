package com.jshandyman.service.mapper;

import com.jshandyman.service.entitys.DataJshandyManServicesConfig;
import com.jshandyman.service.pojo.DataJshandyManServicesConfigPojo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DataJshandyManServicesMapper {


    public DataJshandyManServicesConfigPojo entityToPojo(DataJshandyManServicesConfig entidad) {
        ModelMapper modelMapper = new ModelMapper();
        DataJshandyManServicesConfigPojo pojo = null;
        if (entidad != null) {
            pojo = modelMapper.map(entidad, DataJshandyManServicesConfigPojo.class);
        }
        return pojo;
    }


    public DataJshandyManServicesConfig pojoToEntity( DataJshandyManServicesConfigPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        DataJshandyManServicesConfig entity = null;
        if (pojo != null) {
            entity = modelMapper.map(pojo, DataJshandyManServicesConfig.class);
        }
        return entity;
    }
}
