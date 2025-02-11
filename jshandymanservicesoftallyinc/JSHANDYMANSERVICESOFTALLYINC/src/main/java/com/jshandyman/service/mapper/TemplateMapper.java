package com.jshandyman.service.mapper;

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.entitys.Template;
import com.jshandyman.service.pojo.BillPojo;
import com.jshandyman.service.pojo.TemplatePojo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemplateMapper {


    public Template pojoToEntity(TemplatePojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        Template entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, Template.class);
        }
        return entity;
    }

    public TemplatePojo entityToPojo(Template entity) {
        ModelMapper modelMapper = new ModelMapper();
        TemplatePojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, TemplatePojo.class);
        }
        return pojo;
    }


}
