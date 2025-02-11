package com.service.crom.controller;


import com.service.crom.mapper.ParametersMapper;
import com.service.crom.pojo.ParametersPojo;
import com.service.crom.services.ParametersServicesImplement;
import com.service.crom.validation.ParametersValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/x20586/para")
public class ParametersController {


    @Autowired
    private ParametersMapper mapper;

    @Autowired
    private ParametersValidation validation;

    @Autowired
    private ParametersServicesImplement service;


    @PostMapping("/save")
    private Boolean save(@RequestBody ParametersPojo parameters) {
        return service.save(mapper.pojoToEntity(validation.validationP(parameters)));
    }

    @PostMapping("/save/automatic")
    private Boolean saveAutomatic(@RequestBody ParametersPojo parameters) {
        return service.saveOfFrom(mapper.pojoToEntity(validation.validationP(parameters)));
    }

    @GetMapping("/delete/id/{id}")
    private boolean deleteEstimateFoever(@PathVariable("id") String id) {
        return service.delete(validation.valida_id(id));
    }

    @GetMapping("/clave/{clave}")
    private ParametersPojo findByClave(@PathVariable("clave") String clave) {
        return service.findByClave(clave);
    }

    @GetMapping("/all")
    public List<ParametersPojo> allPrameter(){
        return service.allPrameter();
    }

}
