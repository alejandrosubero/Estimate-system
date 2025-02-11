package com.jshandyman.service.service;

import com.jshandyman.service.pojo.DataJshandyManServicesConfigPojo;

import java.util.List;

public interface DataJshandyManServicesConfigService {

    public DataJshandyManServicesConfigPojo save(DataJshandyManServicesConfigPojo pojo);

    public List<DataJshandyManServicesConfigPojo> getAll();

    public DataJshandyManServicesConfigPojo getActive(String company);

    public DataJshandyManServicesConfigPojo findByUserCode(String userCode);

    public Boolean saveDataJshandyManServicesConfig(DataJshandyManServicesConfigPojo pojo);

    public boolean delete(Long id);

    public boolean deleteLogic(Long id);
}
