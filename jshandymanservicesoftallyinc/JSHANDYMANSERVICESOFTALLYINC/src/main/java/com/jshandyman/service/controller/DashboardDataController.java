package com.jshandyman.service.controller;


import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.service.DashboardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/Dashboard")
public class DashboardDataController {

    @Autowired
    private DashboardDataService dashboardDataService;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;


    @GetMapping("/data")
    private ResponseEntity<EntityRespone> findDashboard(@RequestHeader("Company")  String company) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(dashboardDataService.getStatistics(company));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/data/send/mail")
    private ResponseEntity<EntityRespone> mapTrakerMailDashboard(@RequestHeader("Company")  String company) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(dashboardDataService.trakerSendMails(company));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

//   api/Dashboard/data/send/mail


}
