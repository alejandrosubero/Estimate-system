package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.mapper.TemplateMapper;
import com.jshandyman.service.mapper.UserMapper;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.TemplatePojo;
import com.jshandyman.service.pojo.UserPojo;
import com.jshandyman.service.service.ClientService;
import com.jshandyman.service.service.TemplateService;
import com.jshandyman.service.service.UserService;
import com.jshandyman.service.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/new/user")
public class UserPublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidation userValidationService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private ClientService clientService;



    @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST, consumes="application/json")
    private EntityRespone saveNewUser(@RequestBody UserPojo user, @RequestHeader("keyAdmin")  String keyAdmin){
        return userService.newUser(userMapper.pojoToEntity(userValidationService.validaNewUser(user)), keyAdmin);
    }


    @GetMapping("/start")
    private ResponseEntity<EntityRespone> startClient(@RequestHeader("Company")  String company) {
        clientService.fillClienteMap(company);
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(null);
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/usernameNew/{username}")
    private ResponseEntity<EntityRespone> findByUserName(@PathVariable("username") String  username) {

        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(
                    userService.findByUserName((String) userValidationService.validation(username)));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);

        } catch (DataAccessException e) {
            EntityRespone entityRespone =
                    mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/name/contain/{username}")
    private ResponseEntity<EntityRespone> findByUserNameContain(@PathVariable("username") String  username) {

        EntityRespone entityRespone = mapperEntityRespone.setEntityT(
                userService.findByUserNameContaining(
                        (String) userValidationService.validation(username)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


}

