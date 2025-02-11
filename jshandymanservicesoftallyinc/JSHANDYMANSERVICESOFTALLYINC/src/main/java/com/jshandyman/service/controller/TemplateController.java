package com.jshandyman.service.controller;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.mapper.TemplateMapper;
import com.jshandyman.service.pojo.ClientPojo;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.TemplatePojo;
import com.jshandyman.service.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("config/Template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    MapperEntityRespone mapperEntityRespone;


    @GetMapping("/set")
    private Boolean  setTemplate(){
        return templateService.save(templateMapper.pojoToEntity( new TemplatePojo("", true, "invoice and estimate Template", "Buisnes",Constant.CODE_TEMPLATE)));
    }


    @PostMapping("/save")
    private Boolean  save(@RequestBody TemplatePojo template){
        return templateService.save(templateMapper.pojoToEntity(template));
    }


    @PostMapping("/saves")
    private Boolean  saves(@RequestBody TemplatePojo template){
        return templateService.save2(templateMapper.pojoToEntity(template));
    }


    @GetMapping("/code/{codeTemplete}")
    private ResponseEntity<EntityRespone> findBycodeTemplate(@PathVariable("codeTemplete") String codeTemplete, @RequestHeader("Company")  String company) {

        if(codeTemplete == null){
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", "CODE IS NULL ");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(templateService.findBycodeTemplate(codeTemplete, company));
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
