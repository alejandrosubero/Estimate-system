package com.template.parameter.mapper;


import com.template.parameter.entitys.Template;
import com.template.parameter.pojo.TemplatePojo;
import org.modelmapper.ModelMapper;
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
