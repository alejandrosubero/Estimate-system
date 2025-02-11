
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.entitys.Product;
import com.jshandyman.service.mapper.ProductMapper;
import com.jshandyman.service.pojo.ProductPojo;
import com.jshandyman.service.repository.ProductRepository;
import com.jshandyman.service.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImplement implements ProductService {

    protected static final Log logger = LogFactory.getLog(ProductServiceImplement.class);

    @Autowired
    private ProductRepository productrepository;

    @Autowired
    private ProductMapper productMapper;

    // TODO: AGREGAR CALCULO DE LOS PRECIOS CON EL TAXE AL INGRESAR UN NUEVO PRODUCTO O HACERLO DESDE EL FRONT

    @Override
    public ProductPojo findByProductCode(String productCode) {

        logger.info("Starting getProduct");
        Product productEntity = new Product();
        Optional<Product> fileOptional1 = productrepository.findByProductCode(productCode);
        if (fileOptional1.isPresent()) {
            try {
                productEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return productMapper.entityToPojo(productEntity);
    }

    @Override
    public ProductPojo findByName(String name) {

        logger.info("Starting getProduct");
        Product productEntity = new Product();
        Optional<Product> fileOptional1 = productrepository.findByName(name);

        if (fileOptional1.isPresent()) {
            try {
                productEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return productMapper.entityToPojo(productEntity);
    }

    @Override
    public ProductPojo findByPrice(Double price) {
        logger.info("Starting getProduct");
        Product productEntity = new Product();
        Optional<Product> fileOptional1 = productrepository.findByPrice(price);

        if (fileOptional1.isPresent()) {
            try {
                productEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return productMapper.entityToPojo(productEntity);
    }

    @Override
    public ProductPojo findByStockNumber(Integer stockNumber) {

        logger.info("Starting getProduct");
        Product productEntity = new Product();
        Optional<Product> fileOptional1 = productrepository.findByStockNumber(stockNumber);

        if (fileOptional1.isPresent()) {
            try {
                productEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return productMapper.entityToPojo(productEntity);
    }

    @Override
    public ProductPojo findByCategories(String categories) {

        logger.info("Starting getProduct");
        Product productEntity = new Product();
        Optional<Product> fileOptional1 = productrepository.findByCategories(categories);
        if (fileOptional1.isPresent()) {
            try {
                productEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return productMapper.entityToPojo(productEntity);
    }

    @Override
    public ProductPojo findByDescription(String description) {

        logger.info("Starting getProduct");
        Product productEntity = new Product();
        Optional<Product> fileOptional1 = productrepository.findByDescription(description);
        if (fileOptional1.isPresent()) {
            try {
                productEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);

            }
        }
        return productMapper.entityToPojo(productEntity);
    }

    @Override
    public ProductPojo findByNotas(String notas) {
        logger.info("Starting getProduct");
        Product productEntity = new Product();
        Optional<Product> fileOptional1 = productrepository.findByNotas(notas);
        if (fileOptional1.isPresent()) {
            try {
                productEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return productMapper.entityToPojo(productEntity);
    }


    @Override
    public List<ProductPojo> getAllProduct() {
        logger.info("Get allProyect");
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
        productrepository.findAll().forEach(product ->  listaProduct.add(productMapper.entityToPojo(product)));

//        for ( Product product:productrepository.findAll()) {
//            if (!product.getService()) {
//                listaProduct.add(productMapper.entityToPojo(product));
//            }
//        }
        return listaProduct;
    }


    @Override
    public boolean saveProduct(Product product) {
        logger.info("Save Proyect");
        try {
            productrepository.save(product);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean deleteProduct(Long id) {
        logger.info("Delete Proyect");
        boolean clave = false;
        try {
            productrepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public ProductPojo findById(Long id) {
        return productMapper.entityToPojo(productrepository.findById(id).get());
    }


    @Override
    public List<ProductPojo> findByProductCodeContaining(String productcode) {
        logger.info("Get allProyect");
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
       productrepository.findByProductCodeContaining(productcode)
               .stream().forEach(product -> listaProduct.add(productMapper.entityToPojo(product)));
        return listaProduct;
    }

    @Override
    public List<ProductPojo> findByNameContaining(String name) {
        logger.info("Get allProyect");
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
        productrepository.findByNameContaining(name)
                .stream().forEach(product -> listaProduct.add(productMapper.entityToPojo(product)));
        return listaProduct;
    }

    @Override
    public List<ProductPojo> findByPriceContaining(Double price) {
        logger.info("Get allProyect=======================================");
        System.out.println(price);
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
        List<Product> lista = productrepository.finBySearch(String.valueOf(price));
        lista.stream().forEach(product -> listaProduct.add(productMapper.entityToPojo(product)));
        return listaProduct;
    }

    @Override
    public List<ProductPojo> findByStockNumberContaining(Integer stocknumber) {
        logger.info("Get allProyect");
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
        List<Product> lista = productrepository.finBySearch(String.valueOf(stocknumber));
        lista.stream().forEach(product -> listaProduct.add(productMapper.entityToPojo(product)));
        return listaProduct;
    }

    @Override
    public List<ProductPojo> findByCategoriesContaining(String categories) {
        logger.info("Get allProyect");
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
       productrepository.findByCategoriesContaining(categories)
               .stream().forEach(product -> listaProduct.add(productMapper.entityToPojo(product)));
        return listaProduct;
    }

    @Override
    public List<ProductPojo> findByDescriptionContaining(String description) {
        logger.info("Get allProyect");
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
       productrepository.findByDescriptionContaining(description)
               .stream().forEach(product -> listaProduct.add(productMapper.entityToPojo(product)));
        return listaProduct;
    }


    @Override
    public List<ProductPojo> findByNotasContaining(String notas) {
        logger.info("Get allProyect");
        List<ProductPojo> listaProduct = new ArrayList<ProductPojo>();
         productrepository.findByNotasContaining(notas)
                 .stream().forEach(product -> listaProduct.add(productMapper.entityToPojo(product)));
        return listaProduct;
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


}
