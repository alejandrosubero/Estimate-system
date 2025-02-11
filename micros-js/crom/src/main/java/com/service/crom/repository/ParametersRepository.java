package com.service.crom.repository;


import com.service.crom.entitys.Parameters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends CrudRepository<Parameters, Long> {

    public Parameters findByClave(String Clave);
    public  Parameters findByClaveAndActivo(String Clave, Boolean activo);
}
