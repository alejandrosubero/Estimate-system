package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.entitys.*;
import com.jshandyman.service.pojo.ClientPojo;
import com.jshandyman.service.service.AuditableService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuditableServiceImplement implements AuditableService {


    @Override
    public Work recursionUpdate(Work work, Work workBase) {
        work.setCreadoPor(workBase.getCreadoPor());
        work.setFechaHoraCreacion(workBase.getFechaHoraCreacion());

        if( work.getBills() !=null && work.getBills().size() > 0
                && workBase.getBills() != null && workBase.getBills().size() > 0){
            work.setBills(this.billsUpdate(work.getBills(), workBase.getBills()));
        }

        if( work.getServices() !=null && work.getServices().size() > 0
                && workBase.getServices() != null && workBase.getServices().size() > 0){
            work.setServices(this.servicesUpdate(work.getServices(),workBase.getServices()));
        }

        if( work.getClient() !=null && workBase.getClient() != null ){
            work.getClient().setCreadoPor(workBase.getClient().getCreadoPor());
            work.getClient().setFechaHoraCreacion(workBase.getClient().getFechaHoraCreacion());
        }

        if( work.getSubcontractors() !=null && work.getSubcontractors().size() > 0
                && workBase.getSubcontractors() != null && workBase.getSubcontractors().size() > 0){
            work.setSubcontractors(this.subcontractorUpdate(work.getSubcontractors(),workBase.getSubcontractors()));
        }

        if( work.getPayments() !=null && work.getPayments().size() > 0
                && workBase.getPayments() != null && workBase.getPayments().size() > 0){
            work.setPayments(this.paymentUpdate(work.getPayments(),workBase.getPayments()));
        }
        return  work;
    }


    @Override
    public Estimate recursionUpdate(Estimate estimate, Estimate estimateBase) {
        estimate.setCreadoPor(estimateBase.getCreadoPor());
        estimate.setFechaHoraCreacion(estimateBase.getFechaHoraCreacion());

        if( estimate.getBills() !=null && estimate.getBills().size() > 0
                && estimateBase.getBills() != null && estimateBase.getBills().size() > 0){
            estimate.setBills(this.billsUpdate(estimate.getBills(), estimateBase.getBills()));
        }


        if( estimate.getServices() !=null && estimate.getServices().size() > 0
                && estimateBase.getServices() != null && estimateBase.getServices().size() > 0){
            estimate.setServices(this.servicesUpdate(estimate.getServices(),estimateBase.getServices()));
        }

        if( estimate.getClient() !=null   && estimateBase.getClient() != null ){
            estimate.getClient().setCreadoPor(estimateBase.getClient().getCreadoPor());
            estimate.getClient().setFechaHoraCreacion(estimateBase.getClient().getFechaHoraCreacion());
        }

        if( estimate.getSubcontractors() !=null && estimate.getSubcontractors().size() > 0
                && estimateBase.getSubcontractors() != null && estimateBase.getSubcontractors().size() > 0){
            estimate.setSubcontractors(this.subcontractorUpdate(estimate.getSubcontractors(),estimateBase.getSubcontractors()));

        }
        return  estimate;
    }


    @Override
    public List<Bill> billsUpdate(List<Bill> billNew, List<Bill> billBase) {

        Map<Long, Bill> map1 = new HashMap<Long, Bill>();
        Map<Long, Bill> map2 = new HashMap<Long, Bill>();
        List<Bill> list = new ArrayList<Bill>();

        map1 =  billNew.stream().filter(x-> x.getIdBill() != null).collect(Collectors.toMap(x -> x.getIdBill(), x -> x));
        list =  billNew.stream().filter(x-> x.getIdBill() == null).collect(Collectors.toList());
        map2 =  billBase.stream().collect(Collectors.toMap(x -> x.getIdBill(), x -> x));


        for(Map.Entry<Long, Bill> entry : map1.entrySet()) {

            if(map1.get(entry.getKey()).getIdBill().toString().equals(map2.get(entry.getKey()).getIdBill().toString())){

                if(map1.get(entry.getKey()).getProductsAndServices() !=null
                        &&  map1.get(entry.getKey()).getProductsAndServices().size() > 0
                        &&  map2.get(entry.getKey()).getProductsAndServices() != null
                        &&  map2.get(entry.getKey()).getProductsAndServices().size() >0 ) {

                    map1.get(entry.getKey()).setProductsAndServices(
                            this.productUpdate(map1.get(entry.getKey()).getProductsAndServices(),
                                    map2.get(entry.getKey()).getProductsAndServices()));
                }

                map1.get(entry.getKey()).setCreadoPor(map2.get(entry.getKey()).getCreadoPor());
                map1.get(entry.getKey()).setFechaHoraCreacion(map2.get(entry.getKey()).getFechaHoraCreacion());

                list.add(map1.get(entry.getKey()));
            }
        }
        return list;
    }

    @Override
    public List<Product> productUpdate(List<Product> productNew, List<Product> productBase) {
        Map<Long, Product> map1 = new HashMap<Long, Product>();
        Map<Long, Product> map2 = new HashMap<Long, Product>();
        List<Product> list = new ArrayList<Product>();

        map1 =  productNew.stream().filter(x-> x.getIdProduct()!= null).collect(Collectors.toMap(x -> x.getIdProduct(), x -> x));
        list =  productNew.stream().filter(x-> x.getIdProduct()== null).collect(Collectors.toList());
        map2 =  productBase.stream().collect(Collectors.toMap(x -> x.getIdProduct(), x -> x));

        for(Map.Entry<Long, Product> entry : map1.entrySet()) {
            if(map1.get(entry.getKey()).getIdProduct().toString().equals(map2.get(entry.getKey()).getIdProduct().toString()) ){
                map1.get(entry.getKey()).setCreadoPor(map2.get(entry.getKey()).getCreadoPor());
                map1.get(entry.getKey()).setFechaHoraCreacion(map2.get(entry.getKey()).getFechaHoraCreacion());
                list.add(map1.get(entry.getKey()));
            }
        }
        return list;
    }

    @Override
    public List<ServiceHandyManTally> servicesUpdate(List<ServiceHandyManTally> servicesNew, List<ServiceHandyManTally> servicesBase) {
        Map<Long, ServiceHandyManTally> map1 = new HashMap<Long, ServiceHandyManTally>();
        Map<Long, ServiceHandyManTally> map2 = new HashMap<Long, ServiceHandyManTally>();
        List<ServiceHandyManTally> list = new ArrayList<ServiceHandyManTally>();

        map1 =  servicesNew.stream().filter(x-> x.getIdServices()!= null).collect(Collectors.toMap(x -> x.getIdServices(), x -> x));
        list =  servicesNew.stream().filter(x-> x.getIdServices() == null).collect(Collectors.toList());
        map2 =  servicesBase.stream().collect(Collectors.toMap(x -> x.getIdServices(), x -> x));

        for(Map.Entry<Long, ServiceHandyManTally> entry : map1.entrySet()) {

            if(map1.get(entry.getKey()).getIdServices().toString().equals(map2.get(entry.getKey()).getIdServices().toString())){
                map1.get(entry.getKey()).setCreadoPor(map2.get(entry.getKey()).getCreadoPor());
                map1.get(entry.getKey()).setFechaHoraCreacion(map2.get(entry.getKey()).getFechaHoraCreacion());
                list.add(map1.get(entry.getKey()));
            }
        }
        return list;
    }



    @Override
    public List<Subcontractor> subcontractorUpdate(List<Subcontractor> subcontractorNew, List<Subcontractor> subcontractorBase) {
        Map<Long, Subcontractor> map1 = new HashMap<Long, Subcontractor>();
        Map<Long, Subcontractor> map2 = new HashMap<Long, Subcontractor>();
        List<Subcontractor> list = new ArrayList<Subcontractor>();

        map1 =  subcontractorNew.stream().filter(x-> x.getIdSubContractor()!= null).collect(Collectors.toMap(x -> x.getIdSubContractor(), x -> x));
        list =  subcontractorNew.stream().filter(x-> x.getIdSubContractor()== null).collect(Collectors.toList());
        map2 =  subcontractorBase.stream().collect(Collectors.toMap(x -> x.getIdSubContractor(), x -> x));

        Date fechaHoraCreacion = null;
        String readoPor = null;

        for(Map.Entry<Long, Subcontractor> entry : map1.entrySet()) {
            if (map2.get(entry.getKey()) != null && map2.get(entry.getKey()).getCreadoPor() != null){
                readoPor = map2.get(entry.getKey()).getCreadoPor();
            }

            if (map2.get(entry.getKey()) != null && map2.get(entry.getKey()).getFechaHoraCreacion() != null){
                fechaHoraCreacion = map2.get(entry.getKey()).getFechaHoraCreacion();
            }

            map1.get(entry.getKey()).setCreadoPor(readoPor);
            map1.get(entry.getKey()).setFechaHoraCreacion(fechaHoraCreacion);

            if(map1.get(entry.getKey()).getBillListSubcontractor() !=null
                    &&  map1.get(entry.getKey()).getBillListSubcontractor().size() > 0
                    &&  map2.get(entry.getKey()).getBillListSubcontractor() != null
                    && map2.get(entry.getKey()).getBillListSubcontractor().size() >0 ) {

                // update el producto en la lista
                map1.get(entry.getKey()).setBillListSubcontractor(
                        this.billsUpdate(map1.get(entry.getKey()).getBillListSubcontractor(),
                                map2.get(entry.getKey()).getBillListSubcontractor()));
            }
            list.add(map1.get(entry.getKey()));
        }
        return list;
    }


    @Override
    public List<Payment> paymentUpdate(List<Payment> paymentsmentNew, List<Payment> paymentsBase) {
        Map<Long, Payment> map1 = new HashMap<Long, Payment>();
        Map<Long, Payment> map2 = new HashMap<Long, Payment>();
        List<Payment> list = new ArrayList<Payment>();

        map1 =  paymentsmentNew.stream().filter(x-> x.getIdPayment() != null).collect(Collectors.toMap(x -> x.getIdPayment(), x -> x));
        list =  paymentsmentNew.stream().filter(x-> x.getIdPayment() == null).collect(Collectors.toList());
        map2 =  paymentsBase.stream().collect(Collectors.toMap(x -> x.getIdPayment(), x -> x));

        for(Map.Entry<Long, Payment> entry : map1.entrySet()) {
            if(map1.get(entry.getKey()).getIdPayment().toString().equals(map2.get(entry.getKey()).getIdPayment().toString())){
//            if(map1.get(entry.getKey()).getIdPayment() == map2.get(entry.getKey()).getIdPayment()){

                map1.get(entry.getKey()).setCreadoPor(map2.get(entry.getKey()).getCreadoPor());
                map1.get(entry.getKey()).setFechaHoraCreacion(map2.get(entry.getKey()).getFechaHoraCreacion());
                list.add(map1.get(entry.getKey()));
            }
        }
        return list;
    }

}
