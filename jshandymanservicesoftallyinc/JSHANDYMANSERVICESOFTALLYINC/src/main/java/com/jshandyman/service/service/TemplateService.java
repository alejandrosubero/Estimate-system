package com.jshandyman.service.service;

import com.jshandyman.service.entitys.Template;
import com.jshandyman.service.pojo.TemplatePojo;

import java.util.List;

public interface TemplateService {

    public Boolean saveNewTemplate(Template template);
    public  Boolean save(Template template);
    public  Boolean save2(Template template);
    public TemplatePojo findBycodeTemplate(String codeTemplate, String company);
    public List<TemplatePojo> findBycodeTemplateContaining(String codeTemplate);
    public List<Template> findByActive(Boolean active);
    public boolean deleteTemplete(Long id);
    public String getFileOfTemplate(String archivo);

}
