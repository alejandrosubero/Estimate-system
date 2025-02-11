package com.service.crom.services;


import com.service.crom.entitys.Parameters;
import com.service.crom.mapper.ParametersMapper;
import com.service.crom.pojo.ParametersPojo;
import com.service.crom.repository.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametersServicesImplement implements ParametersServices {

    @Autowired
    private ParametersRepository repository;

    @Autowired
    private ParametersMapper mapper;


    @Override
    public boolean save(Parameters parameters) {
        try {
            repository.save(parameters);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveOfFrom(Parameters parameters) {
        ParametersPojo pojo = null;
        try {
            pojo = this.findByClave(parameters.getClave());
            if (pojo != null && pojo.getId() != null) {
                this.delete(pojo.getId());
            }
            repository.save(parameters);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public ParametersPojo findByClave(String clave) {
        try {
            return mapper.entityToPojo(repository.findByClave(clave));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ParametersPojo> allPrameter() {
        List<ParametersPojo> allPrameters = new ArrayList<>();
        repository.findAll().forEach(parameters -> allPrameters.add(mapper.entityToPojo(parameters)));
        return allPrameters;
    }


    @Override
    public String findValue(String clave) {
        ParametersPojo parametro = null;
        try {
            parametro = mapper.entityToPojo(repository.findByClaveAndActivo(clave, true));
            if (parametro != null && parametro.getValor() != null) {
                return parametro.getValor();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
