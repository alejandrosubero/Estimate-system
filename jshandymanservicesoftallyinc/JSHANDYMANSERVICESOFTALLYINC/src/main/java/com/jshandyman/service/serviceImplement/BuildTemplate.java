package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.Work;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.service.TaxesAndPriceService;
//import gui.ava.html.image.generator.HtmlImageGenerator;
import net.bytebuddy.asm.Advice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BuildTemplate {

    protected static final Log logger = LogFactory.getLog(BuildTemplate.class);

    @Autowired
    private TaxesAndPriceService taxesAndPriceService;

    @Autowired
    private DataJshandyManServicesConfigServiceImplement dataJshandyMan;


    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    private DecimalFormat decim = new DecimalFormat("0.00");

    private  DecimalFormat decim(){
        DecimalFormatSymbols separadorPer = new DecimalFormatSymbols();
        separadorPer.setDecimalSeparator('.');
        DecimalFormat format1 = new DecimalFormat("#.##",separadorPer);
        return format1;
    }


    private Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public String writeTemplate(EmailHandyManTallyPojo pojo) {
        String template = "";
        if (pojo.getWorkPojo() != null) {
            template = builingInvoice(pojo);
            return template;
        }else{
            template = builingEstimate(pojo);
            return template;
        }
    }

    public String builingEstimate(EmailHandyManTallyPojo pojo){

        StringBuilder tabletRows = new StringBuilder();

        StringBuilder mails = new StringBuilder();
        StringBuilder phones = new StringBuilder();

        List<Double> partsSubtotal = new ArrayList<Double>();
        List<Double> partsSubtotalProduct = new ArrayList<Double>();

        StringBuilder billTabletRows = new StringBuilder();
        StringBuilder productosTabletRows = new StringBuilder();
        List<Double> partsSubtotalOverheadCalculate = new ArrayList<Double>();

        Integer countGetBillproduct = 0;
        Integer countGetBill = 0;

        Double subtotalSubcontractors = 0.0;
        Double subtotalServices = 0.0;
        Double overheadCalculateValue = 0.0;
        Double productAndMaterials = 0.0;

        DataJshandyManServicesConfigPojo dataJshandyManServices = dataJshandyMan.getActive(pojo.getCompany());

        String templ = pojo.getTemplate();
        templ = templ.replace("max-width:600px", "max-width:900px");

//        if(pojo.getEmail() != null && pojo.getEmail().getContent() != null && pojo.getEmail().getContent() != ""){
//            templ = templ.replace("@Comments@", this.addComment(pojo.getEmail().getContent()));
//        } else{
//            templ = templ.replace("@Comments@", " ");
//        }

        templ = templ.replace("@type@", "Estimate");
        templ = templ.replace("@Direction@", dataJshandyManServices.getDirection());
        templ = templ.replace("@Mail@", dataJshandyManServices.getMail());
        templ = templ.replace("@Web@", dataJshandyManServices.getWeb());
        templ = templ.replace("@PhoneNumber@",dataJshandyManServices.getPhoneNumber());
        templ = templ.replace("@TaxRegNumber@",dataJshandyManServices.getTaxRegNumber());

        templ = templ.replace("@clientName@", pojo.getEstimatePojo().getClient().getName());
        pojo.getEstimatePojo().getClient().getEmails().stream().forEach(mailClientePojo -> mails.append(mailClientePojo.getEmail() + " "));
        templ = templ.replace("@mail@",mails.toString());
        pojo.getEstimatePojo().getClient().getPhoneNumbers().stream().forEach(phoneClientPojo -> phones.append(phoneClientPojo.getNumber() + " "));
        templ = templ.replace("@phone@",phones);
        templ = templ.replace("@address@",pojo.getEstimatePojo().getClient().getAddress());
        templ = templ.replace("@sate@",pojo.getEstimatePojo().getClient().getState());
        templ = templ.replace("@zipCode@",pojo.getEstimatePojo().getClient().getZipCode());

        String id = " " + pojo.getEstimatePojo().getIdEstimate();
        templ = templ.replace("@estimateNumber@", id);
        templ = templ.replace("@date@", simpleDateFormat.format(new Date()));

        // |----> bill and product and Materials Estimate <-----|

        // |----> product and Materials Estimate<-----|
        if (pojo.getEstimatePojo().getBills().size() > 0){

            if(pojo.getPrintProduct()){
                for (BillPojo billPojo: pojo.getEstimatePojo().getBills()) {

                    if (countGetBillproduct == 0 && billPojo.getProductsAndServices() != null && billPojo.getProductsAndServices().size() >0){
                        productosTabletRows.append(titleHeader("Products"));
                        productosTabletRows.append(heatProduct());
                        countGetBillproduct++;
                    }

                    if(billPojo.getProductsAndServices() != null && billPojo.getProductsAndServices().size() > 0){

                        billPojo.getProductsAndServices().stream().forEach(productPojo -> {
                            productosTabletRows.append(this.table(productPojo.getDescription(),
                                    String.valueOf(productPojo.getStockNumber()), String.valueOf(productPojo.getPrice()),
                                    String.valueOf(productPojo.getTotalPriceWithTaxes()), productPojo.getItemDeliteEdit()));

                            if (!productPojo.getItemDeliteEdit()){
                                partsSubtotalProduct.add(productPojo.getTotalPriceWithTaxes());
                            }
                        });

                    }else {
                        if (countGetBill == 0 ){
                            billTabletRows.append(titleHeader("Bills"));
                            billTabletRows.append(heatProduct());
                            countGetBill++;
                        }

                        billTabletRows.append(this.table(billPojo.getDescription(),"1",
                                String.valueOf(billPojo.getBillTotalWichoutTaxes()),
                                String.valueOf(billPojo.getBillTotal()),
                                billPojo.getItemDeliteEdit()));

                        if (!billPojo.getItemDeliteEdit()){
                            partsSubtotal.add(billPojo.getBillTotal());
                        }
                    }
                }

                partsSubtotalOverheadCalculate.addAll(partsSubtotalProduct);
                partsSubtotalOverheadCalculate.addAll(partsSubtotal);
                overheadCalculateValue += overheadNumberCalculate(partsSubtotalOverheadCalculate, Constant.TAXES, pojo.getEstimatePojo().getOverhead(), pojo.getCompany());

                productAndMaterials = calculateSubtotal(partsSubtotalOverheadCalculate);


                if(countGetBillproduct != 0){
                    tabletRows.append(productosTabletRows);
                    tabletRows.append(partsSubtotalTabletBuild(calculateSubtotal(partsSubtotalProduct)));
                    tabletRows.append("<tr style=\"height: 40px; width: 100%;\"></tr>");
                }

                if(countGetBill != 0){
                    tabletRows.append(billTabletRows);
                    tabletRows.append(partsSubtotalTabletBuild(calculateSubtotal(partsSubtotal)));
                }


                templ = templ.replace("@productWork@", tabletRows.toString());
                templ = templ.replace("@TotalCostMaterialsAndProduct@", buildTotalPayments("Materials and Product", String.valueOf(productAndMaterials)));

            }else {

                if (countGetBill == 0 ){
                    tabletRows.append(titleHeader("Materials and Product"));
                    countGetBill++;
                }

                // |----> bills and Materials <-----|
                    tabletRows.append(heatProduct());

                    pojo.getEstimatePojo().getBills().stream().forEach(billPojo -> {
                        tabletRows.append(
                                this.table(billPojo.getDescription(),"1", String.valueOf(billPojo.getBillTotalWichoutTaxes()), String.valueOf(billPojo.getBillTotal()), billPojo.getItemDeliteEdit()));

                        if (!billPojo.getItemDeliteEdit()) {
                            partsSubtotal.add(billPojo.getBillTotal());
                        }
                    });

                    overheadCalculateValue += overheadNumberCalculate(partsSubtotal, Constant.TAXES, pojo.getEstimatePojo().getOverhead(),pojo.getCompany());

                    productAndMaterials = calculateSubtotal(partsSubtotal);
                    tabletRows.append(partsSubtotalTabletBuild(productAndMaterials));

                    templ = templ.replace("@productWork@", tabletRows.toString());
                    templ = templ.replace("@TotalCostMaterialsAndProduct@", buildTotalPayments("Materials and Product", String.valueOf(productAndMaterials)));
            }
        }else {
            templ = templ.replace("@productWork@", " ");
            templ = templ.replace("@TotalCostMaterialsAndProduct@"," ");
        }


        // |---> service <---|
        if (pojo.getEstimatePojo().getServices().size() > 0){
            List<Double> partsSubtotalServices = new ArrayList<Double>();
            StringBuilder servicesTablet = new StringBuilder();

            servicesTablet.append(titleHeader("Service"));
            servicesTablet.append(heatProduct());

            pojo.getEstimatePojo().getServices().stream().forEach(service -> {

                servicesTablet.append(table(service.getDescriptionOfServicesCost(),
                        "1", String.valueOf(service.getServicesCost()),
                        String.valueOf(service.getServicesCost()),
                        service.isItemDeliteEdit()));

                if (!service.isItemDeliteEdit()) {
                    partsSubtotalServices.add(service.getServicesCost());
                }
            });

            overheadCalculateValue += overheadNumberCalculate(partsSubtotalServices, Constant.TAXES, pojo.getEstimatePojo().getOverhead(), pojo.getCompany());

            subtotalServices = calculateSubtotal(partsSubtotalServices);
            servicesTablet.append(partsSubtotalTabletBuild(subtotalServices));
            templ = templ.replace("@ServiceWorkTablet@", servicesTablet.toString());
            templ = templ.replace("@TotalCostService@",buildTotalPayments("Labor Cost Service", String.valueOf(subtotalServices)));

        }else{
            templ = templ.replace("@ServiceWorkTablet@"," ");
            templ = templ.replace("@TotalCostService@"," ");
        }

// |---> @subContractorsTablet@ <---|
        if (pojo.getEstimatePojo().getSubcontractors().size() > 0){
            List<Double> partsSubtotalSubcontractors = new ArrayList<Double>();
            StringBuilder subcontractorsTablet = new StringBuilder();

            List<Double> profitSubtotalSubcontractors = new ArrayList<Double>();
            List<Double>  porcentualProfit = new ArrayList<Double>();

            Double profitValue = 0.0;
            String profit ="";

            subcontractorsTablet.append(titleHeader("SubContractors"));
            subcontractorsTablet.append(heatProduct());

            pojo.getEstimatePojo().getSubcontractors().stream().forEach(subcontractor -> {
                List<Double> billsSubtotalSubcontractors = new ArrayList<Double>();

                subcontractorsTablet.append(table(subcontractor.getDescription(),"1",
                                                String.valueOf(subcontractor.getTotalCost()),
                                                String.valueOf(subcontractor.getTotalCost()),
                                                subcontractor.isItemDeliteEdit()));

                if (!subcontractor.isItemDeliteEdit()) {
                    partsSubtotalSubcontractors.add(subcontractor.getTotalCost());
                    porcentualProfit.add(subcontractor.getProfit());
                    subcontractor.getBillListSubcontractor().stream().forEach(bill -> billsSubtotalSubcontractors.add(bill.getBillTotal()));
                    profitSubtotalSubcontractors.add(this.profitCalculate(billsSubtotalSubcontractors, subcontractor.getCostOfwork(),subcontractor.getProfit()));
                }
            });

            profit = "Profit " + String.valueOf(this.porcentualProfitCalculate(porcentualProfit)) + "%";
            profitValue  = calculateSubtotal(profitSubtotalSubcontractors);

            subcontractorsTablet.append(table(profit," ", String.valueOf(profitValue), String.valueOf(profitValue), false));

            subtotalSubcontractors = calculateSubtotal(partsSubtotalSubcontractors);
            subcontractorsTablet.append(partsSubtotalTabletBuild(subtotalSubcontractors));


            templ = templ.replace("@subContractorsTablet@", subcontractorsTablet.toString());
            templ = templ.replace("@TotalCostSubContractors@", buildTotalPayments("subContractors", String.valueOf(subtotalSubcontractors)));

        }else{
            templ = templ.replace("@subContractorsTablet@"," ");
            templ = templ.replace("@TotalCostSubContractors@"," ");
        }


        String overheadPorcentual = "OverHead "+ pojo.getEstimatePojo().getOverhead().toString()+"%";
        Double overheadForm = Double.parseDouble(this.decim().format(overheadCalculateValue));
//        templ = templ.replace("@OverHead@", buildTotalPayments(overheadPorcentual, String.valueOf(overheadForm)));
        templ = templ.replace("@OverHead@", buildTotalPayments(overheadPorcentual,String.valueOf(Double.parseDouble(this.decim().format(overheadForm)))));

        Double totalCost = subtotalSubcontractors + subtotalServices + productAndMaterials + overheadCalculateValue;
        Double totalCostForm = Double.parseDouble(this.decim().format(totalCost));
        templ = templ.replace("@TotalCost@",total(String.valueOf(totalCostForm),null));

        templ = templ.replace("@Payments@", " ");
        templ = templ.replace("@TotalPayments@", " ");

    return  templ;
    }


    public String builingInvoice(EmailHandyManTallyPojo pojo){

        StringBuilder tabletRows = new StringBuilder();
        StringBuilder mails = new StringBuilder();
        StringBuilder phones = new StringBuilder();

        List<Double> partsSubtotal = new ArrayList<Double>();
        List<Double> partsSubtotalProduct = new ArrayList<Double>();
        List<Double> partsSubtotalServices = new ArrayList<Double>();


        StringBuilder billTabletRows = new StringBuilder();
        StringBuilder productosTabletRows = new StringBuilder();
        List<Double> partsSubtotalOverheadCalculate = new ArrayList<Double>();


        Integer countGetBillproduct = 0;
        Integer countGetBill = 0;

        Double productAndMaterials = 0.0;
        Double servicioSubtotal = 0.0;
        Double subtotalSubcontractors = 0.0;
        Double overheadCalculateValue = 0.0;

        DataJshandyManServicesConfigPojo dataJshandyManServices = dataJshandyMan.getActive(pojo.getCompany());

        String templ = pojo.getTemplate();
        templ = templ.replace("max-width:600px", "max-width:900px");

//        if(pojo.getEmail() != null && pojo.getEmail().getContent() != null && pojo.getEmail().getContent() != ""){
//            templ = templ.replace("@Comments@", this.addComment(pojo.getEmail().getContent()));
//        } else{
//            templ = templ.replace("@Comments@", " ");
//        }

        templ = templ.replace("@type@", "Invoice");
        templ = templ.replace("@Direction@", dataJshandyManServices.getDirection());
        templ = templ.replace("@Mail@", dataJshandyManServices.getMail());
        templ = templ.replace("@Web@", dataJshandyManServices.getWeb());
        templ = templ.replace("@PhoneNumber@",dataJshandyManServices.getPhoneNumber());
        templ = templ.replace("@TaxRegNumber@",dataJshandyManServices.getTaxRegNumber());
        templ = templ.replace("@clientName@", pojo.getWorkPojo().getClient().getName());

       pojo.getWorkPojo().getClient().getEmails().stream().forEach(mailClientePojo -> mails.append(mailClientePojo.getEmail() + " "));
       templ = templ.replace("@mail@", mails.toString());
       pojo.getWorkPojo().getClient().getPhoneNumbers().stream().forEach(phoneClientPojo -> phones.append(phoneClientPojo.getNumber() + " "));
       templ = templ.replace("@phone@", phones);
       templ = templ.replace("@address@", pojo.getWorkPojo().getClient().getAddress());
       templ = templ.replace("@sate@", pojo.getWorkPojo().getClient().getState());
       templ = templ.replace("@zipCode@", pojo.getWorkPojo().getClient().getZipCode());

       templ = templ.replace("@estimateNumber@", String.valueOf(pojo.getWorkPojo().getIdWork()));

       if(pojo.getWorkPojo().getStarDate() != null){
             templ = templ.replace("@date@", simpleDateFormat.format(pojo.getWorkPojo().getStarDate()));
       }else {
           templ = templ.replace("@date@", "-- / -- / ---- ");
       }



            // |----> product and Materials <-----|

        // |----> product and Materials Estimate<-----|
        if (pojo.getWorkPojo().getBills().size() > 0){
            if(pojo.getPrintProduct()){
                for (BillPojo billPojo: pojo.getWorkPojo().getBills()) {

                    if (countGetBillproduct == 0  && billPojo.getProductsAndServices() != null  && billPojo.getProductsAndServices().size() >0){
                        productosTabletRows.append(titleHeader("Products"));
                        productosTabletRows.append(heatProduct());
                        countGetBillproduct++;
                    }

                    if(billPojo.getProductsAndServices() != null && billPojo.getProductsAndServices().size() > 0){

                        billPojo.getProductsAndServices().stream().forEach(productPojo -> {
                            productosTabletRows.append(this.table(productPojo.getDescription(),
                                                                String.valueOf(productPojo.getStockNumber()),
                                                                String.valueOf(productPojo.getPrice()),
                                                                String.valueOf(productPojo.getTotalPriceWithTaxes()),
                                                                productPojo.getItemDeliteEdit()));

                            if (!productPojo.getItemDeliteEdit()) {
                                partsSubtotalProduct.add(productPojo.getTotalPriceWithTaxes());
                            }
                        });


                    } else {

                        if (countGetBill == 0 ){
                            productosTabletRows.append(titleHeader("Bills"));
                            tabletRows.append(heatProduct());
                            countGetBill++;
                        }

                        tabletRows.append(this.table(billPojo.getDescription(),"1",
                                                    String.valueOf(billPojo.getBillTotalWichoutTaxes()),
                                                    String.valueOf(billPojo.getBillTotal()),
                                                    billPojo.getItemDeliteEdit()));

                        if (!billPojo.getItemDeliteEdit()) {
                            partsSubtotal.add(billPojo.getBillTotal());
                        }
                    }
                }

                partsSubtotalOverheadCalculate.addAll(partsSubtotalProduct);
                partsSubtotalOverheadCalculate.addAll(partsSubtotal);

                overheadCalculateValue += overheadNumberCalculate(partsSubtotalOverheadCalculate, Constant.TAXES, pojo.getWorkPojo().getOverhead(), pojo.getCompany());

                productAndMaterials = calculateSubtotal(partsSubtotalOverheadCalculate);

                tabletRows.append(productosTabletRows);
                tabletRows.append(partsSubtotalTabletBuild(calculateSubtotal(partsSubtotalProduct)));

                if(countGetBillproduct != 0){
                    tabletRows.append("<tr style=\"height: 40px; width: 100%;\"></tr>");
                }

//                tabletRows.append(billTabletRows);
//                tabletRows.append(partsSubtotalTabletBuild(calculateSubtotal(partsSubtotal)));


                templ = templ.replace("@productWork@", tabletRows.toString());
                templ = templ.replace("@TotalCostMaterialsAndProduct@", buildTotalPayments("Materials and Product", String.valueOf(productAndMaterials)));

            }else {

                // |----> bills and Materials <-----|

                if (countGetBill == 0 ){
                    tabletRows.append(titleHeader("Materials and Product"));
                    countGetBill++;
                }

                tabletRows.append(heatProduct());
                pojo.getWorkPojo().getBills().stream().forEach(billPojo -> {
                    tabletRows.append(this.table(billPojo.getDescription(),"1",
                                                            String.valueOf(billPojo.getBillTotalWichoutTaxes()),
                                                            String.valueOf(billPojo.getBillTotal()),
                                                            billPojo.getItemDeliteEdit()));

                    if (!billPojo.getItemDeliteEdit()) {
                        partsSubtotal.add(billPojo.getBillTotal());
                    }
                });
                overheadCalculateValue += overheadNumberCalculate(partsSubtotal, Constant.TAXES, pojo.getWorkPojo().getOverhead(), pojo.getCompany());

                productAndMaterials = calculateSubtotal(partsSubtotal);
                tabletRows.append(partsSubtotalTabletBuild(productAndMaterials));
                templ = templ.replace("@productWork@", tabletRows.toString());
                templ = templ.replace("@TotalCostMaterialsAndProduct@", buildTotalPayments("Materials and Product", String.valueOf(productAndMaterials)));
            }
        }else {
            templ = templ.replace("@productWork@", " ");
            templ = templ.replace("@TotalCostMaterialsAndProduct@"," ");
        }


            // |---> service <---|
            if (pojo.getWorkPojo().getServices().size() > 0){

                StringBuilder servicesTablet = new StringBuilder();
                servicesTablet.append(titleHeader("Service"));
                servicesTablet.append(heatProduct());

                pojo.getWorkPojo().getServices().stream().forEach(service -> {
                    servicesTablet.append(this.table(service.getDescriptionOfServicesCost(),"1",
                            String.valueOf(service.getServicesCost()), String.valueOf(service.getServicesCost()),
                            service.isItemDeliteEdit()));

                    if (!service.isItemDeliteEdit()) {
                        partsSubtotalServices.add(service.getServicesCost());
                    }
                });

                overheadCalculateValue += overheadNumberCalculate(partsSubtotalServices, Constant.TAXES, pojo.getWorkPojo().getOverhead(), pojo.getCompany());

                servicioSubtotal = calculateSubtotal(partsSubtotalServices);
                servicesTablet.append(partsSubtotalTabletBuild(servicioSubtotal));
                templ = templ.replace("@ServiceWorkTablet@", servicesTablet.toString());
                templ = templ.replace("@TotalCostService@", buildTotalPayments("Labor Cost Service", String.valueOf(servicioSubtotal)));

            }else{
                templ = templ.replace("@ServiceWorkTablet@"," ");
                templ = templ.replace("@TotalCostService@"," ");
            }


            // |---> @subContractorsTablet@ <---|
            if (pojo.getWorkPojo().getSubcontractors().size() > 0){
                List<Double> partsSubtotalSubcontractors = new ArrayList<Double>();
                StringBuilder subcontractorsTablet = new StringBuilder();

                List<Double> profitSubtotalSubcontractors = new ArrayList<Double>();
                List<Double>  porcentualProfit = new ArrayList<Double>();

                Double profitValue = 0.0;
                String profit ="";

                subcontractorsTablet.append(titleHeader("SubContractors"));
                subcontractorsTablet.append(heatProduct());

                pojo.getWorkPojo().getSubcontractors().stream().forEach(subcontractor -> {
                    List<Double> billsSubtotalSubcontractors = new ArrayList<Double>();

                    subcontractorsTablet.append(this.table(subcontractor.getDescription(),"1",
                            String.valueOf(subcontractor.getTotalCost()), String.valueOf(subcontractor.getTotalCost()),
                            subcontractor.isItemDeliteEdit()));

                    if (!subcontractor.isItemDeliteEdit()) {
                        partsSubtotalSubcontractors.add(subcontractor.getTotalCost());
                        porcentualProfit.add(subcontractor.getProfit());
                        subcontractor.getBillListSubcontractor().stream().forEach(bill -> billsSubtotalSubcontractors.add(bill.getBillTotal()));
                        profitSubtotalSubcontractors.add(this.profitCalculate(billsSubtotalSubcontractors, subcontractor.getCostOfwork(),subcontractor.getProfit()));
                    }
                });

                profit = "Profit " + String.valueOf(this.porcentualProfitCalculate(porcentualProfit)) + "%";
                profitValue  = calculateSubtotal(profitSubtotalSubcontractors);
                subcontractorsTablet.append(table(profit," ", String.valueOf(profitValue), String.valueOf(profitValue), false));

                subtotalSubcontractors = calculateSubtotal(partsSubtotalSubcontractors);
                subcontractorsTablet.append(partsSubtotalTabletBuild(subtotalSubcontractors));

                templ = templ.replace("@subContractorsTablet@", subcontractorsTablet.toString());
                templ = templ.replace("@TotalCostSubContractors@", buildTotalPayments("subContractors", String.valueOf(subtotalSubcontractors)));

            }else{
                templ = templ.replace("@subContractorsTablet@"," ");
                templ = templ.replace("@TotalCostSubContractors@"," ");
            }


        String overheadPorcentual = "OverHead "+pojo.getWorkPojo().getOverhead().toString()+"%";


        templ = templ.replace("@OverHead@", buildTotalPayments(overheadPorcentual,String.valueOf(Double.parseDouble(this.decim().format(overheadCalculateValue)))));

        Double totalCost = productAndMaterials + servicioSubtotal + subtotalSubcontractors + overheadCalculateValue;
          Double totalCostForm = Double.parseDouble(this.decim().format(totalCost));
        templ = templ.replace("@TotalCost@", total(String.valueOf(totalCostForm),null));


        // |---> @Payments@ <---|

        if (pojo.getAvancePayments() && pojo.getWorkPojo().getPayments() != null && pojo.getWorkPojo().getPayments().size() > 0 ){
            List<Double> subtotalPayments = new ArrayList<Double>();
            StringBuilder tablePaments = new StringBuilder();

            pojo.getWorkPojo().getPayments().stream().forEach(pay -> {
                tablePaments.append(this.TabletPayments(pay.getTypePayment(),String.valueOf(pay.getAmountPaind())));
                subtotalPayments.add(pay.getAmountPaind());
            });

            templ = templ.replace("@Payments@", tablePaments.toString());
            Double totalRest = Double.parseDouble(this.decim().format(pojo.getWorkPojo().getTotalCostWork() - calculateSubtotal(subtotalPayments)));
            templ = templ.replace("@TotalPayments@", this.total(String.valueOf(totalRest),"Payment"));

        }else{
            templ = templ.replace("@Payments@", " ");
            templ = templ.replace("@TotalPayments@", " ");
        }

        return templ;
    }


    public String TabletPayments(String typePayment, String amountPaind){

        StringBuilder rows = new StringBuilder();
        rows.append(" <tr style=\"height: 18px;\">\n");
        rows.append("  <td style=\"text-align: left; padding-left: 8%;\">"+typePayment+"</td>\n");
        rows.append("  <td style=\"text-align: right; padding-right: 5%;\"></td>\n");
        rows.append("  <td style=\"text-align: right; padding-right: 5%; font-weight: bold;\">$"+amountPaind+"</td>\n");
        rows.append("  <td style=\"text-align: right; padding-right: 5%; font-weight: bold;\"></td>\n");
        rows.append(" </tr>");

        return rows.toString();
    }


    public String total(String total, String title){

        if(title == null){ title =""; }

        StringBuilder rows = new StringBuilder();
        rows.append(" <tr style=\"height: 18px;\">\n");
        rows.append("  <td style=\"text-align: left; padding-left: 8%;\"></td>\n");
        rows.append("  <!-- <td style=\"text-align: right; padding-right: 5%;\"></td> -->\n");
        rows.append("  <td class=\"total-1\">Total "+title+":</td>\n");
        rows.append("  <td class=\"total\">$"+total+"</td>\n");
        rows.append( "  </tr>");

        return rows.toString();
    }

    public  String buildTotalPayments(String item, String subTotal){
        StringBuilder tabletRows = new StringBuilder();
        tabletRows.append(" <tr style=\"height: 18px;\">\n" );
        tabletRows.append("               <td style=\"text-align: left; padding-left: 8%;\">"+item+"</td>\n");
        tabletRows.append("               <td class=\"total-Paind\"> </td>\n");
        tabletRows.append("               <td class=\"total-Paind\">"+subTotal+"</td>\n");
        tabletRows.append("               <td style=\"text-align: right; padding-right: 5%; font-weight: bold;\"></td>\n");
        tabletRows.append( "             </tr>\n");
        return tabletRows.toString();
    }

    public String titleHeader(String title){
        StringBuilder titleHeader = new StringBuilder();
        titleHeader.append(" <tr>\n");
        titleHeader.append("                      <td\n  style=\"background-color: #ffffff; text-align:left; padding: 10px; font-weight: bold; line-height: 22px;\">\n" );
        titleHeader.append(   "                                "+title+":\n");
        titleHeader.append("                       </td>\n");
        titleHeader.append( "                                </tr>\n");
        return titleHeader.toString();
    }


   public Double overheadNumberCalculate(List<Double> partsSubtotal, String TaxesDescriptionSearch, Double overhead, String company){

       logger.info("overhead Number Calculate... 1 ");
       Double totalPriceWithTaxes = 0.0;
       double porcentaje=0.0;

       for (Double price:partsSubtotal ) {
           totalPriceWithTaxes += price;
       }
       logger.info("overhead Number Calculate... 2 ");
       porcentaje = overhead != null? totalPriceWithTaxes * (overhead/100): totalPriceWithTaxes * (taxesAndPriceService.findByDescription(TaxesDescriptionSearch, company).getOverHead()/100);

       DecimalFormatSymbols separadorPer = new DecimalFormatSymbols();
       separadorPer.setDecimalSeparator('.');
       DecimalFormat format1 = new DecimalFormat("#.##",separadorPer);

       logger.info("overhead Number Calculate... 3 ");
//       String doblePorcentajeInString =format1.format(porcentaje);
       String doblePorcentajeInString = this.decim().format(porcentaje);
       logger.info("overhead Number Calculate  Value:  "+ doblePorcentajeInString);
       logger.info("overhead Number Calculate... 4 ");
       Double number = Double.parseDouble(doblePorcentajeInString);
       return number;
   }


    public Double profitCalculate(List<Double> subtotal, Double costWork,  Double porcent){
        Double total = 0.0;
        Double profit = 0.0;

        if( costWork != null  && porcent != null) {

            if (subtotal != null && subtotal.size() > 0){
                for (Double amont : subtotal ) {
                    total += amont;
                }
            }
           profit = (costWork + total) * (porcent/100);
       }

        return Double.parseDouble(this.decim().format(profit));
    }


    public Double porcentualProfitCalculate(List<Double> subtotal){
        Double total = 0.0;
        Double profitInPorcent = 0.0;


            if (subtotal != null && subtotal.size() > 0){
                for (Double amont : subtotal ) {
                    total += amont;
                }
            }
        profitInPorcent = total/subtotal.size() ;

        return  profitInPorcent;
    }


    public Double calculateSubtotal(List<Double> partsSubtotal ){
        Double subTotal = 0.0;
        for (Double price:partsSubtotal ) {
            subTotal += price;
        }
    return Double.parseDouble(this.decim().format(subTotal));
    }


    public String partsSubtotalTabletBuild( Double partsSubtotal){

        StringBuilder partsSubtotalTablet = new StringBuilder();

                partsSubtotalTablet.append("                     <tr style=\"height: 18px;\">\n");
                partsSubtotalTablet.append("                     <td style=\"text-align: left; padding-left: 8%;\"></td>\n" );
                partsSubtotalTablet.append("                     <td style=\"text-align: right; padding-right: 5%;\"></td>\n" );
                partsSubtotalTablet.append("                     <td\n  style=\"text-align: justify; padding-right: 5%; border-top: #0c0b0b solid 1px;\">\n" );
                partsSubtotalTablet.append("                      Subtotal:</td>\n" );
                partsSubtotalTablet.append("                      <td\n  style=\"text-align: right; padding-right: 5%; border-top: #0c0b0b solid 1px;\">\n");
                partsSubtotalTablet.append("                        "+partsSubtotal+"  \n");
                partsSubtotalTablet.append( "                     </td>\n");
                partsSubtotalTablet.append("                     </tr>\n" );
                partsSubtotalTablet.append("                     </table>\n" );
                partsSubtotalTablet.append("                     </td>\n");
                partsSubtotalTablet.append("                     </tr>");

        return  partsSubtotalTablet.toString();
    }



    public String heatProduct(){
    StringBuilder heatProduct = new StringBuilder();

        heatProduct.append("                                <tr>\n" );
        heatProduct.append("                                    <td align=\"left\">\n" );
        heatProduct.append( "                                        <table class=\"table-text\">\n");
        heatProduct.append("                                            <tr style=\"background-color: #fd604b; height: 25px;\">\n" );
        heatProduct.append("                                                <th style=\"text-align: left; padding-left: 8%; color: #ffffff;\">\n");
        heatProduct.append("                                                    Description\n" );
        heatProduct.append("                                                </th>\n" );
        heatProduct.append("                                                <th style=\"text-align: right; padding-right: 5%; color: #ffffff;\">\n");
        heatProduct.append("                                                    Quantity\n");
        heatProduct.append("                                                </th>\n");
        heatProduct.append("                                                <th style=\"text-align: right; padding-right: 5%; color: #ffffff;\">\n" );
        heatProduct.append("                                                    Rate\n");
        heatProduct.append("                                                </th>\n");
        heatProduct.append("                                                <th style=\"text-align: right; padding-right: 5%; color: #ffffff;\">\n");
        heatProduct.append("                                                    Amount\n");
        heatProduct.append("                                                </th>\n");
        heatProduct.append("                                            </tr>");

        return heatProduct.toString();
    }

//Products
    public String heatProductTile(String title){
        StringBuilder heatProduct = new StringBuilder();

        heatProduct.append(" <tr>\n");
        heatProduct.append("                      <td\n  style=\"background-color: #ffffff; text-align:left; font-weight: bold; line-height: 20px;\">\n" );
        heatProduct.append(   "                                "+title+":\n");
        heatProduct.append("                       </td>\n");
        heatProduct.append( "                                </tr>\n");

        return heatProduct.toString();
    }


    public String table(String description, String quantity, String rate, String amount, Boolean itemDeliteEdit){
        StringBuilder row = new StringBuilder("\n");

        if (itemDeliteEdit){
            row.append("<tr style=\"height: 18px;\">" + "\n");
            row.append("<td style=\"text-align: left; padding-left: 8%; text-decoration: line-through; color: black;\">"+description+"</td>"+"\n");
            row.append(" <td style=\"text-align: right; padding-right: 5%; text-decoration: line-through; color: black;\"> "+ quantity+" </td>"+"\n");
            row.append(" <td style=\"text-align: right; padding-right: 5%; text-decoration: line-through; color: black;\">"+rate+"</td>"+"\n");
            row.append("<td style=\"text-align: right; padding-right: 5%; text-decoration: line-through; color: black;\">"+amount+"</td>"+"\n");
            row.append("</tr>" + "\n");
        }else{
            row.append("<tr style=\"height: 18px;\">" + "\n");
            row.append("<td style=\"text-align: left; padding-left: 8%;\">"+description+"</td>"+"\n");
            row.append(" <td style=\"text-align: right; padding-right: 5%;\"> "+ quantity+" </td>"+"\n");
            row.append(" <td style=\"text-align: right; padding-right: 5%;\">"+rate+"</td>"+"\n");
            row.append("<td style=\"text-align: right; padding-right: 5%;\">"+amount+"</td>"+"\n");
            row.append("</tr>" + "\n");
        }
        return row.toString();
    }





    public String addComment(String comment){

        StringBuilder addComment = new StringBuilder();
        addComment.append("\n");
        addComment.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+"\n");
        addComment.append("<tr>"+"\n");
        addComment.append("<td align=\"center\" style=\"background-color: #eeeeee;\" bgcolor=\"#eeeeee\">"+"\n");
        addComment.append("<div style=\"margin-bottom: 20px; margin-top: 10px; padding: 35px; background-color: #fffbfbb4; max-width:900px; margin-left: 20%; margin-right: 20%; border-bottom: #c1bebe solid 2px;\" >"+"\n");
        addComment.append("     <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:900px;\">"+"\n");
        addComment.append("      <tr>");
        addComment.append("             <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; \">"+"\n");
        addComment.append("              <div style=\"font-size: 10px; margin: 0px; color: #0c0b0b;\">"+"\n");
        addComment.append("              <div style=\"text-align: left; margin-top: 20px; margin-bottom: 10px;\">Comments: </div>"+"\n");
        addComment.append("              <div style=\"text-align: justify; line-height: 18px;\">"+"\n");
        addComment.append(comment +"\n");
        addComment.append("              </div>"+"\n");
        addComment.append("             <div style=\"line-height: 20px; margin-top: 10px; margin-bottom: 10px;\"> JS Handyman Services of Tally, Inc </div>"+"\n");
        addComment.append("                 </div>"+"\n");
        addComment.append("         </td>"+"\n");
        addComment.append("        </tr>"+"\n");
        addComment.append("     </table>"+"\n");
        addComment.append("</div>"+"\n");
        addComment.append("</td>"+"\n");
        addComment.append("</tr>"+"\n");
        addComment.append("</table>"+"\n");

        return addComment.toString();
    }




    public String writeTemplateComentsSignature(String templete, DataJshandyManServicesConfigPojo jshandyManServicesConfig){
        StringBuilder rowComents = new StringBuilder("\n");
        templete = jshandyManServicesConfig.getComents1() != null ? templete.replace("@Coments1@", jshandyManServicesConfig.getComents1()): templete.replace("@Coments1@", " ");
        templete = jshandyManServicesConfig.getComents2() != null && !jshandyManServicesConfig.getComents2().equals("")? templete.replace("@Coments2@", jshandyManServicesConfig.getComents2()): templete.replace("@Coments2@", " ");
        templete = jshandyManServicesConfig.getComents3() != null && !jshandyManServicesConfig.getComents3().equals("")? templete.replace("@Coments3@", jshandyManServicesConfig.getComents3()): templete.replace("@Coments3@", " ");
        templete = jshandyManServicesConfig.getComents4() != null && !jshandyManServicesConfig.getComents4().equals("")? templete.replace("@Coments4@", jshandyManServicesConfig.getComents4()): templete.replace("@Coments4@", " ");
        templete = jshandyManServicesConfig.getCompany() != null && !jshandyManServicesConfig.getCompany().equals("")? templete.replace("@company@", jshandyManServicesConfig.getCompany()): templete.replace("@company@", " ");
        return templete;
    }

    public String setComent(String templ, EmailHandyManTallyPojo pojo){
        if(pojo.getEmail() != null && pojo.getEmail().getContent() != null && pojo.getEmail().getContent() != ""){
            templ = templ.replace("@Comments@", this.addComment(pojo.getEmail().getContent()));
        } else{
            templ = templ.replace("@Comments@", " ");
        }
        return templ;
    }


    public String writeTemplateInvoicePainFormate(String templete, EmailHandyManTallyPojo handyManTallyPojo){
        templete = templete.replace("@invoiceID@", handyManTallyPojo.getWorkPojo().getIdWork().toString());
        templete = templete.replace("@createDateInvoice@", simpleDateFormat.format(handyManTallyPojo.getWorkPojo().getCreateDay()));
        Double totalRest =0.0;

        if (handyManTallyPojo.getAvancePayments() && handyManTallyPojo.getWorkPojo().getPayments() != null && handyManTallyPojo.getWorkPojo().getPayments().size() > 0 ){
            List<Double> subtotalPayments = new ArrayList<Double>();
            StringBuilder tablePaments = new StringBuilder();

            handyManTallyPojo.getWorkPojo().getPayments().stream().forEach(pay -> {
                subtotalPayments.add(pay.getAmountPaind());
            });
            totalRest = Double.parseDouble(this.decim().format(handyManTallyPojo.getWorkPojo().getTotalCostWork() - calculateSubtotal(subtotalPayments)));
        }else {
            totalRest = handyManTallyPojo.getWorkPojo().getTotalCostWork();
        }

        templete = templete.replace("@TotalAmountInvoice@", totalRest.toString());
        templete = templete.replace("@clientName@", handyManTallyPojo.getWorkPojo().getClient().getFullName());
        templete = templete.replace("@Direction@", handyManTallyPojo.getDataJshandyManServices().getDirection());
        templete = templete.replace("@Company@", handyManTallyPojo.getDataJshandyManServices().getCompany());
        return templete;
    }


//    public void writeHtmlTimage(String html, String name) throws IOException {
//        HtmlImageGenerator hig = new HtmlImageGenerator();
//        hig.loadHtml(html);
//        hig.saveAsImage(new File(Constant.templateFile + name));
//    }

}



