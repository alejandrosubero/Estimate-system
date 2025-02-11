package com.jshandyman.service.repository;

import com.jshandyman.service.entitys.Client;
import com.jshandyman.service.entitys.Subcontractor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcontractorRepository extends CrudRepository<Subcontractor, Long> {

    public Subcontractor findByCompany(String company);
    public Subcontractor findByCodeClient(String Code);
    public Subcontractor findByMail(String mail);

    @Query(value = "SELECT p FROM Subcontractor p WHERE CONCAT_WS( p.company, ' ', p.codeClient, ' ', p.mail, ' ', idWork, ' ',idEstimate, ' ',description) LIKE %?1%")
    public List<Subcontractor> finBySearch(String keyword);

}


