package com.template.parameter.service;


import com.template.parameter.entitys.Parameters;
import com.template.parameter.pojo.ParametersPojo;

import java.util.List;

public interface ParametersServices {
    public String findValue(String clave);
    public boolean saveOfFrom(Parameters parameters);
    public boolean save(Parameters parameters);
    public boolean delete( Long id);
    public ParametersPojo findByClave(String Clave);
    public List<ParametersPojo> allPrameter();
}
