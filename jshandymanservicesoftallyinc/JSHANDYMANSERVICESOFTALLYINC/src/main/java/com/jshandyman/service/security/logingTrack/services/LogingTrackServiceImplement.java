package com.jshandyman.service.security.logingTrack.services;

import com.jshandyman.service.security.logingTrack.entitys.LogingTrack;
import com.jshandyman.service.security.logingTrack.mappers.LogingTrackMapper;
import com.jshandyman.service.security.logingTrack.pojos.LogingTrackPojo;
import com.jshandyman.service.security.logingTrack.repository.LogingTrackRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LogingTrackServiceImplement implements LogingTrackService {

    protected static final Log logger = LogFactory.getLog(LogingTrackServiceImplement.class);

    @Autowired
    private LogingTrackRepository repository;

    @Autowired
    private LogingTrackMapper mapper;


    @Override
    public LogingTrackPojo findByCodeWorkAndActive(String codeWork, Boolean active) {
        LogingTrackPojo response = null;
        try {
            Optional<LogingTrack> track = repository.findByCodeWorkAndActive(codeWork, active);
            if(track.isPresent()){
                response = this.mapper.entityToPojo(track.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
        return response;
    }

    @Override
    public LogingTrackPojo findByToken(String token) {
        LogingTrackPojo response = null;
        try {
            response = this.mapper.entityToPojo(repository.findByToken(token).get());
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
        return response;
    }

    @Override
    public LogingTrackPojo findByTokenAndActive(String token, Boolean active) {
        LogingTrackPojo response = null;
        try {
            response = this.mapper.entityToPojo(repository.findByTokenAndActive(token, active).get());
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
        return response;
    }

    @Override
    public Boolean deleteLogingTrack(Long idLogingTrack) {
        try {
            repository.updateActive(false, idLogingTrack);
            repository.updateLogUpDayOficial(new Date(),idLogingTrack);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public LogingTrackPojo saveLogingTrack(LogingTrack logingTrack) {
        LogingTrackPojo response = null;
        try {
            response = mapper.entityToPojo(repository.save(logingTrack));
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
        return response;
    }
}
