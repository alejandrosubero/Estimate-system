
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
 
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/

package com.jshandyman.service.pojo;

import java.util.Objects;


public class ProductPojo  {


    private Long idProduct;
    private String productCode;
    private String name;
    private Double price;
    private Double totalPriceWithTaxes;
    private Integer stockNumber;
    private String categories;
    private String description;
    private String notas;
    private Long idBill;
    private Double priceWithTaxes;
    private Double totalPriceWithOupTaxes;
    private Boolean itemDeliteEdit;


    public ProductPojo() {
    }



    public void setItemDeliteEdit(boolean itemDeliteEdit) {
        this.itemDeliteEdit = itemDeliteEdit;
    }

    public ProductPojo(String productCode, String name, Double price, Double totalPriceWithTaxes,
                       Integer stockNumber, String categories, String description, String notas, Long idBill,
                       Double priceWithTaxes, Double totalPriceWithOupTaxes, Boolean itemDeliteEdit) {

        if (productCode != null)
            this.productCode = productCode;

        if (name != null)
            this.name = name;

        if (price != null)
            this.price = price;

        if (totalPriceWithTaxes != null)
            this.totalPriceWithTaxes = totalPriceWithTaxes;

        if (stockNumber != null)
            this.stockNumber = stockNumber;

        if (categories != null)
            this.categories = categories;

        if (description != null)
            this.description = description;

        if (notas != null)
            this.notas = notas;

        if (idBill != null)
            this.idBill = idBill;

        if (priceWithTaxes != null)
            this.priceWithTaxes = priceWithTaxes;

        if (totalPriceWithOupTaxes != null)
            this.totalPriceWithOupTaxes = totalPriceWithOupTaxes;

        if (itemDeliteEdit != null){
            this.itemDeliteEdit = itemDeliteEdit;
            }

    }


    public ProductPojo(ProductPojo oterProductPojo) {

        if(oterProductPojo != null){

            if (oterProductPojo.getProductCode() != null)
                this.productCode = oterProductPojo.getProductCode();

            if (oterProductPojo.getName() != null)
                this.name = oterProductPojo.getName();

            if (oterProductPojo.getPrice() != null)
                this.price = oterProductPojo.getPrice();

            if (oterProductPojo.getTotalPriceWithTaxes() != null)
                this.totalPriceWithTaxes = oterProductPojo.getTotalPriceWithTaxes();

            if (oterProductPojo.getStockNumber() != null)
                this.stockNumber = oterProductPojo.getStockNumber();

            if (oterProductPojo.getCategories() != null)
                this.categories = oterProductPojo.getCategories();

            if (oterProductPojo.getDescription() != null)
                this.description = oterProductPojo.getDescription();

            if (oterProductPojo.getNotas() != null)
                this.notas = notas;

//            if (idBill != null)
//                this.idBill = idBill;

            if (oterProductPojo.getPriceWithTaxes() != null)
                this.priceWithTaxes = priceWithTaxes;

            if (oterProductPojo.getTotalPriceWithOupTaxes() != null)
                this.totalPriceWithOupTaxes = totalPriceWithOupTaxes;

            if (oterProductPojo.getItemDeliteEdit() != null){
                this.itemDeliteEdit = oterProductPojo.getItemDeliteEdit();
            }
        }
    }



    public Boolean getItemDeliteEdit() {
        return itemDeliteEdit;
    }

    public void setItemDeliteEdit(Boolean itemDeliteEdit) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPriceWithTaxes() {
        return totalPriceWithTaxes;
    }

    public void setTotalPriceWithTaxes(Double totalPriceWithTaxes) {
        this.totalPriceWithTaxes = totalPriceWithTaxes;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductPojo)) return false;
        ProductPojo that = (ProductPojo) o;
        return itemDeliteEdit == that.itemDeliteEdit && Objects.equals(idProduct, that.idProduct) && Objects.equals(productCode, that.productCode) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(totalPriceWithTaxes, that.totalPriceWithTaxes) && Objects.equals(stockNumber, that.stockNumber) && Objects.equals(categories, that.categories) && Objects.equals(description, that.description) && Objects.equals(notas, that.notas) && Objects.equals(idBill, that.idBill) && Objects.equals(priceWithTaxes, that.priceWithTaxes) && Objects.equals(totalPriceWithOupTaxes, that.totalPriceWithOupTaxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, productCode, name, price, totalPriceWithTaxes, stockNumber, categories, description, notas, idBill, priceWithTaxes, totalPriceWithOupTaxes, itemDeliteEdit);
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

