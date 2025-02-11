package com.template.parameter.repository;


import com.template.parameter.entitys.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {

    public Template findBycodeTemplate(String codeTemplate);
    public List<Template> findBycodeTemplateContaining(String codeTemplate);
    public List<Template> findByActive(Boolean active);

//    @Query(value = "SELECT p FROM Client p WHERE CONCAT( p.codeClient, ' ', p.name, ' ', p.address) LIKE %?1%")
//    public List<Client> finBySearch(String keyword);
}
