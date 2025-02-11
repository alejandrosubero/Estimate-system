package com.jshandyman.service.validation;

import com.jshandyman.service.pojo.RolPojo;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RolValidation {

    public RolPojo valida(RolPojo rol) {
        RolPojo pojo = null;
        try {
            if (rol != null) {
                if (rol.getRol() != null && rol.getDescription() != null) {
                    pojo = rol;
                }
            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return pojo;
        }
    }

    // remplace de name of variable for you proyecte
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