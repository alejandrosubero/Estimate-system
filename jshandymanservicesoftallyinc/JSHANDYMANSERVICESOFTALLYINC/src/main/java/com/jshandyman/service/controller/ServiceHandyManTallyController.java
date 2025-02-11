package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.ServiceHandyManTallyPojo;
import com.jshandyman.service.service.ServiceHandyManTallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/service")
public class ServiceHandyManTallyController {

    @Autowired
    private ServiceHandyManTallyService service;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;


    @PostMapping("/save")
    private Boolean save(@RequestBody ServiceHandyManTallyPojo pojo){
        return service.save(pojo);
    }


    @GetMapping("/idWork/{idWork}")
    private ResponseEntity<EntityRespone> findByIdWork(@PathVariable("idWork") Long idWork) {

        if(idWork == null){
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", "CODE IS NULL ");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByIdWork(idWork));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/idServices/{idServices}")
    private ResponseEntity<EntityRespone> findByIdServices(@PathVariable("idServices") Long idServices) {

        if(idServices == null){
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", "CODE IS NULL ");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByIdServices(idServices));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/description/{description}")
    private ResponseEntity<EntityRespone> findByIdServices(@PathVariable("description") String description) {

        if(description == null){
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", "CODE IS NULL ");
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByDescriptionOfServicesCostContaining(description));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }
}
