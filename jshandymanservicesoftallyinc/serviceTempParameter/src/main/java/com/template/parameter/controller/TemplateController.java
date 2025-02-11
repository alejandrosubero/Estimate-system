package com.template.parameter.controller;


import com.template.parameter.configurations.Constant;
import com.template.parameter.mapper.MapperEntityRespone;
import com.template.parameter.mapper.TemplateMapper;
import com.template.parameter.pojo.EntityRespone;
import com.template.parameter.pojo.TemplatePojo;
import com.template.parameter.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/Template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;


    @GetMapping("/set")
    private Boolean  setTemplate(){
        return templateService.save(templateMapper.pojoToEntity( new TemplatePojo("", true, "invoice and estimate Template", "Buisnes", Constant.CODETEMPLATE)));
    }


    @GetMapping("/all")
    private List<TemplatePojo> allTemplate(){
        return templateService.allTemplate();
    }

    @PostMapping("/save")
    private Boolean  save(@RequestBody TemplatePojo template){
        return templateService.save(templateMapper.pojoToEntity(template));
    }

    @PostMapping("/save/List")
    public Boolean saveList(@RequestBody List<TemplatePojo> templates){
        return templateService.saveList(templates);
    }

    @GetMapping("/code/{codeTemplete}")
    private ResponseEntity<EntityRespone> findBycodeTemplate(@PathVariable("codeTemplete") String codeTemplete) {

        if(codeTemplete == null){
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", "CODE IS NULL ");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(templateService.findBycodeTemplate(codeTemplete));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/id/{id}")
    private boolean delete(@PathVariable("id") Long id) {
        return templateService.deleteTemplete(id);
    }

}
