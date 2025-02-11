package com.jshandyman.service.validation;

import com.jshandyman.service.entitys.Estimate;
import com.jshandyman.service.pojo.EstimatePojo;
import com.jshandyman.service.pojo.WorkPojo;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EstimateValidation {

    public EstimatePojo valida(EstimatePojo estimate) {
        EstimatePojo pojo = null;
        try {
            if (estimate != null) {
                if ( estimate.getDescription() != null
                        && estimate.getClient() != null
                        && estimate.getTotalCostWork() != null) {
                    pojo = estimate;
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
