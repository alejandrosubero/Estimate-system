package com.jshandyman.service.repository;

import com.jshandyman.service.entitys.Parameters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends CrudRepository<Parameters, Long> {

    public Parameters findByClave(String Clave);
    public  Parameters findByClaveAndActivo(String Clave, Boolean activo);
    public  Parameters findByClaveAndCompany(String Clave, String company);
}
