package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.pojo.DataJshandyManServicesConfigPojo;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.security.EncryptAES;
import com.jshandyman.service.service.DataJshandyManServicesConfigService;
import com.jshandyman.service.validation.DataJshandyManServicesConfigValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("config/configuration")
public class DataJshandyManServicesConfigController {

    @Autowired
    private DataJshandyManServicesConfigService service;

    @Autowired
    private DataJshandyManServicesConfigValidation validation;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private EncryptAES encryptAES;

    @PostMapping("/save")
    private Boolean saveEstimate(@RequestBody DataJshandyManServicesConfigPojo pojo, @RequestHeader("Company")  String company) {
        pojo.setCompany(company);
        return service.saveDataJshandyManServicesConfig(validation.valida(pojo));
    }

    @PostMapping("/save/New")
    private DataJshandyManServicesConfigPojo save(@RequestBody DataJshandyManServicesConfigPojo pojo,  @RequestHeader("Company")  String company) {
       pojo.setCompany(company);
        return service.save(validation.valida(pojo));
    }

    @GetMapping("/delete/id/{id}")
    private boolean deleteEstimateFoever(@PathVariable("id") String id) {
        return service.delete(validation.valida_id(id));
    }

    @GetMapping("/delete/logical/{id}")
    private boolean deleteLogical(@PathVariable("id") String id) {
        return service.deleteLogic(validation.valida_id(id));
    }


    @GetMapping("/code/{code}")
    private ResponseEntity<EntityRespone> findByCode(@PathVariable("code") String codework) {

        try {
            String busca = (String) validation.validation(codework);
            // TODO: IMPLEMENTAR ESTA CONSULTA CON EL CAMPO ENCRIPTADO EN EL FRONT
//            EntityRespone entityRespone =  mapperEntityRespone.setEntityTobj(service.findByUserCode(encryptAES.decryptAES(busca)));

            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByUserCode(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

}
