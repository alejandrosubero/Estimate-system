package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.entitys.DataJshandyManServicesConfig;
import com.jshandyman.service.mapper.DataJshandyManServicesMapper;
import com.jshandyman.service.pojo.DataJshandyManServicesConfigPojo;
import com.jshandyman.service.repository.DataJshandyManServicesConfigRepository;
import com.jshandyman.service.service.DataJshandyManServicesConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataJshandyManServicesConfigServiceImplement implements DataJshandyManServicesConfigService {

    protected static final Log logger = LogFactory.getLog(WorkServiceImplement.class);

    @Autowired
    private DataJshandyManServicesConfigRepository repository;

    @Autowired
    private DataJshandyManServicesMapper mapper;


    @Override
    public DataJshandyManServicesConfigPojo findByUserCode(String userCode) {
        DataJshandyManServicesConfigPojo configPojo = null;
        Optional<DataJshandyManServicesConfig> este = repository.findByUserCode(userCode);

        if (este.isPresent() && este.get().getActive()) {
            return mapper.entityToPojo(este.get());
        }
        return configPojo;
    }


    @Override
    public List<DataJshandyManServicesConfigPojo> getAll() {
        logger.info("Get all Data JshandyMan Services Config ");
        List<DataJshandyManServicesConfigPojo> lista = new ArrayList<DataJshandyManServicesConfigPojo>();

        repository.findAll().forEach(data -> {
            if (data.getActive()) {
                lista.add(mapper.entityToPojo(data));
            }
        });
        return lista;
    }


    @Override
    public DataJshandyManServicesConfigPojo getActive(String company) {
        logger.info("Get all DataJshandyManServicesConfig ");
        List<DataJshandyManServicesConfigPojo> lista = new ArrayList<DataJshandyManServicesConfigPojo>();
        try {
//            repository.findAll()
            repository.findByCompanyAndActive(company, true).forEach(data -> {
                if (data.getActive()) {
                    lista.add(mapper.entityToPojo(data));
                }
            });
            if (lista.size() == 1) {
                return lista.get(0);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DataJshandyManServicesConfigPojo save(DataJshandyManServicesConfigPojo pojo) {
        logger.info("save Data JshandyMan Services Config ");
        try {
//            List<DataJshandyManServicesConfigPojo> lista = getAll();
            List<DataJshandyManServicesConfigPojo> lista = new ArrayList<DataJshandyManServicesConfigPojo>();
                    repository.findByCompany(pojo.getCompany()).forEach(element ->{
                        lista.add(mapper.entityToPojo(element));
                    });

            if (lista.size() > 0) {
                lista.stream().forEach(element -> deleteLogic(element.getIdDataConfig()));
            }

            if(pojo.getActive() == null){
                pojo.setActive(true);
            }
            return mapper.entityToPojo(repository.save(mapper.pojoToEntity(pojo)));
        } catch (Exception e) {
            e.printStackTrace();
            return new DataJshandyManServicesConfigPojo();
        }
    }

    @Override
    public Boolean saveDataJshandyManServicesConfig(DataJshandyManServicesConfigPojo pojo) {

        logger.info("save DataJshandyManServicesConfig ");

        List<DataJshandyManServicesConfigPojo> lista = null;
        try {
            lista = getAll();

            if (lista.size() > 0) {
                lista.stream().forEach(element -> deleteLogic(element.getIdDataConfig()));
            }

            repository.save(mapper.pojoToEntity(pojo));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(Long id) {
        logger.info("Delete Estimate");
        boolean clave = false;
        try {
            repository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public boolean deleteLogic(Long id) {
        logger.info("Delete Estimate");
        boolean clave = false;
        try {
            repository.updateActive(false, id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


}
