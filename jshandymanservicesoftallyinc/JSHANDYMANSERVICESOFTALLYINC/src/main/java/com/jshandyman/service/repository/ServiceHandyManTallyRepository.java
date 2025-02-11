package com.jshandyman.service.repository;


import com.jshandyman.service.entitys.ServiceHandyManTally;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceHandyManTallyRepository extends CrudRepository<ServiceHandyManTally, Long> {

    public ServiceHandyManTally findByIdWork(Long idWork);
    public List<ServiceHandyManTally> findByDescriptionOfServicesCostContaining(String description);
    public ServiceHandyManTally findByIdServices(Long idServices);

    @Query(value = "SELECT p FROM ServiceHandyManTally p WHERE CONCAT_WS( p.descriptionOfServicesCost, ' ', p.idWork, ' ', p.idServices, ' ', p.idEstimate) LIKE %?1%")
    public List<ServiceHandyManTally> finBySearch(String keyword);

}
