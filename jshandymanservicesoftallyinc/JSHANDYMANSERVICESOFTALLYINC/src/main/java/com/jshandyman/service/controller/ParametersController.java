package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.ParametersMapper;
import com.jshandyman.service.pojo.ParametersPojo;
import com.jshandyman.service.serviceImplement.ParametersServicesImplement;
import com.jshandyman.service.validation.ParametersValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("config/parameters")
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

}
