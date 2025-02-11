
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/

package com.jshandyman.service.entitys;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "product")
@Audited
public class Product extends Auditable  {

    
    @Id
    @GeneratedValue(generator = "sequence_mat_id_generator")
    @SequenceGenerator(name = "sequence_mat_id_generator", initialValue = 25, allocationSize = 1000)
    @Column(name = "idProduct", updatable = true, nullable = false, length = 25)
    private Long idProduct;

    @Column(name = "productCode", updatable = true, nullable = true, length = 200)
    private String productCode;

    @Column(name = "name", updatable = true, nullable = true, length = 200)
    private String name;

    @Column(name = "stockNumber", updatable = true, nullable = true, length = 200)
    private Integer stockNumber;

    @Column(name = "categories", updatable = true, nullable = true, length = 200)
    private String categories;

    @Column(name = "price", updatable = true, nullable = true, length = 200)
    private Double price;

    @Column(name = "priceWithTaxes", updatable = true, nullable = true, length = 200)
    private Double priceWithTaxes;

    @Column(name = "totalPriceWithOupTaxes", updatable = true, nullable = true, length = 200)
    private Double totalPriceWithOupTaxes;

    @Column(name = "totalPriceWithTaxes", updatable = true, nullable = true, length = 200)
    private Double totalPriceWithTaxes;

    @Column(name = "description", updatable = true, nullable = true, length = 200)
    private String description;

    @Column(name = "notas", updatable = true, nullable = true, length = 200)
    private String notas;

    @Column(name = "bill_id")
    private Long idBill;

    @Column(name = "itemDeliteEdit", updatable = true, nullable = true, length = 10)
    private boolean itemDeliteEdit;

    public Product() {
    }

    public boolean isItemDeliteEdit() {
        return itemDeliteEdit;
    }

    public void setItemDeliteEdit(boolean itemDeliteEdit) {
        this.itemDeliteEdit = itemDeliteEdit;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceWithTaxes() {
        return priceWithTaxes;
    }

    public void setPriceWithTaxes(Double priceWithTaxes) {
        this.priceWithTaxes = priceWithTaxes;
    }

    public Double getTotalPriceWithOupTaxes() {
        return totalPriceWithOupTaxes;
    }

    public void setTotalPriceWithOupTaxes(Double totalPriceWithOupTaxes) {
        this.totalPriceWithOupTaxes = totalPriceWithOupTaxes;
    }

    public Double getTotalPriceWithTaxes() {
        return totalPriceWithTaxes;
    }

    public void setTotalPriceWithTaxes(Double totalPriceWithTaxes) {
        this.totalPriceWithTaxes = totalPriceWithTaxes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return itemDeliteEdit == product.itemDeliteEdit && Objects.equals(idProduct, product.idProduct) && Objects.equals(productCode, product.productCode) && Objects.equals(name, product.name) && Objects.equals(stockNumber, product.stockNumber) && Objects.equals(categories, product.categories) && Objects.equals(price, product.price) && Objects.equals(priceWithTaxes, product.priceWithTaxes) && Objects.equals(totalPriceWithOupTaxes, product.totalPriceWithOupTaxes) && Objects.equals(totalPriceWithTaxes, product.totalPriceWithTaxes) && Objects.equals(description, product.description) && Objects.equals(notas, product.notas) && Objects.equals(idBill, product.idBill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, productCode, name, stockNumber, categories, price, priceWithTaxes, totalPriceWithOupTaxes, totalPriceWithTaxes, description, notas, idBill, itemDeliteEdit);
    }
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

