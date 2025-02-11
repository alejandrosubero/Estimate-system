package com.jshandyman.service.service;

import com.jshandyman.service.entitys.Parameters;
import com.jshandyman.service.pojo.ParametersPojo;

public interface ParametersServices {
    public String findValue(String clave);
    public boolean saveOfFrom(Parameters parameters);
    public boolean save(Parameters parameters);
    public boolean delete( Long id);
    public ParametersPojo findByClave(String Clave);
    public ParametersPojo findByClave(String Clave, String Company);

}
