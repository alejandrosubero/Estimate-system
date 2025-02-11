package com.template.parameter.service;



import com.template.parameter.entitys.Template;
import com.template.parameter.pojo.TemplatePojo;

import java.util.List;

public interface TemplateService {

    public Boolean saveList(List<TemplatePojo> templates);
    public Boolean saveNewTemplate(Template template);
    public  Boolean save(Template template);
    public TemplatePojo findBycodeTemplate(String codeTemplate);
    public List<TemplatePojo> findBycodeTemplateContaining(String codeTemplate);
    public List<Template> findByActive(Boolean active);
    public boolean deleteTemplete(Long id);
    public String getFileOfTemplate(String archivo);
    public List<TemplatePojo> allTemplate();
}
