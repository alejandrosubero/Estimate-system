package com.template.parameter.repository;


import com.template.parameter.entitys.Parameters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends CrudRepository<Parameters, Long> {

    public Parameters findByClave(String Clave);
    public  Parameters findByClaveAndActivo(String Clave, Boolean activo);
}
