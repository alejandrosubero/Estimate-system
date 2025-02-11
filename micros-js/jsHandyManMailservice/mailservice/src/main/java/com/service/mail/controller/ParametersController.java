package com.service.mail.controller;


import com.service.mail.mapper.ParametersMapper;
import com.service.mail.pojo.ParametersPojo;
import com.service.mail.serviceImplement.ParametersServicesImplement;
import com.service.mail.validation.ParametersValidation;
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
