package com.jshandyman.service.controller;

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.ProductPojo;
import com.jshandyman.service.pojo.SearchDatePojo;
import com.jshandyman.service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/search")
public class SearchServiceController {

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private SearchService service;


    @GetMapping("/keyword/{keyword}")
    private ResponseEntity<EntityRespone> search(@PathVariable("keyword") String keyword) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.finBySearch(keyword));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/keyword/between")
    private ResponseEntity<EntityRespone> search(@RequestBody SearchDatePojo searchDatePojo) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.finByCreateDayBetween(searchDatePojo.getStarDate(), searchDatePojo.getEndDate(), searchDatePojo.getClave()));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e){
            e.printStackTrace();
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/keyword/month/year")
    private ResponseEntity<EntityRespone> searchMonthYear(@RequestBody SearchDatePojo searchDatePojo) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByCreateDayMonthYear(searchDatePojo.getMonth(),searchDatePojo.getYear(), searchDatePojo.getClave()));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }




//new Date('2014-25-23').toISOString();
//{
//    "starDate":"2022-02-01T11:32:16.025Z",
//    "endDate":"2022-04-14T11:32:16.025Z"
//}

}

