package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.TemplateMapper;
import com.jshandyman.service.pojo.TemplatePojo;
import com.jshandyman.service.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/template/new")
public class TemplateSaveController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateMapper templateMapper;

    @PostMapping("/save")
    private Boolean  saves(@RequestBody TemplatePojo template){
        return templateService.save2(templateMapper.pojoToEntity(template));
    }
}
