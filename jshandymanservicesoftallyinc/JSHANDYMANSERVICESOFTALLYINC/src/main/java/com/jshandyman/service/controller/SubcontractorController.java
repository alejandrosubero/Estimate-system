package com.jshandyman.service.controller;

import com.jshandyman.service.entitys.Subcontractor;
import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.mapper.SubcontractorMapper;
import com.jshandyman.service.pojo.BillPojo;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.SubcontractorPojo;
import com.jshandyman.service.service.SubcontractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/Subcontractor")
public class SubcontractorController {

    @Autowired
    private SubcontractorService subcontractorService;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private SubcontractorMapper subcontractorMapper;

    @GetMapping("/All")
    private ResponseEntity<EntityRespone> allSubContractor(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(subcontractorService.allSubContractor());
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }


    @PostMapping("/save")
    private Boolean  saveBill(@RequestBody SubcontractorPojo subcontractor){
        return subcontractorService.saveSubContractor(subcontractorMapper.pojoToEntity(subcontractor)); }


    @DeleteMapping("/delete/id/{id}")
    private boolean deleteBill(@PathVariable("id") Long id) {
        subcontractorService.deleteSubcontractor(id);
        return true;
    }

    @GetMapping("/company/{company}")
    private SubcontractorPojo deleteBill(@PathVariable("company") String company) {
        return  subcontractorService.findByCompany(company);
    }

    @GetMapping("/mail/{mail}")
    private SubcontractorPojo mail(@PathVariable("mail") String mail) {
        return  subcontractorService.findByMail(mail);
    }

    @GetMapping("/codeClient/{code}")
    private SubcontractorPojo code(@PathVariable("code") String code) {
        return  subcontractorService.findByCodeClient(code);
    }

}
