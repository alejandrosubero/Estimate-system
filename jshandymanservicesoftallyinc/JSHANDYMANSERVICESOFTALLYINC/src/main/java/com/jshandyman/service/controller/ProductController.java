
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/



package com.jshandyman.service.controller;

import com.jshandyman.service.mapper.MapperEntityRespone;
import com.jshandyman.service.mapper.ProductMapper;
import com.jshandyman.service.pojo.EntityRespone;
import com.jshandyman.service.pojo.ProductPojo;
import com.jshandyman.service.service.ProductService;
import com.jshandyman.service.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductValidation productValidationService;

    @Autowired
   ProductMapper productMapper;

    @Autowired
   MapperEntityRespone mapperEntityRespone;



        @GetMapping("/productcode/{productcode}")
        private  ResponseEntity<EntityRespone> findByProductCode(@PathVariable("productcode") String  productcode) {
        String busca = (String) productValidationService.validation(productcode);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findByProductCode(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/name/{name}")
        private  ResponseEntity<EntityRespone> findByName(@PathVariable("name") String  name) {
        String busca = (String) productValidationService.validation(name);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findByName(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

//        @GetMapping("/price/{price}")
//        private  ResponseEntity<EntityRespone> findByPrice(@PathVariable("price") Double  price) {
//        Double busca = (Double) productValidationService.validation(price);
//        try {
//                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findByPrice(busca));
//                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
//             } catch (DataAccessException e) {
//                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
//             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
//        }
//     }

        @GetMapping("/stocknumber/{stocknumber}")
        private  ResponseEntity<EntityRespone> findByStockNumber(@PathVariable("stocknumber") Integer  stocknumber) {
        Integer busca = (Integer) productValidationService.validation(stocknumber);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findByStockNumber(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/categories/{categories}")
        private  ResponseEntity<EntityRespone> findByCategories(@PathVariable("categories") String  categories) {
        String busca = (String) productValidationService.validation(categories);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findByCategories(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/description/{description}")
        private  ResponseEntity<EntityRespone> findByDescription(@PathVariable("description") String  description) {
        String busca = (String) productValidationService.validation(description);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findByDescription(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }

        @GetMapping("/notas/{notas}")
        private  ResponseEntity<EntityRespone> findByNotas(@PathVariable("notas") String  notas) {
        String busca = (String) productValidationService.validation(notas);
        try {
                EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findByNotas(busca));
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
             } catch (DataAccessException e) {
                 EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
     }


        @GetMapping("/contain/{productcode}")
        private ResponseEntity<EntityRespone> findByProductCodeContain(@PathVariable("productcode") String  productcode) {
              String busca = (String) productValidationService.validation(productcode);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.findByProductCodeContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/name/contain/{name}")
        private ResponseEntity<EntityRespone> findByNameContain(@PathVariable("name") String  name) {
              String busca = (String) productValidationService.validation(name);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.findByNameContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/price/contain/{price}")
        private ResponseEntity<EntityRespone> findByPriceContain(@PathVariable("price") Double  price) {
             // Double busca = (Double) productValidationService.validation(price);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.findByPriceContaining(price));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/stocknumber/contain/{stocknumber}")
        private ResponseEntity<EntityRespone> findByStockNumberContain(@PathVariable("stocknumber") Integer  stocknumber) {
              Integer busca = (Integer) productValidationService.validation(stocknumber);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.findByStockNumberContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/categories/contain/{categories}")
        private ResponseEntity<EntityRespone> findByCategoriesContain(@PathVariable("categories") String  categories) {
              String busca = (String) productValidationService.validation(categories);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.findByCategoriesContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/description/contain/{description}")
        private ResponseEntity<EntityRespone> findByDescriptionContain(@PathVariable("description") String  description) {
              String busca = (String) productValidationService.validation(description);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.findByDescriptionContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }

        @GetMapping("/notas/contain/{notas}")
        private ResponseEntity<EntityRespone> findByNotasContain(@PathVariable("notas") String  notas) {
              String busca = (String) productValidationService.validation(notas);
              EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.findByNotasContaining(busca));
              return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        }


        @GetMapping("/id/{id}")
          private ResponseEntity<EntityRespone> findById(@PathVariable("id") String id) {
          EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(productService.findById(productValidationService.valida_id(id))); 
             return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
          }


        @GetMapping("/All")
        private  ResponseEntity<EntityRespone> getAllProduct(){
        EntityRespone entityRespone = mapperEntityRespone.setEntityT(productService.getAllProduct());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }



        @PostMapping("/save")
        private Boolean  saveProduct(@RequestBody ProductPojo  product){ 
            return productService.saveProduct(productMapper.pojoToEntity(productValidationService.valida(product)) ); }





        @DeleteMapping("/delete/id/{id}")
            private boolean deleteProduct(@PathVariable("id") String id) {
            return productService.deleteProduct(productValidationService.valida_id(id)); }

}
 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


