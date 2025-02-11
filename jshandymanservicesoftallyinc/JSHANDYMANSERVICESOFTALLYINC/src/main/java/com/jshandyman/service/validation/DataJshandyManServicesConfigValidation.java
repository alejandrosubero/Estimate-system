package com.jshandyman.service.validation;

import com.jshandyman.service.pojo.DataJshandyManServicesConfigPojo;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DataJshandyManServicesConfigValidation {


    public DataJshandyManServicesConfigPojo valida(DataJshandyManServicesConfigPojo data) {
        DataJshandyManServicesConfigPojo pojo = null;
        try {
            if (data != null) {
                if ( data.getUserCode() != null ) {
                    pojo = data;
                }
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }


    public Long valida_id(String poder) {
        Long id_Delete = 0l;
        try {
            if (poder != null) {
                if (poder.length() > 0 && !Pattern.matches("[a-zA-Z]+", poder)) {
                    id_Delete = Long.parseLong(poder);
                }
            }
            return id_Delete;
        } catch (Exception e) {
            e.printStackTrace();
            return id_Delete;
        }
    }

    public <T> Object validation(T t) {
        T elemento = null;
        try {
            if (t != null) {
                elemento = t;
            }
            return elemento;
        } catch (Exception e) {
            e.printStackTrace();
            return elemento;
        }
    }

}
