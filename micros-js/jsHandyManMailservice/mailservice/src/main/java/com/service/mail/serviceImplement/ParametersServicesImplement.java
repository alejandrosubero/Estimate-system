package com.service.mail.serviceImplement;


import com.service.mail.entitys.Parameters;
import com.service.mail.mapper.ParametersMapper;
import com.service.mail.pojo.ParametersPojo;
import com.service.mail.repository.ParametersRepository;
import com.service.mail.service.ParametersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
