package com.service.crom.services;


import com.service.crom.entitys.Parameters;
import com.service.crom.pojo.ParametersPojo;

import java.util.List;

public interface ParametersServices {
    public String findValue(String clave);
    public boolean saveOfFrom(Parameters parameters);
    public boolean save(Parameters parameters);
    public boolean delete( Long id);
    public ParametersPojo findByClave(String Clave);
    public List<ParametersPojo> allPrameter();
}
