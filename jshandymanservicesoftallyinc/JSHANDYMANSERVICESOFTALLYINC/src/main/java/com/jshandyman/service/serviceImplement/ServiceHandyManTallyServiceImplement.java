package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.mapper.ServiceHandyManTallyMapper;
import com.jshandyman.service.pojo.ServiceHandyManTallyPojo;
import com.jshandyman.service.repository.ServiceHandyManTallyRepository;
import com.jshandyman.service.service.ServiceHandyManTallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceHandyManTallyServiceImplement implements ServiceHandyManTallyService {

    @Autowired
    private ServiceHandyManTallyRepository repository;

    @Autowired
    private ServiceHandyManTallyMapper mapper;


    @Override
    public ServiceHandyManTallyPojo findByIdWork(Long idWork) {
        return mapper.entityToPojo(repository.findByIdWork(idWork));
    }

    @Override
    public List<ServiceHandyManTallyPojo> finBySearch(String keyword) {
        List<ServiceHandyManTallyPojo> ser =  mapper.entityToPojoList(repository.finBySearch(keyword));
        return ser;
    }

    @Override
    public List<ServiceHandyManTallyPojo> findByDescriptionOfServicesCostContaining(String description) {
        return mapper.entityToPojoList(repository.findByDescriptionOfServicesCostContaining(description));
    }

    @Override
    public ServiceHandyManTallyPojo findByIdServices(Long idServices) {
        return mapper.entityToPojo(repository.findById(idServices).get());
    }


    @Override
    public Boolean save(ServiceHandyManTallyPojo pojo) {
       try {
           if (pojo != null){
               repository.save(mapper.pojoToEntity(pojo));
           }
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
        return true;
    }


//    @Override
//    public List<ServiceHandyManTallyPojo> findByDescriptionOfServicesCostContaining(String description) {
//        List<ServiceHandyManTallyPojo> list = new ArrayList<ServiceHandyManTallyPojo>();
//        repository.findByDescriptionOfServicesCostContaining(description)
//                .stream().forEach(serviceHandyManTally -> list.add(mapper.entityToPojo(serviceHandyManTally)));
//        return list;
//    }


}
