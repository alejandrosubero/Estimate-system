package com.jshandyman.service.repository;

import com.jshandyman.service.entitys.SendMailTraker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendMailTrakerRepository extends CrudRepository<SendMailTraker, Long> {

    public  SendMailTraker findByIdEstimateReferene(Long id);
    public  SendMailTraker findByIdWorkReferene(Long id);

    public List<SendMailTraker> findByCompany(String company);
    public long countByIdWorkRefereneIsNotNull();
    public long countByIdEstimateRefereneIsNotNull();
}
