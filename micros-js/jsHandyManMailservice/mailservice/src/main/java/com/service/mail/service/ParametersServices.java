package com.service.mail.service;


import com.service.mail.entitys.Parameters;
import com.service.mail.pojo.ParametersPojo;

public interface ParametersServices {
    public String findValue(String clave);
    public boolean saveOfFrom(Parameters parameters);
    public boolean save(Parameters parameters);
    public boolean delete( Long id);
    public ParametersPojo findByClave(String Clave);
}
