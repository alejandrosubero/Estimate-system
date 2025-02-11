package com.jshandyman.service.security.logingTrack.repository;

import com.jshandyman.service.security.logingTrack.entitys.LogingTrack;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface LogingTrackRepository extends CrudRepository<LogingTrack, Long> {

    public Optional<LogingTrack> findByCodeWorkAndToken(String codeWork,String token);
    public Optional<LogingTrack> findByCodeWorkAndActive(String codeWork,Boolean active);
    public Optional<LogingTrack> findByToken(String token);
    public Optional<LogingTrack> findByTokenAndActive(String token,Boolean active);

    public LogingTrack save(LogingTrack logingTrack);

    @Transactional
    @Modifying
    @Query("update LogingTrack u set u.active = ?1 where u.idLogingTrack = ?2")
    void updateActive(Boolean active, Long idLogingTrack);

    @Transactional
    @Modifying
    @Query("update LogingTrack u set u.logUpDayOficial = ?1 where u.idLogingTrack = ?2")
    void updateLogUpDayOficial(Date logUpDayOficial, Long idLogingTrack);

}
// TODO: REALIZAR EL SERVICIO Y SU IMPLEMENTACION ASI COMO EL CONTROLADOR PARA LAS CONSULTAS.