package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.Template;
import com.jshandyman.service.mapper.TemplateMapper;
import com.jshandyman.service.pojo.TemplatePojo;
import com.jshandyman.service.repository.TemplateRepository;
import com.jshandyman.service.service.TemplateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateServiceImplement implements TemplateService {

    protected static final Log logger = LogFactory.getLog(TemplateServiceImplement.class);

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateRepository templateRepository;


    @Override
    public Boolean saveNewTemplate(Template template) {
        Template templateBase = null;
        try {
            templateBase = templateRepository.findBycodeTemplate(template.getCodeTemplate());
            if (templateBase != null && templateBase.getCodeTemplate() != null
                    && !templateBase.getCodeTemplate().equals(template.getCodeTemplate())) {
                return false;
            }
            template.setTemplate(this.getFileOfTemplate(Constant.TEMPLATENAME));
            templateRepository.save(template);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean save(Template template) {
        this.logger.info("Start the set template");
        Template templateBase = null;
        try {
            templateBase = templateRepository.findBycodeTemplate(template.getCodeTemplate());
            if (templateBase != null && templateBase.getIdTemplete() != null) {
                templateRepository.deleteById(templateBase.getIdTemplete());
            }
            template.setTemplate(this.getFileOfTemplate(Constant.TEMPLATENAME));
            templateRepository.save(template);
            this.logger.info("end el set template");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info("end set template in error");
            this.logger.error("end set template in error", e);
            return false;
        }
    }

    @Override
    public Boolean save2(Template template) {
        this.logger.info("Start the set template");
        Template templateBase = null;
        try {
            templateBase = templateRepository.findBycodeTemplate(template.getCodeTemplate());
            if (templateBase != null && templateBase.getIdTemplete() != null) {
                templateRepository.deleteById(templateBase.getIdTemplete());
            }
            templateRepository.save(template);
            this.logger.info("end el set template");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info("end set template in error");
            this.logger.error("end set template in error", e);
            return false;
        }
    }
    @Override
    public boolean deleteTemplete(Long id) {
        try {
            templateRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public TemplatePojo findBycodeTemplate(String codeTemplate, String company) {
        TemplatePojo pojo = null;
        try {
            // pojo = templateMapper.entityToPojo(templateRepository.findBycodeTemplate(codeTemplate));
            return TemplatePojo.newTemplate(templateRepository.findBycodeTemplateAndCompany(codeTemplate, company));
            //  return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }


    @Override
    public List<TemplatePojo> findBycodeTemplateContaining(String codeTemplate) {
        List<TemplatePojo> lista = new ArrayList<TemplatePojo>();
        try {
            templateRepository.findBycodeTemplateContaining(codeTemplate)
                    .stream().forEach(template -> lista.add(templateMapper.entityToPojo(template)));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return lista;
        }
    }

    @Override
    public List<Template> findByActive(Boolean active) {
        List<Template> lista = new ArrayList<Template>();

        try {
            templateRepository.findByActive(active).stream().forEach(template -> lista.add(template));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return lista;
        }
    }


    @Override
    public String getFileOfTemplate(String archivo) {
        this.logger.info("get-File-Of-Template...");
        String cadena;
        FileReader f = null;
        StringBuilder sb = new StringBuilder("");

        try {
            String content = new String(Files.readAllBytes(Paths.get(Constant.templateFile + archivo)));
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }

        try {
                f = new FileReader(Constant.templateFile + archivo);
                BufferedReader b = new BufferedReader(f);
                while ((cadena = b.readLine()) != null) {
                    sb.append(cadena);
                }
                b.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException a) {
            a.printStackTrace();
        }
        return sb.toString();
    }


}
