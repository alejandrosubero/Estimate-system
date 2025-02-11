package com.jshandyman.service.service;

import com.jshandyman.service.pojo.ServiceHandyManTallyPojo;

import java.util.List;

public interface ServiceHandyManTallyService {

    public List<ServiceHandyManTallyPojo> finBySearch(String keyword);
    public ServiceHandyManTallyPojo findByIdWork(Long idWork);
    public List<ServiceHandyManTallyPojo> findByDescriptionOfServicesCostContaining(String description);
    public ServiceHandyManTallyPojo findByIdServices(Long idServices);
    public Boolean save(ServiceHandyManTallyPojo pojo);
}
