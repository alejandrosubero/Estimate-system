package com.jshandyman.service.controller;


import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.pojo.EmailHandyManTallyPojo;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.PhoneClientPojo;
import com.jshandyman.service.service.MailServices;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/email")
public class EmailController {

    @Autowired
    private MailServices mailServices;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;


    @PostMapping("/send/Work")
    private ResponseEntity<EntityRespone>  sendMailOfWork(@RequestBody EmailHandyManTallyPojo handyManTallyPojo, @RequestHeader("Company")  String company){
        EntityRespone entityRespone= null;
        handyManTallyPojo.setCompany(company);
        entityRespone = mapperEntityRespone.setEntityTobj(mailServices.sendMailOfWork(handyManTallyPojo));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/send/Work")
    private ResponseEntity<EntityRespone> sendMailOfWorkclean(){
        EntityRespone entityRespone= null;
        entityRespone = mapperEntityRespone.setEntityTobj(mailServices.cleanDirectory());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/codeWork/{code}")
    private ResponseEntity<EntityRespone> findByHost(@PathVariable("code") String code) {
        try {
//                mailServices.sendEmailWork(code);

            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(true);
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);

        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

}
