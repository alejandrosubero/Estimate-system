package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.*;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.service.ClientService;
import com.jshandyman.service.service.EstimateService;
import com.jshandyman.service.validation.BillValidation;
import com.jshandyman.service.validation.ClientValidation;
import com.jshandyman.service.validation.EstimateValidation;
import com.jshandyman.service.validation.PaymentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/estimate")
public class EstimateController {

    @Autowired
    private EstimateMapper mapper;

    @Autowired
    private EstimateService service;

    @Autowired
    private EstimateValidation validation;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @Autowired
    private ClientValidation clientValidationService;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PaymentValidation paymentValidationService;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private BillValidation billValidationService;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    ClientService  clientService;


    @PostMapping("/save")
    private Boolean saveEstimate(@RequestBody EstimatePojo estimate) {
        Boolean vector = service.saveEstimate(mapper.pojoToEntity(validation.valida(estimate)));
        return vector;
    }

    // this metho return null if dont's save the estimate object
    @PostMapping("/new/save")
    private EstimatePojo saveNewEstimate(@RequestBody EstimatePojo estimate, @RequestHeader("Company")  String company) {
        estimate.setCompany(company);
         return service.saveNewEstimate(mapper.pojoToEntity(validation.valida(estimate)));
    }


    // this metho return null if dont's save the estimate object
    @PostMapping("/new/save/update")
    private EstimatePojo saveUpdateEstimate(@RequestBody EstimatePojo estimate, @RequestHeader("Company")  String company) {

        if(estimate.getCompany() == null || estimate.getCompany().equals("")){
            estimate.setCompany(company);
        }
        return service.saveUpdateEstimate(mapper.pojoToEntity(validation.valida(estimate)));
    }

    @PostMapping("/covertEstimate")
    private ResponseEntity<EntityRespone> covertEstimateToWork(@RequestBody EstimatePojo estimate, @RequestHeader("Company")  String company) {
        if(estimate.getCompany() == null || estimate.getCompany().equals("")){
            estimate.setCompany(company);
        }
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.covertEstimateToWork(estimate));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findById(validation.valida_id(id)));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/All")
    private ResponseEntity<EntityRespone> getAll(@RequestHeader("Company")  String company) {
        EntityRespone entityRespone =
                mapperEntityRespone.setEntityT(service.getAllEstimateCompany(company));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/All/list")
    private ResponseEntity<EntityRespone> getAllList(@RequestHeader("Company")  String company) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.getEstimateListTablet(company));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/delete/id/{id}")
    private boolean deleteEstimateFoever(@PathVariable("id") String id) {
        return service.deleteEstimate(validation.valida_id(id));
    }

    @GetMapping("/delete/logical/{id}")
    private boolean deleteLogical(@PathVariable("id") String id) {
        return service.deleteEstimateLogic(validation.valida_id(id));
    }

    @GetMapping("/pament/all/{idwork}")
    private ResponseEntity<EntityRespone> allPament(@PathVariable("idwork") Long idwork) {
        Long busca = (Long) validation.validation(idwork);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.getAllPamentToEstimate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/product/all/{codework}")
    private ResponseEntity<EntityRespone> AllProduct(@PathVariable("codework") String codework) {
        String busca = (String) validation.validation(codework);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.AllProductInEstimate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/codework/{codework}")
    private ResponseEntity<EntityRespone> findByCode(@PathVariable("codework") String codework) {
        String busca = (String) validation.validation(codework);
        try {
            EntityRespone entityRespone =  mapperEntityRespone.setEntityTobj(service.findByCodeWork(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/dedline/{dedline}")
    private ResponseEntity<EntityRespone> findByDedline(@PathVariable("dedline") Date dedline) {
        Date busca = (Date) validation.validation(dedline);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByDedline(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stardate/{stardate}")
    private ResponseEntity<EntityRespone> findByStarDate(@PathVariable("stardate") Date stardate) {
        Date busca = (Date) validation.validation(stardate);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByStarDate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/daystodeline/contain/{daystodeline}")
    private ResponseEntity<EntityRespone> findByDaysToDelineContain(@PathVariable("daystodeline") Long daystodeline) {
        Long busca = (Long) validation.validation(daystodeline);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByDaysToDelineContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/totalcostwork/withouttaxes/{totalcostworkwithouttaxes}")
    private ResponseEntity<EntityRespone> findByTotalCostWorkWithoutTaxes(@PathVariable("totalcostworkwithouttaxes") Double totalcostworkwithouttaxes) {
        Double busca = (Double) validation.validation(totalcostworkwithouttaxes);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByTotalCostEstimateWithoutTaxes(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/totalcostwork/{totalcostwork}")
    private ResponseEntity<EntityRespone> findByTotalCost(@PathVariable("totalcostwork") Double totalcostwork) {
        Double busca = (Double) validation.validation(totalcostwork);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByTotalCostEstimate(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/remainingpayable/{remainingpayable}")
    private ResponseEntity<EntityRespone> findByRemainingPayable(@PathVariable("remainingpayable") Double remainingpayable) {
        Double busca = (Double) validation.validation(remainingpayable);
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByRemainingPayable(busca));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/codework/contain/{codework}")
    private ResponseEntity<EntityRespone> findByCodeWorkContain(@PathVariable("codework") String codework) {
        String busca = (String) validation.validation(codework);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByCodeEstimateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/description/contain/{description}")
    private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String description) {
        String busca = (String) validation.validation(description);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByDescriptionContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/dedline/contain/{dedline}")
    private ResponseEntity<EntityRespone> findByDedlineContain(@PathVariable("dedline") Date dedline) {
        Date busca = (Date) validation.validation(dedline);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByDedlineContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/stardate/contain/{stardate}")
    private ResponseEntity<EntityRespone> findByStarDateContain(@PathVariable("stardate") Date stardate) {
        Date busca = (Date) validation.validation(stardate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByStarDateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }


    @GetMapping("/dayslate/contain/{dayslate}")
    private ResponseEntity<EntityRespone> findByDaysLateContain(@PathVariable("dayslate") Long dayslate) {
        Long busca = (Long) validation.validation(dayslate);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByDaysLateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/totalcostwork/withouttaxes/contain/{totalcostworkwithouttaxes}")
    private ResponseEntity<EntityRespone> findByTotalCostWorkWithoutTaxesContain(@PathVariable("totalcostworkwithouttaxes") Double totalcostworkwithouttaxes) {
        Double busca = (Double) validation.validation(totalcostworkwithouttaxes);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByTotalCostEstimateWithoutTaxesContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/totalcostwork/contain/{totalcostwork}")
    private ResponseEntity<EntityRespone> findByTotalCostWorkContain(@PathVariable("totalcostwork") Double totalcostwork) {
        Double busca = (Double) validation.validation(totalcostwork);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByTotalCostEstimateContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }

    @GetMapping("/remainingpayable/contain/{remainingpayable}")
    private ResponseEntity<EntityRespone> findByRemainingPayableContain(@PathVariable("remainingpayable") Double remainingpayable) {
        Double busca = (Double) validation.validation(remainingpayable);
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByRemainingPayableContaining(busca));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }




    @PostMapping("/payments/contain/")
    private List<EstimatePojo> findByPayment(@RequestBody PaymentPojo payment) {
        return service.findByPaymentContaining(paymentMapper.pojoToEntity(paymentValidationService.valida(payment)));
    }


    @PostMapping("/bills/contain/")
    private List<EstimatePojo> findByBill(@RequestBody BillPojo bill) {
        return service.findByBillContaining(billMapper.pojoToEntity(billValidationService.valida(bill)));
    }


    @PostMapping("/find/client")
    private ResponseEntity<EntityRespone> findRelacionClient(@RequestBody ClientPojo client) {
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(service.findByRelacionClient(clientMapper.pojoToEntity(clientValidationService.valida(client))));
        return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
    }
}


//    @GetMapping("/description/{description}")
//    private ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String description) {
//        String busca = (String) validation.validation(description);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByDescription(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/dayslate/{dayslate}")
//    private ResponseEntity<EntityRespone> findByDaysLate(@PathVariable("dayslate") Long dayslate) {
//        Long busca = (Long) validation.validation(dayslate);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByDaysLate(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/daystodeline/{daystodeline}")
//    private ResponseEntity<EntityRespone> findByDaysToDeline(@PathVariable("daystodeline") Long daystodeline) {
//        Long busca = (Long) validation.validation(daystodeline);
//        try {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(service.findByDaysToDeline(busca));
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//    }