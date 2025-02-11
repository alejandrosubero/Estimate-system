package com.jshandyman.service.repository;

import com.jshandyman.service.entitys.Bill;
import com.jshandyman.service.entitys.Client;
import com.jshandyman.service.entitys.Template;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {

    public Template findBycodeTemplate(String codeTemplate);
    public Template findBycodeTemplateAndCompany(String codeTemplate, String company);
    public List<Template> findBycodeTemplateContaining(String codeTemplate);
    public List<Template> findByActive(Boolean active);

//    @Query(value = "SELECT p FROM Client p WHERE CONCAT( p.codeClient, ' ', p.name, ' ', p.address) LIKE %?1%")
//    public List<Client> finBySearch(String keyword);
}
