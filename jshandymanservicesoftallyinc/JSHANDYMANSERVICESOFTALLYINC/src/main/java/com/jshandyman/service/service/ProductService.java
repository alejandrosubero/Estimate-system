
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.service ;

import java.util.Optional;import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import com.jshandyman.service.entitys.Product;
import com.jshandyman.service.pojo.ProductPojo;


public interface ProductService{
 
		public ProductPojo findByProductCode(String productCode);

		public ProductPojo  findByName(String name);

		public ProductPojo  findByPrice(Double price);

		public ProductPojo  findByStockNumber(Integer stockNumber);

		public ProductPojo  findByCategories(String categories);

		public ProductPojo  findByDescription(String description);

		public ProductPojo  findByNotas(String notas);

		public List<ProductPojo>  findByProductCodeContaining(String productCode);

		public List<ProductPojo>  findByNameContaining(String name);

		public List<ProductPojo> findByPriceContaining(Double price);

		public List<ProductPojo>  findByStockNumberContaining(Integer stockNumber);

		public List<ProductPojo>  findByCategoriesContaining(String categories);

		public List<ProductPojo>  findByDescriptionContaining(String description);

		public List<ProductPojo>  findByNotasContaining(String notas);

		public ProductPojo findById(Long id);
		public boolean saveProduct(Product product);
		public List<ProductPojo> getAllProduct();
		public boolean deleteProduct(Long id);

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


