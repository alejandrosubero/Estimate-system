
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.repository;

import java.util.List;
import java.util.Date;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.jshandyman.service.entitys.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    public Optional<Product> findByProductCode(String productCode);

    public List<Product> findByProductCodeContaining(String productCode);

    public Optional<Product> findByName(String name);

    public List<Product> findByNameContaining(String name);

    public Optional<Product> findByPrice(Double price);

    public List<Product> findByPriceContaining(Double price);

    public Optional<Product> findByStockNumber(Integer stockNumber);

    public List<Product> findByStockNumberContaining(Integer stockNumber);

    public Optional<Product> findByCategories(String categories);

    public List<Product> findByCategoriesContaining(String categories);

    public Optional<Product> findByDescription(String description);

    public List<Product> findByDescriptionContaining(String description);

    public Optional<Product> findByNotas(String notas);

    public List<Product> findByNotasContaining(String notas);

    @Query(value = "SELECT p FROM Product p WHERE CONCAT( p.price, ' ', p.name, ' ', p.productCode, ' ', p.stockNumber) LIKE %?1%")
    public List<Product> finBySearch(String keyword);
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


