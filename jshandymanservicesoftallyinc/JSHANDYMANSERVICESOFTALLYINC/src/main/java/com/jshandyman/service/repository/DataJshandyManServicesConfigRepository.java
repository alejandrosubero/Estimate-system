package com.jshandyman.service.repository;

import com.jshandyman.service.entitys.DataJshandyManServicesConfig;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataJshandyManServicesConfigRepository extends CrudRepository<DataJshandyManServicesConfig, Long> {

    public List<DataJshandyManServicesConfig> findByCompany(String company);
    public List<DataJshandyManServicesConfig> findByCompanyAndActive(String company, Boolean active);
    public DataJshandyManServicesConfig findByIdDataConfig(Long id);

    public Optional<DataJshandyManServicesConfig> findByUserCode(String userCode);
    public DataJshandyManServicesConfig save(DataJshandyManServicesConfig data);
    @Transactional
    @Modifying
    @Query("update DataJshandyManServicesConfig u set u.active = ?1 where u.idDataConfig = ?2")
    void updateActive(Boolean active, Long idDataConfig);

    @Query(value = "SELECT p FROM DataJshandyManServicesConfig p WHERE CONCAT( p.userCode, ' ', p.direction, ' ', p.phoneNumber, ' ', p.company) LIKE %?1%")
    public List<DataJshandyManServicesConfig> finBySearch(String keyword);
}


